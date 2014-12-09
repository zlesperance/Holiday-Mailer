package holidayMailer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RemoveContactsController implements Initializable, ChildWindow {
	private MailerGUIController parentWindow;
	
	@FXML
	Button cancelButton;
	
	@FXML
	Button actionButton;

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	} // end initialize
	
	public void setParentWindow (MailerGUIController parent) {
		this.parentWindow = parent;
	} // end setParentWindow
	
	@FXML
	private void handleCancel (ActionEvent event) {
		close(event);
	} // end handleCancel
	
	@FXML
	private void handleConfirm (ActionEvent event) {
		this.parentWindow.confirmRemoval();
		close(event);
	} // end handleConfirm
	
	private void close (ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();		
	}
}
