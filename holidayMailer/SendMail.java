package holidayMailer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class SendMail{
 
	public static void Send(String addressTo, String subject,String body) {
 
		final String username = "moustachedmuchachos@gmail.com";
		final String password = "cscd350password";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
		      return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(addressTo));
			message.setSubject(subject);
			message.setText(body);
 
			Transport.send(message);
 
			System.out.println("Done");//for testing only!!!!!!!!!!!!!!!!!!
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void Send(String addressFrom, String pass, String addressTo, String subject,String body) {
		 
		final String username = "addressFrom";
		final String password = "pass";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
		      return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(addressTo));
			message.setSubject(subject);
			message.setText(body);
 
			Transport.send(message);
 
			System.out.println("Done");//for testing only!!!!!!!!!!!!!!!!!!
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	//given a first name will search through the database and send to that name
	   public static void sendByFirstName(String name)throws RuntimeException{
	      DBAccess db = null;
			try {
				db = new DBAccess();
			} catch (SQLException e) {
				System.out.println("Error When connecting to the database");
				return;
			} catch (ClassNotFoundException e) {
				System.out.println("SQLite Driver Class Not Found");
			}
	      //get people 
			
	      ArrayList<Contact> contacts = new ArrayList<Contact>();
	      try{
			   contacts = db.getContactsByFirstName (name);
	      }
	      catch (SQLException e2) {
					throw new RuntimeException("could not send by first name");
				}
		   //send 
	      int x= 0;
	      while (x < contacts.size()) {
	             Contact cur = contacts.get(x);
	             SendMail.Send(cur.getAddr(),"Moustache Muchachos Test","This is a test using the gmail to send to the yahoo account.");
	        }
	      try {
					db.close();
				} catch (SQLException e2) {
					System.out.println("Could Not Close Database");
				}
	   }//end sendByFirstName
	   
	 //given a last name will search through the database and send to that name
	   public static void sendByLastName(String name){
	      DBAccess db = null;
			try {
				db = new DBAccess();
			} catch (SQLException e) {
				System.out.println("Error When connecting to the database");
				return;
			} catch (ClassNotFoundException e) {
				System.out.println("SQLite Driver Class Not Found");
			}
	      //get people 
			
	      ArrayList<Contact> contacts = new ArrayList<Contact>();
	      try{
			   contacts = db.getContactsByLastName (name);
	      }
	      catch (SQLException e2) {
	    	  throw new RuntimeException("could not send by last name");
				}
		   //send 
	      int x= 0;
	      while (x < contacts.size()) {
	             Contact cur = contacts.get(x);
	             SendMail.Send(cur.getAddr(),"Moustache Muchachos Test","This is a test using the gmail to send to the yahoo account.");
	        }
	      try {
					db.close();
				} catch (SQLException e2) {
					System.out.println("Could Not Close Database");
				}
	   }//end sendByLastName
	   
	 //given a first name and last name will search through the database and send to that name
	   public static void sendByName(String nameF,String nameL){
	      DBAccess db = null;
			try {
				db = new DBAccess();
			} catch (SQLException e) {
				System.out.println("Error When connecting to the database");
				return;
			} catch (ClassNotFoundException e) {
				System.out.println("SQLite Driver Class Not Found");
			}
	      //get people 
			
	      ArrayList<Contact> contacts = new ArrayList<Contact>();
	      try{
			   contacts = db.getContactsByName (nameF,nameL);
	      }
	      catch (SQLException e2) {
	    	  throw new RuntimeException("could not send by full name");
				}
		   //send 
	      int x= 0;
	      while (x < contacts.size()) {
	             Contact cur = contacts.get(x);
	             SendMail.Send(cur.getAddr(),"Moustache Muchachos Test","This is a test using the gmail to send to the yahoo account.");
	        }
	      try {
					db.close();
				} catch (SQLException e2) {
					System.out.println("Could Not Close Database");
				}
	   }//end sendByName
	   
	 //gets all previously received from
	   public static void sendByPreviouslyRecived(){
	      DBAccess db = null;
			try {
				db = new DBAccess();
			} catch (SQLException e) {
				System.out.println("Error When connecting to the database");
				return;
			} catch (ClassNotFoundException e) {
				System.out.println("SQLite Driver Class Not Found");
			}
	      //get people 
			
	      ArrayList<Contact> contacts = new ArrayList<Contact>();
	      try{
			   contacts = db.getPreviousSenders ();
	      }
	      catch (SQLException e2) {
	    	  throw new RuntimeException("could not send by previously recived");
				}
		   //send 
	      int x= 0;
	      while (x < contacts.size()) {
	             Contact cur = contacts.get(x);
	             SendMail.Send(cur.getAddr(),"Moustache Muchachos Test","This is a test using the gmail to send to the yahoo account.");
	        }
	      try {
					db.close();
				} catch (SQLException e2) {
					System.out.println("Could Not Close Database");
				}
	   }//end sendByPreviouslyRecived
	   
	 //gets all previously received from with years offset
	   public static void sendByPreviouslyRecivedOffset(int yearOffset){
	      DBAccess db = null;
			try {
				db = new DBAccess();
			} catch (SQLException e) {
				System.out.println("Error When connecting to the database");
				return;
			} catch (ClassNotFoundException e) {
				System.out.println("SQLite Driver Class Not Found");
			}
	      //get people 
			
	      ArrayList<Contact> contacts = new ArrayList<Contact>();
	      try{
			   contacts = db.getPreviousSenders (yearOffset);
	      }
	      catch (SQLException e2) {
	    	  throw new RuntimeException("could not send by previously recived offset");
				}
		   //send 
	      int x= 0;
	      while (x < contacts.size()) {
	             Contact cur = contacts.get(x);
	             SendMail.Send(cur.getAddr(),"Moustache Muchachos Test","This is a test using the gmail to send to the yahoo account.");
	        }
	      try {
					db.close();
				} catch (SQLException e2) {
					System.out.println("Could Not Close Database");
				}
	   }//end sendByPreviouslyRecivedOffset
	   
	   public static void sendAll(){
		      DBAccess db = null;
				try {
					db = new DBAccess();
				} catch (SQLException e) {
					System.out.println("Error When connecting to the database");
					return;
				} catch (ClassNotFoundException e) {
					System.out.println("SQLite Driver Class Not Found");
				}
		      //get people 
				
		      ArrayList<Contact> all = new ArrayList<Contact>();
		      try{
				   all = db.getAllContacts ();
		      }
		      catch (SQLException e2) {
		    	  throw new RuntimeException("could not send all");
					}
			   //send too all
		      int x= 0;
		      while (x < all.size()) {
		             Contact cur = all.get(x);
		             SendMail.Send(cur.getAddr(),"Moustache Muchachos Test","This is a test using the gmail to send to the yahoo account.");
		        }
		      try {
						db.close();
					} catch (SQLException e2) {
						System.out.println("Could Not Close Database");
					}
		   }//end sendAll

}