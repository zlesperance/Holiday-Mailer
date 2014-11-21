package holidayMailer;

import org.apache.commons.validator.routines.EmailValidator;
import java.util.Scanner;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class UserIn implements iUserIn{
    private Scanner keyIn;

    public UserIn(){
        this.keyIn = new Scanner(System.in);
    }
 
    public String readName(){
        Boolean exit = false;
        String in;
        do{
            System.out.print(">");
            in = keyIn.nextLine();
            exit = validateName(in);

            if(!exit)
                System.out.println("Improper Input");

        }while(!exit);
        return in;
    }

    public String readEMail(){
        Boolean exit = false;
        String in = "";
        do{
            System.out.print(">");
            in = keyIn.nextLine();
            exit = validateEMail(in);

            if(!exit)
                System.out.println("Improper Input");

        }while(!exit);
        return in;
    }

    public Boolean validateEMail(String in) {
        EmailValidator EV = EmailValidator.getInstance();
        return EV.isValid(in);
    }

    public int readLastRec(){
        boolean exit = false;
        String in;
        do{
            System.out.print(">");
            in = keyIn.nextLine();
            exit = validateInt(in);
            if(!exit){
                System.out.println("Improper Input");
            }
        }while(!exit);
        return Integer.parseInt(in);
    }

    public boolean validateName(String in){
        return in.matches("^[a-zA-Z.'-]{1,25}$");
    }

    public boolean validateInt(String in){
        Calendar cal = new GregorianCalendar();
	    int yr = cal.get(Calendar.YEAR);
        int minYr = (yr - 100), maxYr = (yr + 1);

        try{
            int temp = Integer.parseInt(in);
            if(temp > minYr && temp < maxYr){
                return true;
            }else{
                return false;
            }
        }catch(NumberFormatException e){
            System.out.println("Improper Input");
            return false;
        }
    }
}
