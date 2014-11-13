package holidayMailer;

import java.sql.*;

public class DBAccess {
	private Connection connection = null;
	
	public DBAccess () throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		
		this.open();
	} // end constructor
	
	public void create(Contact contact) throws SQLException {
		this.open();
		String sql = "INSERT INTO contacts (email, firstName, lastName, lastReceivedDate) VALUES (?, ?, ?, ?);";
		
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setString(1, contact.getAddr());
		statement.setString(2, contact.getFName());
		statement.setString(3, contact.getLName());
		statement.setDate(4, Date.valueOf(contact.getLastRec() + "-01-01"));
		statement.executeUpdate(); // Will throw a SQLException if contact already exists
	} // end create
	
	public void update(Contact contact) throws SQLException {
		this.open();
		String sql = "UPDATE contacts SET firstName = ?, lastName = ?, lastReceivedDate = ? WHERE email = ?;";
		
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setString(1, contact.getFName());
		statement.setString(2, contact.getLName());
		statement.setDate(3, Date.valueOf(contact.getLastRec() + "-01-01"));
		statement.setString(4, contact.getAddr());
		
		statement.executeUpdate();
	} // end update
	
	public void updateWithKeyChange(Contact contact, String oldKey) throws SQLException {
		this.open();
		String sql = "UPDATE contacts SET email = ?, firstName = ?, lastName = ?, lastReceivedDate = ? WHERE email = ?;";
		
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setString(1, contact.getAddr());
		statement.setString(2, contact.getFName());
		statement.setString(3, contact.getLName());
		statement.setDate(4, Date.valueOf(contact.getLastRec() + "-01-01"));
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
