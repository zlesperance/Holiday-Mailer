package holidayMailer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MailerGUIController implements Initializable {
	@FXML
	TableView<Contact> contactsTable;
	@FXML
	MenuBar mailerMenuBar;
	@FXML
	MenuItem newContactButton;
	@FXML
	MenuItem removeSelectedButton;
	@FXML
	MenuItem quitButton;
	@FXML
	Button emailSelectedButton;
	@FXML
	MenuItem aboutButton;
	@FXML
	Button emailAllButton;
	
	private UserOut userOut;
	private UserIn userIn;
	private MailControl mailControl;
	private Stage childWindow;
	private ResourceBundle resources;
	private ObservableList<Contact> removalBuffer;
	
	public void initialize (URL location, ResourceBundle resources) {
		this.resources = resources;
		initializeTable();
		BooleanBinding hasItemSelected = contactsTable.getSelectionModel().selectedItemProperty().isNull();
		removeSelectedButton.disableProperty().bind(hasItemSelected);
		emailSelectedButton.disableProperty().bind(hasItemSelected);
	} // end initialize
	
	private void initializeTable () {
		//ObservableList<TableColumn<Contact, ?>> columns = contactsTable.getColumns();
		// Set up context menu:
		contactsTable.setRowFactory(
			new Callback<TableView<Contact>, TableRow<Contact>>() {
				public TableRow<Contact> call(TableView<Contact> tableView) {
				final TableRow<Contact> row = new TableRow<Contact>();
				final ContextMenu rowMenu = new ContextMenu();
				MenuItem editItem = new MenuItem("Edit");
				editItem.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
				    	userOut.printError("Edit Menu Not Implemented");
				    }
				});
				MenuItem removeItem = new MenuItem("Delete");
				removeItem.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						contactsTable.getItems().remove(row.getItem());
					}
				});
				rowMenu.getItems().addAll(editItem, removeItem);
				
				// only display context menu for non-null items:
				row.contextMenuProperty().bind(
					Bindings.when(Bindings.isNotNull(row.itemProperty()))
						.then(rowMenu)
						.otherwise((ContextMenu)null));
					return row;
				}
		});
		
		TableColumn<Contact,String> firstNameCol = new TableColumn<Contact,String>("First Name");
		firstNameCol.setPrefWidth(154.0);
		firstNameCol.setCellValueFactory(
				new PropertyValueFactory<Contact,String>("fName")
		);
		
		TableColumn<Contact,String> lastNameCol = new TableColumn<Contact,String>("Last Name");
		lastNameCol.setPrefWidth(137.0);
		lastNameCol.setCellValueFactory(
				new PropertyValueFactory<Contact,String>("lName")
		);
		
		TableColumn<Contact,String> emailCol = new TableColumn<Contact,String>("Email");
		emailCol.setPrefWidth(187.0);
		emailCol.setCellValueFactory(
				new PropertyValueFactory<Contact,String>("addr")
		);
		
		TableColumn<Contact,String> lastReceivedCol = new TableColumn<Contact,String>("Last Received");
		lastReceivedCol.setPrefWidth(98.0);
		lastReceivedCol.setCellValueFactory(
				new PropertyValueFactory<Contact,String>("lastRec")
		);
		
		contactsTable.getColumns().clear();
		contactsTable.getColumns().addAll(firstNameCol, lastNameCol, emailCol, lastReceivedCol);
		contactsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	} // end initializeTable
	
	public void refreshTable () {
		if (this.userOut == null) {
			return;
		}
		if (this.mailControl == null) {
			this.userOut.printError("Error: Database Not Found");
			return;
		}
		
		ObservableList<Contact> data = FXCollections.observableArrayList();
		
		ArrayList<Contact> contacts = this.mailControl.getContacts();
		for (Contact contact : contacts) {
			data.add(contact);
		}
		
		contactsTable.setItems(data);
	} // end refreshTable
	
	public void addContactToTable (Contact contact) {
		
		this.mailControl.addContact(contact);
		contactsTable.getItems().add(contact);
		
	} // end addContactToTable
		
	public void initDB (MailControl mc) {
		
		this.mailControl = mc;
	} // end initDB
	
	public void initUserOut (UserOut userOut) {
		this.userOut = userOut;
	} // end initUserOut
	
	public void initUserIn (UserIn userIn) {
		this.userIn = userIn;
	} // end initUserIn
	
	@FXML
	private void handleQuitAction (ActionEvent event) {
		
		try {
			this.mailControl.commitChanges();
		} catch (SQLException e) {
			this.userOut.printError("An Error Occurred while closing the database: " + e.getMessage());
		}//try/catch
		
		Stage stage = (Stage) mailerMenuBar.getScene().getWindow();
		stage.close();
		
		if (this.childWindow != null && this.childWindow.isShowing()) {
			this.childWindow.close();
		}
	} // end handleQuitAction
	
	@FXML
	private void handleNewContact (ActionEvent event) {
		//this.parentWindow.openWindow(GUIClient.ADD_USER_WINDOW_NAME);
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("addContactWindow.fxml"), this.resources);
			root = loader.load();
			if (this.childWindow != null) {
				this.childWindow.close();
				this.childWindow = null;
			}
			ChildWindow controller = loader.getController();
			controller.setParentWindow(this);
			
			this.childWindow = new Stage();
			this.childWindow.setTitle("Add New Contact");
			this.childWindow.setScene(new Scene(root));
			this.childWindow.show();
		} catch (IOException e) {
			this.userOut.printError("An Error occurred when opening the new window: " + e.getMessage());
		}
	} // end handleNewContact
	
	@FXML
	private void handleAbout (ActionEvent event) {
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("aboutWindow.fxml"), this.resources);
			root = loader.load();
			if (this.childWindow != null) {
				this.childWindow.close();
				this.childWindow = null;
			}
			
			this.childWindow = new Stage();
			this.childWindow.initModality(Modality.APPLICATION_MODAL);
			this.childWindow.setTitle("About");
			this.childWindow.setScene(new Scene(root));
			this.childWindow.show();
		} catch (IOException e) {
			this.userOut.printError("An Error occurred when opening the new window: " + e.getMessage());
		}
	} // end handleAbout
	
	@FXML
	private void handleEmailSelected (ActionEvent event) {
		ObservableList<Contact> selectedContacts = contactsTable.getSelectionModel().getSelectedItems();
		openSendWindow(selectedContacts);
	} // end handleEmailSelected
	
	@FXML
	private void handleEmailAll (ActionEvent event) {
		ObservableList<Contact> allContacts = contactsTable.getItems();
		openSendWindow(allContacts);
	} // end handleEmailAll
	
	private void openSendWindow (ObservableList<Contact> contacts) {
		if (contacts.isEmpty()) {
			this.userOut.printError("Error: No contacts selected");
			return;
		}
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("sendMailWindow.fxml"), this.resources);
			root = loader.load();
			if (this.childWindow != null) {
				this.childWindow.close();
				this.childWindow = null;
			}
			SendMailWindowController controller = loader.getController();
			controller.setToParam(contacts);
			
			this.childWindow = new Stage();
			this.childWindow.setTitle("Send Mail");
			this.childWindow.setScene(new Scene(root));
			this.childWindow.show();
		} catch (IOException e) {
			this.userOut.printError("An Error occurred when opening the new window: " + e.getMessage());
		}
	} // end openSendWindow
	
	@FXML
	private void handleRemoveSelected (ActionEvent event) {
		this.removalBuffer = contactsTable.getSelectionModel().getSelectedItems();
		
		if (this.removalBuffer.isEmpty()) {
			this.userOut.printError("Error: No contacts selected");
			return;
		}
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("removeSelectedWindow.fxml"), this.resources);
			root = loader.load();
			if (this.childWindow != null) {
				this.childWindow.close();
				this.childWindow = null;
			}
			ChildWindow controller = loader.getController();
			controller.setParentWindow(this);
			
			this.childWindow = new Stage();
			this.childWindow.setTitle("Remove " + this.removalBuffer.size() + " Contact" + ((this.removalBuffer.size() > 1)? "s" : ""));
			this.childWindow.setScene(new Scene(root));
			this.childWindow.show();
		} catch (IOException e) {
			this.userOut.printError("An Error occurred when opening the new window: " + e.getMessage());
		}
	} // end handleRemoveSelected
	
	@FXML
	public void confirmRemoval () {
		if (this.removalBuffer == null)
			return;
		
		for (Contact contact : this.removalBuffer) {
			
			this.mailControl.deleteContact(contact);
		}
		
		this.removalBuffer = null;
		refreshTable();
	} // end confirmRemoval
	
	@FXML
	public void handleFilter (ActionEvent event) {
		
	} // end handleFilter
	
	public void clearFilters () {
		refreshTable();
	} // end clearFilters
	
	public void filterContacts (String firstName) {
		
	}
} // end MailerGUIController
