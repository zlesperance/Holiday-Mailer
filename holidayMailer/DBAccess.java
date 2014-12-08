package holidayMailer;

import java.sql.*;
import java.util.ArrayList;

public class DBAccess {
	private Connection connection = null;
	
	public DBAccess () throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		
		this.open();
	} // end constructor
	
	public void create(Contact contact) throws SQLException {
		this.open();
		String sql = "INSERT INTO contacts (email, firstName, lastName, lastReceivedYear) VALUES (?, ?, ?, ?);";
		
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setString(1, contact.getAddr());
		statement.setString(2, contact.getFName());
		statement.setString(3, contact.getLName());
		statement.setInt(4, contact.getLastRec());
		statement.executeUpdate(); // Will throw a SQLException if contact already exists
	} // end create
	
	public void update(Contact contact) throws SQLException {
		this.open();
		String sql = "UPDATE contacts SET firstName = ?, lastName = ?, lastReceivedYear = ? WHERE email = ?;";
		
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setString(1, contact.getFName());
		statement.setString(2, contact.getLName());
		statement.setInt(3, contact.getLastRec());
		statement.setString(4, contact.getAddr());
		
		statement.executeUpdate();
	} // end update
	
	public void updateWithKeyChange(Contact contact, String oldKey) throws SQLException {
		this.open();
		String sql = "UPDATE contacts SET email = ?, firstName = ?, lastName = ?, lastReceivedYear = ? WHERE email = ?;";
		
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setString(1, contact.getAddr());
		statement.setString(2, contact.getFName());
		statement.setString(3, contact.getLName());
		statement.setInt(4, contact.getLastRec());
		statement.setString(5, oldKey);
		
		statement.executeUpdate();		
	} // end updateWithKeyChange
	
	public void delete(Contact contact) throws SQLException {
		this.open();
		String sql = "DELETE FROM contacts WHERE email = ?;";
		
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setString(1, contact.getAddr());
		
		statement.executeUpdate();
	} // end delete
	
	public ArrayList<Contact> getAllContacts () throws SQLException {
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		
		String sql = "SELECT firstName, lastName, email, lastReceivedYear FROM contacts;";
		
		PreparedStatement statement = this.connection.prepareStatement(sql);
		ResultSet results = statement.executeQuery();
		
		while (results.next()) {
			contacts.add(new Contact(results.getString(1), results.getString(2), results.getString(3), results.getInt(4)));
		}
		
		return contacts;
	} // end getAllContacts
	
	public ArrayList<Contact> getPreviousSenders (int yearOffset) throws SQLException {
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		
		String sql = "SELECT"
				+ " firstName,"
				+ " lastName,"
				+ " email,"
				+ " lastReceivedYear"
				+ " FROM contacts"
				+ " WHERE lastReceivedYear IS NOT NULL"
				+ " AND lastReceivedYear > YEAR(CURDATE()) - ?;";
		
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setInt(1, yearOffset);
		ResultSet results = statement.executeQuery();
		
		while (results.next()) {
			contacts.add(new Contact(results.getString(1), results.getString(2), results.getString(3), results.getInt(4)));
		}
		
		return contacts;
	} // end getPreviousSenders
	
	public ArrayList<Contact> getPreviousSenders () throws SQLException {
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		
		String sql = "SELECT"
				+ " firstName,"
				+ " lastName,"
				+ " email,"
				+ " lastReceivedYear"
				+ " FROM contacts"
				+ " WHERE lastReceivedYear IS NOT NULL;";
		
		PreparedStatement statement = this.connection.prepareStatement(sql);
		ResultSet results = statement.executeQuery();
		
		while (results.next()) {
			contacts.add(new Contact(results.getString(1), results.getString(2), results.getString(3), results.getInt(4)));
		}
		
		return contacts;
	} // end getPreviousSenders
	
	public ArrayList<Contact> getContactsByName (String firstName, String lastName) throws SQLException {
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		
		String sql = "SELECT"
				+ " firstName,"
				+ " lastName,"
				+ " email,"
				+ " lastReceivedYear"
				+ " FROM contacts"
				+ " WHERE firstName = ?"
				+ " AND lastName = ?;";
		
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setString(1, firstName);
		statement.setString(2, lastName);
		ResultSet results = statement.executeQuery();
		
		while (results.next()) {
			contacts.add(new Contact(results.getString(1), results.getString(2), results.getString(3), results.getInt(4)));
		}
		
		return contacts;
	} // end getContactsByName
	
	public ArrayList<Contact> getContactsByFirstName (String firstName) throws SQLException {
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		
		String sql = "SELECT"
				+ " firstName,"
				+ " lastName,"
				+ " email,"
				+ " lastReceivedYear"
				+ " FROM contacts"
				+ " WHERE firstName = ?;";
		
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setString(1, firstName);
		ResultSet results = statement.executeQuery();
		
		while (results.next()) {
			contacts.add(new Contact(results.getString(1), results.getString(2), results.getString(3), results.getInt(4)));
		}
		
		return contacts;
	} // end getContactsByFirstName
	
	public ArrayList<Contact> getContactsByLastName (String lastName) throws SQLException {
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		
		String sql = "SELECT"
				+ " firstName,"
				+ " lastName,"
				+ " email,"
				+ " lastReceivedYear"
				+ " FROM contacts"
				+ " WHERE lastName = ?;";
		
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setString(1, lastName);
		ResultSet results = statement.executeQuery();
		
		while (results.next()) {
			contacts.add(new Contact(results.getString(1), results.getString(2), results.getString(3), results.getInt(4)));
		}
		
		return contacts;
	} // end getContactsByLastName
	
	private void open() throws SQLException {
		if (this.connection != null) {
			return;
		}
		this.connection = DriverManager.getConnection("jdbc:sqlite:holidayMailer.db");
	} // end open
	
	public void close() throws SQLException {
		if (this.connection != null) {
			this.connection.close();
		}
	} // end close
} // end DBAccess
