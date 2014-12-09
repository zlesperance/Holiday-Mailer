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
		Contact tester = new Contact("Test", "Contact","tester@eagles.ewu.edu", 2013);
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
		Contact tester2 = new Contact("Steven", "Mather","smather@eagles.ewu.edu", 2013);
		try {
			db.create(tester2);
		} catch (SQLException e) {
			fail("Error When Creating User");
			try {
				db.close();
			} catch (SQLException e2) {
				fail("Could Not Close Database");
			}
		}
		///
		Contact tester3 = new Contact("Steven2", "Mather2","lord_of_fudge@hotmail.com", 2000);
		try {
			db.create(tester3);
		} catch (SQLException e) {
			fail("Error When Creating User");
			try {
				db.close();
			} catch (SQLException e2) {
				fail("Could Not Close Database");
			}
		}
		///
		Contact tester4 = new Contact( "Steven3", "Mather3","lordfoodge@gmail.com", 2005);
		try {
			db.create(tester4);
		} catch (SQLException e) {
			fail("Error When Creating User");
			try {
				db.close();
			} catch (SQLException e2) {
				fail("Could Not Close Database");
			}
		}
		try{
			SendMail.sendAll(db);
		}catch (RuntimeException e7){
			try {
				db.delete(tester);
			} catch (SQLException e) {
				fail("Error When Deleting User");
			} 
			try {
				db.delete(tester2);
			} catch (SQLException e) {
				fail("Error When Deleting User");
			} 
			try {
				db.delete(tester3);
			} catch (SQLException e) {
				fail("Error When Deleting User");
			} 
			try {
				db.delete(tester4);
			} catch (SQLException e) {
				fail("Error When Deleting User");
			}
			fail(e7.getLocalizedMessage());
		}
		try{
			SendMail.sendByFirstName(db,"Steven");
		} catch (RuntimeException e2){
			try {
				db.delete(tester);
			} catch (SQLException e) {
				fail("Error When Deleting User");
			} 
			try {
				db.delete(tester2);
			} catch (SQLException e) {
				fail("Error When Deleting User");
			} 
			try {
				db.delete(tester3);
			} catch (SQLException e) {
				fail("Error When Deleting User");
			} 
			try {
				db.delete(tester4);
			} catch (SQLException e) {
				fail("Error When Deleting User");
			}
			fail(e2.getLocalizedMessage());
		}
		try{
			SendMail.sendByLastName("Mather2");
		}catch (RuntimeException e3){
			try {
				db.delete(tester);
			} catch (SQLException e) {
				fail("Error When Deleting User");
			} 
			try {
				db.delete(tester2);
			} catch (SQLException e) {
				fail("Error When Deleting User");
			} 
			try {
				db.delete(tester3);
			} catch (SQLException e) {
				fail("Error When Deleting User");
			} 
			try {
				db.delete(tester4);
			} catch (SQLException e) {
				fail("Error When Deleting User");
			}
			fail(e3.getLocalizedMessage());
		}
		try{
			SendMail.sendByName("Steven3","Mather3");
		}catch (RuntimeException e4){
			try {
				db.delete(tester);
			} catch (SQLException e) {
				fail("Error When Deleting User");
			} 
			try {
				db.delete(tester2);
			} catch (SQLException e) {
				fail("Error When Deleting User");
			} 
			try {
				db.delete(tester3);
			} catch (SQLException e) {
				fail("Error When Deleting User");
			} 
			try {
				db.delete(tester4);
			} catch (SQLException e) {
				fail("Error When Deleting User");
			}
			fail(e4.getLocalizedMessage());
		}
		try{
			SendMail.sendByPreviouslyRecived();
		}catch (RuntimeException e5){
			try {
				db.delete(tester);
			} catch (SQLException e) {
				fail("Error When Deleting User"+ e5.getLocalizedMessage());
			} 
			try {
				db.delete(tester2);
			} catch (SQLException e) {
				fail("Error When Deleting User");
			} 
			try {
				db.delete(tester3);
			} catch (SQLException e) {
				fail("Error When Deleting User");
			} 
			try {
				db.delete(tester4);
			} catch (SQLException e) {
				fail("Error When Deleting User");
			}
			fail(e5.getLocalizedMessage());
		}
		try{
			SendMail.sendByPreviouslyRecivedOffset(7);
		}catch (RuntimeException e6){
			try {
				db.delete(tester);
			} catch (SQLException e) {
				fail("Error When Deleting User" + e6.getLocalizedMessage());
			} 
			try {
				db.delete(tester2);
			} catch (SQLException e) {
				fail("Error When Deleting User");
			} 
			try {
				db.delete(tester3);
			} catch (SQLException e) {
				fail("Error When Deleting User");
			} 
			try {
				db.delete(tester4);
			} catch (SQLException e) {
				fail("Error When Deleting User");
			}
			fail(e6.getLocalizedMessage());
		}
		
		try {
			db = new DBAccess();
		} catch (SQLException e) {
			fail("Error When connecting to the database");
			return;
		} catch (ClassNotFoundException e) {
			fail("SQLite Driver Class Not Found");
		}
		
		try {
			db.delete(tester);
		} catch (SQLException e) {
			fail("Error When Deleting User"+ e.getLocalizedMessage());
		} 
		try {
			db.delete(tester2);
		} catch (SQLException e) {
			fail("Error When Deleting User");
		} 
		try {
			db.delete(tester3);
		} catch (SQLException e) {
			fail("Error When Deleting User");
		} 
		try {
			db.delete(tester4);
		} catch (SQLException e) {
			fail("Error When Deleting User");
		}
		try{
			db.close();
		}
		catch (SQLException e){
			fail("Error When closing");
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
