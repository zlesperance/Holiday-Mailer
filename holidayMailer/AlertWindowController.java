package holidayMailer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AlertWindowController implements Initializable {
	@FXML
	Label detailsLabel;
	
	@FXML
	Label messageLabel;
	
	@FXML
	Button okButton;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		detailsLabel.setText("");
	} // end initialize
	
	public void setMessage(String str) {
		messageLabel.setText(str);
	} // end setMessage
	
	public void setDetails(String str) {
		detailsLabel.setText(str);
	} // end setDetails
	
	@FXML
	private void handleOk (ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();		
	} // end handleOk

}
