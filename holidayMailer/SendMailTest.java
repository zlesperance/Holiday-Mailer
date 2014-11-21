package holidayMailer;

import static org.junit.Assert.*;

import org.junit.Test;

import java.sql.SQLException;


public class SendMailTest {

	@Test
	public void test() {
		DBAccess db = null;
		try {
			db = new DBAccess();
		} catch (SQLException e) {
			fail("Error When connecting to the database");
			return;
		} catch (ClassNotFoundException e) {
			fail("SQLite Driver Class Not Found");
		}
		///
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
		///
		tester = new Contact("smather@eagles.ewu.edu", "Steven", "Mather", 2013);
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
		///
		tester = new Contact("lord_of_fudge@hotmail.com", "Steven2", "Mather2", 2000);
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
		///
		tester = new Contact("lordfoodge@gmail.com", "Steven3", "Mather4", 2005);
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
		try{
			SendMail.sendByFirstName("Steven");
		} catch (RuntimeException e2){
			fail("Failed to send.");
		}
		try{
			SendMail.sendByLastName("Mather2");
		}catch (RuntimeException e3){
			fail("Failed to send.");
		}
		try{
			SendMail.sendByName("Steven3","Mather3");
		}catch (RuntimeException e4){
			fail("Failed to send.");
		}
		try{
			SendMail.sendByPreviouslyRecived();
		}catch (RuntimeException e5){
			fail("Failed to send.");
		}
		try{
			SendMail.sendByPreviouslyRecivedOffset(7);
		}catch (RuntimeException e6){
			fail("Failed to send.");
		}
		try{
			SendMail.sendAll();
		}catch (RuntimeException e7){
			fail("Failed to send.");
		}
		
	}

}
/*
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

}*/
