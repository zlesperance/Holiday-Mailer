package holidayMailer;

public class Contact{
   
   private String fName;
   private String lName;
   private String addr;
   private int lastRec;
   
   public Contact(String fName, String lName, String addr, int lastRec){
      this.fName = fName;
      this.lName = lName;
      this.addr = addr;
      this.lastRec = lastRec;
   }//end constructor
   
   public String getFName(){
      return this.fName;
   }//end getFName
   
   public String getLName(){
      return this.lName;
   }//end getLName
   
   public String getAddr(){
      return this.addr;
   }//end getAddr
   
   public int getLastRec(){
      return this.lastRec;
   }//end getLastRec
   
   public boolean equals(Contact that) {
	   
	   if (this.fName.equals(that.getFName()) &&
		   this.lName.equals(that.getLName()) &&
		   this.addr.equals(that.getAddr())   &&
		   this.lastRec == that.getLastRec()      ) {
		   
		   return true;
	   }//if fname, lName, addr, and lastRec are all equal
	   
	   return false;
	   
   }
}
