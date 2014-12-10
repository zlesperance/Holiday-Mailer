package holidayMailer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserWindowController implements Initializable, ChildWindow {
	@FXML
	TextField emailField;
	@FXML
	PasswordField passwordField;
	
	private MailerGUIController parentWindow;
	private UserOut userOut;

	public void initialize (URL location, ResourceBundle resources) {
		
	} // end initialize
	
	public void setParentWindow (MailerGUIController parentWindow) {
		this.parentWindow = parentWindow;
	} // end setParentWindow
	
	public void setUserOut (UserOut userOut) {
		this.userOut = userOut;
	} // end setUserOut
	
	@FXML
	private void handleSave (ActionEvent event) {
		String email = emailField.getText();
		String pass = passwordField.getText();
		
		if (email.length() == 0) {
			this.userOut.printError("Error: The Email field is required");
			return;
		}
		
		if (pass.length() == 0) {
			this.userOut.printError("Error: The Password field cannot be left blank");
			return;
		}
		
		this.parentWindow.setEmail(email, pass);
		
		pass = null;
		
		this.close(event);
	} // end handleSave
	
	@FXML
	private void handleCancel (ActionEvent event) {
		close(event);
	} // end handleCancel
	
	private void close (ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();	
	} // end close
}
