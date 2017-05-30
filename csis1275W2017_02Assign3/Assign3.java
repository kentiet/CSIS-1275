import java.io.*;
import java.util.*;
import java.text.*;  // For NumberFormat

class Assign3
{
   // public static String[] combined;
   static NumberFormat dollars = NumberFormat.getCurrencyInstance();  //Currency format for sales
   private double sales;  //for total sales
   
   public void setSales(double a)
   {
      sales = a;
   }
   
   public double getSales()
   {
      return sales;
   }
   
   public void sortArray(String arrNames[], double arrSales[])  //Sort array method
   {
      String tempStr;  //temp for name
      double tempD;   //temp for sales
      
   
      for (int counter = 1; counter < arrNames.length; counter++)
      {
         for (int i = 0; i < arrNames.length - 1; i++)
         {
            if (arrNames[i].compareToIgnoreCase(arrNames[i + 1]) > 0)
            {
               tempStr = arrNames[i];    //Swap names
               arrNames[i] = arrNames[i + 1];
               arrNames[i + 1] = tempStr;
               
               tempD = arrSales[i];   //Swap sales
               arrSales[i] = arrSales[i + 1];
               arrSales[i + 1] = tempD;
               
            }
         }
      }   
   }
   
   public int binSrch(String arrNames[], String a)  //Binnary search
   {
      int mid = -1;
      int first = 0;
      int end = arrNames.length - 1;
      boolean found = false;
      
      while (first <= end)
      {
         mid = (first + end) / 2;
      //         System.out.println(mid);
         if (arrNames[mid].compareToIgnoreCase(a) == 0)
         {
            found = true;
            break; 
         }
         else
         {
            if (arrNames[mid].compareToIgnoreCase(a) < 0)
               first = mid + 1;
            else
               end = mid - 1;
         }
      }
      
      if (!found)
         mid = -1;  // -1 when not found
      return mid;  
   }
   
   public void displayArray(String arrNames[], double arrSales[])  //Display result without asterisk
   {
      for (int i = 0; i < arrNames.length; i++)
      {
         System.out.println((i+ 1) + " --- " + arrNames[i] + " ===> " + dollars.format(arrSales[i]));
      }
   
      System.out.println("===============================");
   }
   
   public void displayArray(String arrNames[], double arrSales[], String a)  //Display result with asterisk
   {
      String[] merge = new String[arrNames.length];
      int index;
      index = binSrch(arrNames, a);  //the position of name in the array
      
      for (int i = 0; i < arrNames.length; i++)
      {
         merge[i] = (i+ 1) + " --- " + arrNames[i] + " ===> " + dollars.format(arrSales[i]);
      }
   
      for (int i = index; i < arrNames.length; i++)   //linear search forward
      {
         if (arrNames[i].compareToIgnoreCase(a) == 0)
         {
            merge[i] = (i+ 1) + " --- " + arrNames[i] + " ===> " + dollars.format(arrSales[i]) + " ********";
            sales += arrSales[i];
         }
      }
      
      for (int i = index - 1;  i >= 0; i--)   //linear search backward
      {
         if (arrNames[i].compareToIgnoreCase(a) == 0)
         {
            merge[i] = (i+ 1) + " --- " + arrNames[i] + " ===> " + dollars.format(arrSales[i]) + " ********";
            sales += arrSales[i];
         }
      
      }
      
      for (int i = 0; i < arrNames.length; i++)
      {
         System.out.println(merge[i]);
      }
   
      
      System.out.println("=============================");
   }
   
   public static void main(String args[])
   {
      String name;
      int indexSrch;
      
      NumberFormat dollars = NumberFormat.getCurrencyInstance();
      Scanner sc = new Scanner(System.in);
      
      Assign3 a3 = new Assign3();
      
      String names[] = {"Carol Danvers","halo JOnes", "lex LUthor", "NEal adams", "halo Jones","theo KOjak", "david starsky", 
                              "clark KEnt", "Carol Danvers","bruce LEE", "bruce WAYne","donald trump", "NEal adams", "DONAld TRump", "david starsky", "clark KEnT", "Bruce Lee", 
                              "bruCe WAYne","xavier chuck", "donald trump","JAck Kirby","LOis LAne",
                              "LAdy Penelope","DIana Prince","Carol Danvers"};
   	    
          
      double sales[] = {124.5,62.72,78.2,0.0,56.12,934.21,124.5,
          62.72,78.2,145.0,56.12,934.21,124.5,62.72,78.2,0.0,56.12,
          934.21,124.5,62.72,78.2,0.0,56.12,934.21,74.34};
          
         
      do
      {
         a3.sortArray(names, sales);
      
         System.out.println("Please enter a name to search (0 to Exit)");
         
         name = sc.nextLine().trim();  //Trim the input value
         
      
         indexSrch = a3.binSrch(names, name);
         if (indexSrch != -1)
         {
            System.out.println("Alphabetical Order\n");
            a3.displayArray(names, sales, name);
            System.out.println(name + "++++ Sales Total: " + dollars.format(a3.getSales()));
         }
         else
         {
            if (!name.equals("0"))
            {
               System.out.println("Alphabetical Order\n");
            
               a3.displayArray(names, sales);
               System.out.println(name + " is not found");
            }
         }
         a3.setSales(0.0);  //Reset total sales for next search
         
      } while (name.compareTo("0") != 0);
          
   } // main

}