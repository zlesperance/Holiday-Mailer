package holidayMailer;

import java.sql.SQLException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
		
		Scene scene = new Scene(loader.load());
		
		MailerGUIController controller = loader.getController();
		
		controller.initDB(dbAccess);
		controller.initUserOut(userOut);
		controller.initUserIn(userIn);
		controller.refreshTable();
		
		stage.setTitle("Holiday Mailer");
		stage.setScene(scene);
		stage.show();
		stage.setOnCloseRequest(new EventHandler<WindowEvent> () {
			public void handle(WindowEvent e) {
				if (dbAccess != null) {
					try {
						dbAccess.close();
					} catch (SQLException ex) {
						userOut.printString(ex.getMessage());
					}
				}
			}
		});
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
} // end GUIClient
