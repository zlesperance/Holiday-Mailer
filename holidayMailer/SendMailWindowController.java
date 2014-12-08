package holidayMailer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SendMailWindowController implements Initializable {	
	@FXML
	Button cancelButton;
	
	@FXML
	TextArea messageTextBox;
	
	@FXML
	TextField subjectTextBox;
	
	@FXML
	TextField toTextBox;
	
	@FXML
	Button sendButton;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
	} // end initialize
	
	public void setToParam(ObservableList<Contact> contacts) {
		boolean start = true;
		String toString = "";
		for (Contact contact : contacts) {
			if (!start) {
				toString += ", ";
			} else {
				start = false;
			}
			toString += contact.toString();
		}
		
		toTextBox.setText(toString);
	} // end setToParam
	
	@FXML
	private void handleSend (ActionEvent event) {
		
	} // end handleSend
	
	@FXML
	private void handleCancel (ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();		
	} // end handleCancel

}
