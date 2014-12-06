package holidayMailer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddContactController implements Initializable {
	@FXML
	Button addContactButton;
	@FXML
	TextField contactEmail;
	@FXML
	TextField contactFirstName;
	@FXML
	TextField contactLastName;
	@FXML
	TextField contactLastReceived;
	
	private UserIn userIn;
	private UserOut userOut;
	private MailerGUIController parentWindow;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	} // end initialize
	
	public void setUserIn (UserIn userIn) {
		this.userIn = userIn;
	} // end setUserIn
	
	public void setUserOut (UserOut userOut) {
		this.userOut = userOut;
	} // end setUserOut
	
	public void setParentWindow (MailerGUIController parentWindow) {
		this.parentWindow = parentWindow;
	} // setParentWindow
	
	@FXML
	private void handleAddContact (ActionEvent event) {
		// TODO: Use UserIn Here?
		String firstName = contactFirstName.getText();
		String lastName = contactLastName.getText();
		String email = contactEmail.getText();
		int lastReceived;
		try {
			lastReceived = Integer.parseInt(contactLastReceived.getText());
		} catch (NumberFormatException e) {
			if (this.userOut != null) {
				this.userOut.printString("Error: Please enter a valid year.");
			}
			return;
		}
		
		// TODO: Validate all the inputs
		
		// TODO: Use the ContactFactory here
		Contact newContact = new Contact(firstName, lastName, email, lastReceived);
		
		if (this.parentWindow == null) {
			this.userOut.printString("Error: Parent Window Not Found!");
			return;
		}
		this.parentWindow.addContactToTable(newContact);
		
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	} // end handleAddContact
	
	@FXML
	private void handleCancel (ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	} // end handleCancel

}
