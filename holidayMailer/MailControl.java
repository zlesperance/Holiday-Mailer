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
			this.dba = new DBAccess ();
			this.contactList = dba.getAllContacts();
		} catch (SQLException e) {
			this.uo.printError("An error occured querying the database.");
		} catch (Exception e) {
			this.uo.printError ("Error connecting to the database");
		}	
		
	}//Constructor
	
	public ArrayList<Contact> getContacts() {
		
		return this.contactList;
	
	}//getContacts	
		
	public void commitChanges(boolean close) throws SQLException {
	
		updateContactInDB();
		addContactToDB();
		removeContactFromDB();	
		
		if (close) {
			closeDB();
		}
				
	}//commitChanges	
	
	
	public void editContact(Contact con) {
		
		//TODO: Add ability to update contact (if needed in final product...yagni?)
		
	}//editContact
	
	public boolean addContact(Contact con) {
		
		boolean canBeAdded = true;
		if(this.addedContacts == null) {
			this.addedContacts = new ArrayList<Contact> ();
		}
				
		for(Contact c : contactList) {
			
			if (c.equals(con)) {
				
				uo.printError("Contact already in database.");
				canBeAdded = false;				
			}//if c==con
			
		}//for contacts in cantoctList
		
		if (canBeAdded) {
			this.contactList.add(con);
			this.addedContacts.add(con);
		}//if canBeAdded
		
		return canBeAdded;
		
	}//addContact
	
	public void deleteContact(Contact con) {
		
		if (this.removedContacts == null) {
			this.removedContacts = new ArrayList<Contact> ();
		}
		
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
	
	//-----Private helper methods
	private void addContactToDB() {
		
		if (this.addedContacts != null) {
			for (Contact contact : addedContacts) {
				try {
					dba.create(contact);
				} catch (SQLException e) {
					uo.printError("An error occured creating a new contact in the database.");
					
				}//try/catch
				
			}//for contact in updated
		}//if addContacts != null
		
	}//addContactToDB
	
	private void removeContactFromDB() {
		
		if (this.removedContacts != null) { 
			for (Contact contact : removedContacts) {
				try {
					dba.delete(contact);
				} catch (SQLException e) {
					uo.printError("An error occured deleting a contact from the database.");
				}
			}//for contact in updated
		}
	}//removeContactFromDB

	private void updateContactInDB() {
		
		if (this.updatedContacts != null) {
			for (Contact contact : updatedContacts) {
				try {
					dba.update(contact);
					
				} catch (SQLException e) {
					uo.printError("An error occured updating the database");
					
				}//try/catch
				
			}//for contact in updated
			
		}//if updatedContact != null
		
	}//updateContactInDB	
	
	private void closeDB() {
		
		try {
			this.dba.close();
		} catch (SQLException e) {
			this.uo.printError("An Error Occurred while closing the database: " + e.getMessage());
		}//try/catch
		
	}//close db
	
		
}//class
