package holidayMailer;

public class UserOut implements iUserOut {
    public void printString(String msg){
        System.out.println(msg);
    }

    public void printLName(String name){
        System.out.println(name);
    }

    public void printFName(String name){
        System.out.println(name);
    }

    public void printEMail(String email){
        System.out.println(email);
    }

    public void printLastRec(int date){
        System.out.println(date);
    }
}
