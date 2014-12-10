package holidayMailer;

import java.sql.SQLException;
import java.util.*;

public class MailControl {

	private UserOut uo;
	private ArrayList<Contact> contactList;
	private ArrayList<Contact> updatedContacts;
	private ArrayList<Contact> addedContacts;
	private ArrayList<Contact> removedContacts;
	private DBAccess dba;
	
	public MailControl (UserOut uo) throws SQLException {
				
		this.uo = uo;
		
		try {
			this.dba = new DBAccess();
			this.contactList = dba.getAllContacts();
		} catch (SQLException e) {
			this.uo.printError("An error occured querying the database.");
		} catch(Exception e) {
			this.uo.printError("An Error Occurred when connecting to the database: " + e.getMessage());
			return;
		}//try/catch/catch
				
	}//Constructor
	
	public ArrayList<Contact> getContacts() {
		
		return this.contactList;
	
	}//getContacts	
	
	public void commitChanges() throws SQLException {
		
		updateContactInDB();
		addContactToDB();
		removeContactFromDB();
				
	}//commitChanges
	
	private void addContactToDB() {
		
		for (Contact contact : addedContacts) {
			try {
				dba.create(contact);
			} catch (SQLException e) {
				uo.printError("An error occured creating a new contact in the database.");
				
			}//try/catch
			
		}//for contact in updated
		
	}//addContactToDB
	
	private void removeContactFromDB() {
		
		for (Contact contact : removedContacts) {
			try {
				dba.delete(contact);
			} catch (SQLException e) {
				uo.printError("An error occured deleting a contact from the database.");
			}
		}//for contact in updated
		
	}//removeContactFromDB

	private void updateContactInDB() {
		
		for (Contact contact : updatedContacts) {
			try {
				dba.update(contact);
				
			} catch (SQLException e) {
				uo.printError("An error occured updating the database");
				
			}//try/catch
			
		}//for contact in updated
		
	}//updateContactInDB	
	
	public void editContact(Contact con) {
		
		//TODO: Add ability to update contact (if needed in final product...yagni?)
		
	}//editContact
	
	public void addContact(Contact con) {
		
		boolean exists = false;
		for(Contact c : contactList) {
			
			if (c.equals(con)) {
				uo.printError("Contact already in database.");
				exists = true;
				break;
			}//if c==con
			
		}//for contacts in cantoctList
		
		if (!exists){
			
			this.contactList.add(con);
			this.addedContacts.add(con);
			
		}//if not in database
		
	}//addContact
	
	public void deleteContact(Contact con) {
		
		boolean exists = false;
		for(Contact c : contactList) {
			
			if (c.equals(con)) {
				
				contactList.remove(con);
				removedContacts.add(con);
				exists = true;
				break;
			}//if c==con
			
		}//for contacts in cantoctList
		
		if (!exists){
			
			uo.printError("Contact not in database");
			
		}//if not in database		
		
	}//deleteContact
		
}//class
