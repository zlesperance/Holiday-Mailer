package holidayMailer;

/**
 * Creator: Steven Berg
 * Date: 11/20/14
 * ChangeLog:
 * 11/20/14:
 * To Do:
 * -
 */
public interface iUserOut {
    void printString(String msg);
    void printLName(String name);
    void printFName(String name);
    void printEMail(String email);
    void printLastRec(int date);
    void printError(String msg);
    void printError(String msg, String details);
}
