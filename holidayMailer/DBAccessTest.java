package holidayMailer;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

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
		
		Contact tester = new Contact("Test", "Contact", "tester@eagles.ewu.edu", 2013);
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
	
	@Test
	public void QueryAllTest() {
		DBAccess db = null;
		try {
			db = new DBAccess();
		} catch (SQLException e) {
			fail("Error When connecting to the database");
			return;
		} catch (ClassNotFoundException e) {
			fail("SQLite Driver Class Not Found");
			return;
		}
		
		try {
			db.getAllContacts();
		} catch (SQLException e) {
			fail("SQL Error: " + e.getMessage());
		}
	}
	
	@Test
	public void QuerySpecificTest() {
		DBAccess db = null;
		try {
			db = new DBAccess();
		} catch (SQLException e) {
			fail("Error When connecting to the database");
			return;
		} catch (ClassNotFoundException e) {
			fail("SQLite Driver Class Not Found");
			return;
		}

		Contact tester = new Contact("Test", "Contact", "tester@eagles.ewu.edu", 2013);
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
		
		ArrayList<Contact> results = null;
		try {
			results = db.getContactsByFirstName("Test");
		} catch (SQLException e) {
			fail("Error When Querying: " + e.getMessage());
			try {
				db.close();
			} catch (SQLException e2) {
				fail("Could Not Close Database");
			}
			return;
		}
		assertTrue(results.size() > 0);
		Contact result = results.get(0);
		assertTrue(result.getFName().compareTo(tester.getFName()) == 0);
		assertTrue(result.getLName().compareTo(tester.getLName()) == 0);
		assertTrue(result.getAddr().compareTo(tester.getAddr()) == 0);
		assertTrue(result.getLastRec() == tester.getLastRec());
		
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
