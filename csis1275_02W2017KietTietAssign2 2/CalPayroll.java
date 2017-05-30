import java.text.NumberFormat; 

class CalPayroll extends Pay
{
   Accept ac = new Accept();

   NumberFormat dollars = NumberFormat.getCurrencyInstance();
   NumberFormat percentage = NumberFormat.getPercentInstance();
	
   Payroll subP = new Payroll();  // Instantiate Payroll object

   void displayinfo() // Method to display gross, tax, and net pay
   {
      double gross;
      gross = super.calc_payroll();
      System.out.println("Gross pay is \t:" + dollars.format(gross));
      System.out.println("Tax is \t\t\t:" + percentage.format(tax(gross)));
      System.out.println("Net pay is \t\t:" + dollars.format(subP.calc_payroll()));
   }

   void acceptPay() 
   {
      char select = ' ';
      int hrsStr;
      double rate;
      double hours;
      do 
      {
         System.out.println("Payroll Computation\n");
         System.out.println("Enter number of hours worked (00.0) <0 for Quick exit>:");
         hours = ac.AcceptInputDouble();
         super.setHours(Math.abs(hours));
         subP.setHours(Math.abs(hours));
      
         if (super.getHours() != 0.0) // When the working hours is 0 pass all the calculation
         {
            System.out.println("Enter first number of hours straigt (integer or 0 to disable):");
            hrsStr = ac.AcceptInputInt();
            super.setHrsStr(Math.abs(hrsStr));
            subP.setHrsStr(Math.abs(hrsStr));
         	
            System.out.println("Enter hourly rate of worker (00.00):");
            rate = ac.AcceptInputDouble();
            super.setRate(Math.abs(rate));
            subP.setRate(Math.abs(rate));
         	
            Screen.scrollscreen('=', 100, 2); // Adding line of '=' for visual
            displayinfo();  // Display result
         }
      	
         System.out.println("\ne to exit, any other letter + <ENTER> to continue");
         ac.AcceptInputString();
         select = ac.AcceptInputChar();
         select = Character.toLowerCase(select);
      } while (select != 'e'); //End by input 'e'
   
   }

}