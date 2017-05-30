import java.io.*;

class Menu
{
	// Initiate accept class object to scan the input
	Accept ac = new Accept();
	
	// Initiate the WindChill class object
	WindChill windC = new WindChill();
	
	static void mainmenu()
	{
       // Display the main menu
	    String menu = "Kiet Temperature Analysis MENU\n\n1. W)ind Chill Temperature\n\n\n\n0. E)xit";
	    System.out.println(menu);
	    System.out.println("\nEnter Selection: ");
	}

    void acceptselection() 
    {
 	   char charSelection; // Char for input
       
	       do
	       {
	    	   charSelection = ac.AcceptInputChar();
	    	   
	    	   charSelection = Character.toLowerCase(charSelection);  // Convert the input into lower case
	    	   
	    	   switch (charSelection)
	    	   {
				    case '1':
				    case 'w':
				    	do
				    	{
	 				    	Screen.scrollscreen(25); // Print 25 file blank lines
					    	windC.windChill_accept();
					    	Screen.scrollscreen('=', 80, 1);  // Print lines of char to clarify console screen
					    	windC.windChillDisplay();
					    	System.out.println("\ne to exit, any onther character + <ENTER> to continue");
					    	charSelection = ac.AcceptInputChar();
				    	} while (charSelection != 'e');  // First time exit - Out of the calculation
				    break;
				    
				    case '0':
				    case 'e':
				    	charSelection = 'e';
				    break;
				    
				    default:
				    	System.out.println("Error - Run program again");
	    	   }
	       }while (charSelection != 'e');  // Exit completely from program
       
    }

}

/*
   Name: Kiet Tiet - 300265412
   Version 1.0
   Date: Jan 26, 2017
*/
class Assign1
{
	public static void main(String arg[])  // The entry of the program
	{	
		Menu m = new Menu();   // Initiate m object of menu class
		Menu.mainmenu();
		m.acceptselection();
	}

}





