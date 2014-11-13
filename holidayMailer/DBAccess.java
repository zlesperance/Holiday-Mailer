package holidayMailer;

import java.sql.*;

public class DBAccess {
	private Connection connection = null;
	
	
	public DBAccess () throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		
		this.open();
	}
	
	public void create(Contact contact) throws SQLException {
		this.open();
		String sql = "INSERT INTO contacts (email, firstName, lastName, lastReceivedDate) VALUES (?, ?, ?, ?);";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, contact.getAddr());
		statement.setString(2, contact.getFName());
		statement.setString(3, contact.getLName());
		statement.setDate(4, Date.valueOf(contact.getLastRec() + "-01-01"));
		statement.executeUpdate(); // Will throw a SQLException if contact already exists
	}
	
	public void update(Contact contact) throws SQLException {
		this.open();
		String sql = "UPDATE contacts SET firstName = ?, lastName = ?, lastReceivedDate = ? WHERE email = ?;";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, contact.getFName());
		statement.setString(2, contact.getLName());
		statement.setDate(3, Date.valueOf(contact.getLastRec() + "-01-01"));
		statement.setString(4, contact.getAddr());
		
		statement.executeUpdate();
	}
	
	public void updateWithKeyChange(Contact contact, String oldKey) throws SQLException {
		this.open();
		String sql = "UPDATE contacts SET email = ?, firstName = ?, lastName = ?, lastReceivedDate = ? WHERE email = ?;";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, contact.getAddr());
		statement.setString(2, contact.getFName());
		statement.setString(3, contact.getLName());
		statement.setDate(4, Date.valueOf(contact.getLastRec() + "-01-01"));
		statement.setString(5, oldKey);
		
		statement.executeUpdate();		
	}
	
	public void delete(Contact contact) throws SQLException {
		this.open();
		String sql = "DELETE FROM contacts WHERE email = ?;";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, contact.getAddr());
		
		statement.executeUpdate();
	}
	
	private void open() throws SQLException {
		if (connection != null) {
			return;
		}
		connection = DriverManager.getConnection("jdbc:sqlite:holidayMailer.db");
	}
	
	public void close() throws SQLException {
		if (connection != null) {
			this.connection.close();
		}
	}
}
