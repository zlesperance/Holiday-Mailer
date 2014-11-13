package holidayMailer;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class DBAccessTest {

	@Test
	public void crudTest() {
		DBAccess db = null;
		try {
			db = new DBAccess();
		} catch (SQLException e) {
			fail("Error When connecting to the database");
			return;
		} catch (ClassNotFoundException e) {
			fail("SQLite Driver Class Not Found");
		}
		
		Contact tester = new Contact("tester@eagles.ewu.edu", "Test", "Contact", 2013);
		try {
			db.create(tester);
		} catch (SQLException e) {
			fail("Error When Creating User");
			try {
				db.close();
			} catch (SQLException e2) {
				fail("Could Not Close Database");
			}
		}
		
		try {
			db.delete(tester);
		} catch (SQLException e) {
			fail("Error When Deleting User");
		} finally {
			try {
				db.close();
			} catch (SQLException e) {
				fail("Could Not Close Database");
			}
		}
	}

}
