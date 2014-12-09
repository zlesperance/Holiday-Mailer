package holidayMailer;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GUIClient extends Application {
	//private ContactFactory contactFactory;
	private static UserIn userIn;
	private static UserOut userOut;
	private static DBAccess dbAccess;
	

	@Override
	public void start(Stage stage) throws Exception {		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("mailerGUI.fxml"));
		
		Scene scene = new Scene(loader.<Parent>load());
		
		MailerGUIController controller = loader.getController();
		
		controller.initDB(dbAccess);
		controller.initUserOut(userOut);
		controller.initUserIn(userIn);
		controller.refreshTable();
		
		stage.setTitle("Holiday Mailer");
		stage.setScene(scene);
		
		stage.onCloseRequestProperty().set(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				// TODO Auto-generated method stub
				controller.handleQuitAction(event);
			}
		});

		stage.show();
	} // end start

	public static void main(String[] args) {
		userOut = new UserOut();
		try {
			dbAccess = new DBAccess();
		} catch(Exception e) {
			userOut.printError("An Error Occurred when connecting to the database: " + e.getMessage());
			return;
		}
		
		launch(args);
	} // end main
} // end GUIClient
