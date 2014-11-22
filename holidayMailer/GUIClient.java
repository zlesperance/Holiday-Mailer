package holidayMailer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIClient extends Application {
	//private ContactFactory contactFactory;
	private static UserIn userIn;
	private static UserOut userOut;
	private static DBAccess dbAccess;
	private static Stage stage;

	@Override
	public void start(Stage stage) throws Exception {
		GUIClient.stage = stage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("mailerGUI.fxml"));
		
		Scene scene = new Scene(loader.load(), 600, 400);
		
		MailerGUIController controller = loader.<MailerGUIController>getController();
		
		controller.initDB(dbAccess);
		controller.initUserOut(userOut);
		controller.initUserIn(userIn);
		controller.refreshTable();
		
		stage.setTitle("Holiday Mailer");
		stage.setScene(scene);
		stage.show();
	} // end start

	public static void main(String[] args) {
		userOut = new UserOut();
		try {
			dbAccess = new DBAccess();
		} catch(Exception e) {
			userOut.printString("An Error Occurred when connecting to the database: " + e.getMessage());
			return;
		}
		
		launch(args);
	} // end main
	
	public static void close() {
		if (dbAccess != null) {
			try {
				dbAccess.close();
			} catch (Exception e) {
				userOut.printString("An Error Occurred when disconnecting from the database: " + e.getMessage());
			}
		}
		stage.close();
	} // end close
} // end GUIClient
