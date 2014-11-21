import java.util.Scanner;

public class Tester{
    public static Scanner myscan = new Scanner(System.in);
	public static void main(String[] args){
        Boolean exit = false;
        do{
            System.out.print("Enter 0 to exit. 1 to continue\n>");
            int choice = myscan.nextInt();
            if(choice != 0){
                test();
            }else{
                exit = true;
            }
        }while(!exit);
	}

    public static void test(){
        UserOut uo = new UserOut();
        UserIn ui = new UserIn();
        uo.printString("Name");
        String name = ui.readName();
        uo.printString("Date");
        int date = ui.readLastRec();
        uo.printString("email");
        String email = ui.readEMail();
        System.out.println(name);
        System.out.println(date);
        System.out.println(email);
    }
}
