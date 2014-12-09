package holidayMailer;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import javax.activation.*;
import javax.sql.DataSource;
 
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
 
			System.out.println("Done sent to "+ addressTo);//for testing only!!!!!!!!!!!!!!!!!!
 
		} catch (MessagingException e) {
			System.out.println("failed to sent to "+ addressTo);
			//throw new RuntimeException(e);
		}
	}
	
	public static void Send(String addressTo, String subject,String body,File fileAttachment) {
		 
		final String username = "moustachedmuchachosdose@gmail.com";
		final String password = "cscd350password2";
 
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
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(addressTo));
			message.setSubject(subject);

			MimeBodyPart messageBodyPart = new MimeBodyPart();
	
		    //fill message
		    messageBodyPart.setText(body);
	
		    Multipart multipart = new MimeMultipart();
		    multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();
		    DataSource source =  (DataSource) fileAttachment;
		    messageBodyPart.setDataHandler(new DataHandler((javax.activation.DataSource) source));
		    messageBodyPart.setFileName(fileAttachment.getName());
		    multipart.addBodyPart(messageBodyPart);

		    // Put parts in message
		    message.setContent(multipart);
 
			Transport.send(message);
 
			System.out.println("Done sent to "+ addressTo);//for testing only!!!!!!!!!!!!!!!!!!
 
		} catch (MessagingException e) {
			System.out.println("failed to sent to "+ addressTo);
			//throw new RuntimeException(e);
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
 
			System.out.println("Done sent to "+ addressTo);//for testing only!!!!!!!!!!!!!!!!!!
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void Send(String addressFrom, String pass, String addressTo, String subject,String body,File fileAttachment) {
		 
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
			
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			
		    //fill message
		    messageBodyPart.setText(body);
	
		    Multipart multipart = new MimeMultipart();
		    multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();
		    DataSource source = (DataSource) fileAttachment;
		    messageBodyPart.setDataHandler(new DataHandler((javax.activation.DataSource) source));
		    messageBodyPart.setFileName(fileAttachment.getName());
		    multipart.addBodyPart(messageBodyPart);

		    // Put parts in message
		    message.setContent(multipart);
 
			Transport.send(message);
 
			System.out.println("Done sent to "+ addressTo);//for testing only!!!!!!!!!!!!!!!!!!
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	//given a first name will search through the database and send to that name
	   public static void sendByFirstName(DBAccess db,String name)throws RuntimeException{
	      /*
			try {
				db = new DBAccess();
			} catch (SQLException e) {
				System.out.println("Error When connecting to the database");
				return;
			} catch (ClassNotFoundException e) {
				System.out.println("SQLite Driver Class Not Found");
			}
			*/
	      //get people 
			
	      ArrayList<Contact> contacts = new ArrayList<Contact>();
	      try{
			   contacts = db.getContactsByFirstName (name);
	      }
	      catch (SQLException e2) {
					//throw new RuntimeException("could not send by first name:"+name+":"+ contacts.size());
	    	  throw new RuntimeException(e2);
				}
		   //send 
	      int x= 0;
	      while (x < contacts.size()) {
	             Contact cur = contacts.get(x);
	             SendMail.Send(cur.getAddr(),"HolidayMail", "This message comes to you from the Mustache Muchacho Holiday Mailer, Greatings and happy holidays "+ cur.getFName());
	             x++;
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
	             SendMail.Send(cur.getAddr(),"HolidayMail", "This message comes to you from the Mustache Muchacho Holiday Mailer, Greatings and happy holidays "+ cur.getFName());
	             x++;
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
	             SendMail.Send(cur.getAddr(),"HolidayMail", "This message comes to you from the Mustache Muchacho Holiday Mailer, Greatings and happy holidays "+ cur.getFName());
	             x++;
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
			   contacts = db.getPreviousSenders();
	      }
	      catch (SQLException e2) {
	    	  throw new RuntimeException(e2.getLocalizedMessage()+" could not send by previously recived");
				}
		   //send 
	      int x= 0;
	      while (x < contacts.size()) {
	             Contact cur = contacts.get(x);
	             SendMail.Send(cur.getAddr(),"HolidayMail", "This message comes to you from the Mustache Muchacho Holiday Mailer, Greatings and happy holidays "+ cur.getFName());
	             x++;
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
	    	  throw new RuntimeException("could not send by previously recived offset"+ e2.getLocalizedMessage());
				}
		   //send 
	      int x= 0;
	      while (x < contacts.size()) {
	             Contact cur = contacts.get(x);
	             SendMail.Send(cur.getAddr(),"HolidayMail", "This message comes to you from the Mustache Muchacho Holiday Mailer, Greatings and happy holidays "+ cur.getFName());
	        
	             x++;
	      }
	      try {
					db.close();
				} catch (SQLException e2) {
					System.out.println("Could Not Close Database");
				}
	   }//end sendByPreviouslyRecivedOffset
	   
	   public static void sendAll(DBAccess db){
		      //DBAccess db = null;
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
		    	  throw new RuntimeException(e2);
					}
			   //send too all
		      int x= 0;
		      while (x < all.size()) {
		             Contact cur = all.get(x);
		             SendMail.Send(cur.getAddr(),"HolidayMail", "This message comes to you from the Mustache Muchacho Holiday Mailer, Greatings and happy holidays "+ cur.getFName());
		             x++;
		      }
		      try {
						db.close();
					} catch (SQLException e2) {
						System.out.println("Could Not Close Database");
					}
		   }//end sendAll
	   
	   //////////////////////////////////////////////////send with custom subject and body
	   
	 //given a first name will search through the database and send to that name
	   public static void sendByFirstName(DBAccess db,String name,String subject,String body)throws RuntimeException{
	      /*
			try {
				db = new DBAccess();
			} catch (SQLException e) {
				System.out.println("Error When connecting to the database");
				return;
			} catch (ClassNotFoundException e) {
				System.out.println("SQLite Driver Class Not Found");
			}
			*/
	      //get people 
			
	      ArrayList<Contact> contacts = new ArrayList<Contact>();
	      try{
			   contacts = db.getContactsByFirstName (name);
	      }
	      catch (SQLException e2) {
					//throw new RuntimeException("could not send by first name:"+name+":"+ contacts.size());
	    	  throw new RuntimeException(e2);
				}
		   //send 
	      int x= 0;
	      while (x < contacts.size()) {
	             Contact cur = contacts.get(x);
	             SendMail.Send(cur.getAddr(),subject, body);
	             x++;
	      }
	      try {
					db.close();
				} catch (SQLException e2) {
					System.out.println("Could Not Close Database");
				}
	   }//end sendByFirstName
	   
	 //given a last name will search through the database and send to that name
	   public static void sendByLastName(String name,String subject,String body){
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
	             SendMail.Send(cur.getAddr(),subject, body);
	             x++;
	      }
	      try {
					db.close();
				} catch (SQLException e2) {
					System.out.println("Could Not Close Database");
				}
	   }//end sendByLastName
	   
	 //given a first name and last name will search through the database and send to that name
	   public static void sendByName(String nameF,String nameL,String subject, String body){
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
	             SendMail.Send(cur.getAddr(),subject,body);
	             x++;
	      }
	      try {
					db.close();
				} catch (SQLException e2) {
					System.out.println("Could Not Close Database");
				}
	   }//end sendByName
	   
	 //gets all previously received from
	   public static void sendByPreviouslyRecived(String subject,String body){
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
			   contacts = db.getPreviousSenders();
	      }
	      catch (SQLException e2) {
	    	  throw new RuntimeException(e2.getLocalizedMessage()+" could not send by previously recived");
				}
		   //send 
	      int x= 0;
	      while (x < contacts.size()) {
	             Contact cur = contacts.get(x);
	             SendMail.Send(cur.getAddr(),subject,body);
	             x++;
	      }
	      try {
					db.close();
				} catch (SQLException e2) {
					System.out.println("Could Not Close Database");
				}
	   }//end sendByPreviouslyRecived
	   
	 //gets all previously received from with years offset
	   public static void sendByPreviouslyRecivedOffset(int yearOffset,String subject,String body){
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
	    	  throw new RuntimeException("could not send by previously recived offset"+ e2.getLocalizedMessage());
				}
		   //send 
	      int x= 0;
	      while (x < contacts.size()) {
	             Contact cur = contacts.get(x);
	             SendMail.Send(cur.getAddr(),subject, body);
	        
	             x++;
	      }
	      try {
					db.close();
				} catch (SQLException e2) {
					System.out.println("Could Not Close Database");
				}
	   }//end sendByPreviouslyRecivedOffset
	   
	   public static void sendAll(DBAccess db,String subject,String body){
		      //DBAccess db = null;
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
		    	  throw new RuntimeException(e2);
					}
			   //send too all
		      int x= 0;
		      while (x < all.size()) {
		             Contact cur = all.get(x);
		             SendMail.Send(cur.getAddr(),subject, body);
		             x++;
		      }
		      try {
						db.close();
					} catch (SQLException e2) {
						System.out.println("Could Not Close Database");
					}
		   }//end sendAll

}