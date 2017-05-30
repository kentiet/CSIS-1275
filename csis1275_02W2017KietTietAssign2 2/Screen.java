public class Screen
{
	// A for loop for printing blank line to clear the screen
   public static void scrollscreen(int numLine)   
   {  
      for (int i = 1; i <= numLine; i++)
      {
         System.out.println();
      }
   }
	
	// The overload method of scrollscreen method
	// Print the line of character to separate parts in console screen
   public static void scrollscreen(char inputChar, int numChar, int numLine)
   {
      for (int row = 1; row <= numLine; row++)
      {
         for (int col = 1; col <= numChar; col++)
         {
            System.out.print(inputChar);
         }
         System.out.println(" ");
      }
   }     
}