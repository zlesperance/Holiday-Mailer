package holidayMailer;

public class SendMailRun{
 
	public static void main(String[] args) {
 
		SendMail.SendMail("m_muchachos@yahoo.com","Moustache Muchachos Test","This is a test using the gmail to send to the yahoo account.");
			
		SendMail.SendMail("moustachedmuchachos@gmail.com","cscd350password","m_muchachos@yahoo.com","Moustache Muchachos Test","This is a test using the gmail to send to the yahoo account.");
	}
}