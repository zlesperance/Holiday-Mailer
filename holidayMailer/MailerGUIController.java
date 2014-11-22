package holidayMailer;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MailerGUIController implements Initializable{
	@FXML
	TableView<Contact> contactsTable;
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
	private DBAccess dbAccess;
	
	@Override
	public void initialize (URL location, ResourceBundle resources) {		
		initializeTable();
	} // end initialize
	
	private void initializeTable () {
		//ObservableList<TableColumn<Contact, ?>> columns = contactsTable.getColumns();
		
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
		if (this.dbAccess == null) {
			this.userOut.printString("Error: Database Not Found");
			return;
		}
		
		ObservableList<Contact> data = FXCollections.observableArrayList();
		try {
			ArrayList<Contact> contacts = this.dbAccess.getAllContacts();
			for (Contact contact : contacts) {
				data.add(contact);
			}
		} catch (SQLException e) {
			this.userOut.printString("Error: Could not query users: " + e.getMessage());
		}
		
		contactsTable.setItems(data);
	} // end refreshTable
	
	public void initDB (DBAccess dbAccess) {
		this.dbAccess = dbAccess;
	} // end initDB
	
	public void initUserOut (UserOut userOut) {
		this.userOut = userOut;
	} // end initUserOut
	
	public void initUserIn (UserIn userIn) {
		this.userIn = userIn;
	} // end initUserIn
	
	@FXML
	private void handleQuitAction (ActionEvent event) {
		GUIClient.close();
	}
} // end MailerGUIController
