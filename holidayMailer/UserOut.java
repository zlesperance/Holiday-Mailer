package holidayMailer;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UserOut implements iUserOut {
	private Stage alertWindow;
	
    public void printString(String msg){
        System.out.println(msg);
    } // end printString

    public void printLName(String name){
        System.out.println(name);
    } // end printLName

    public void printFName(String name){
        System.out.println(name);
    } // end printFName

    public void printEMail(String email){
        System.out.println(email);
    } // end printEMail

    public void printLastRec(int date){
        System.out.println(date);
    } // end printLastRec
    
    public void printError(String msg, String details) {
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("alertWindow.fxml"));
			root = loader.load();
			
			// Don't show another alert window if one is already showing
			if (this.alertWindow != null && this.alertWindow.isShowing()) {
				return;
			}
			AlertWindowController controller = loader.getController();
			controller.setMessage(msg);
			controller.setDetails(details);
			
			this.alertWindow = new Stage();
			this.alertWindow.initModality(Modality.WINDOW_MODAL);
			this.alertWindow.setTitle("Error");
			this.alertWindow.setScene(new Scene(root));
			this.alertWindow.show();
		} catch (IOException e) {
			System.out.println("An Error occurred when opening the new window: " + e.getMessage());
		}
    } // end printError
    
    public void printError(String msg) {
    	printError(msg, "");
    } // end printError
}
