/* 
Winter 2017

Calculate wind chill temperature.


*/
/////////////////////////////////////////////////////////
import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class WindChill
{
	Accept ac = new Accept();
	
	// Create decimal format object to modify the appropriate output
	NumberFormat dcFormat = new DecimalFormat("#0.0");
	public double startTemp; //Start temperature
	public double endTemp;	// End temperature
	public double speedIn;	// Input speed from users
	public double speedStart = 8.0; // Default start speed of wind
	
	public void windChill_accept()
	{	
		
		System.out.println("Wind Chill Temperature Calculation\n");
		
		// Set do/while loops in order to verify the input
		do
		{
			System.out.println("Please enter the START air temp in celsius (decimal) MUST be BELOW 9: ");
			startTemp = ac.AcceptInputDouble();
		} while (startTemp >= 9);
			
		do
		{
			System.out.println("Please enter the END air temp in celsius (decimal) MUST be BELOW 9: ");
			endTemp = ac.AcceptInputDouble();
		} while (endTemp >= 9);
			
		do
		{
			System.out.println("Please enter wind speed (decimal) FROM 8km/h to: ");
			speedIn = ac.AcceptInputDouble();
		} while (speedIn <= 8);	
	}

	public void windChillDisplay()
	{		
		swap();  // Call swap method in case the start temp is larger than end temp
		
		
		// Print out the column headers
		for (double col = startTemp; col <= endTemp; col += 5.0)
		{
			if (col != endTemp)
			{
				System.out.print("\t\t" + col + '\u00B0' + "C");
			}
		}
		System.out.print("\t\t" + endTemp + '\u00B0' + "C");
		
		// Loops for calculate and print the result
		for (double row = speedStart; row <= speedIn; row += 0.5)
		{
			if (row != speedIn)
				System.out.print("\n" + row);

			for (double col = startTemp; col <= endTemp; col += 5.0)
			{
				if (row != speedIn)
				{
					if (col != endTemp)
					{
						System.out.print("\t\t" + dcFormat.format(windChillCalc(col, row)));
					}
				}
				
			}
			if (row != speedIn)
				System.out.print("\t\t" + dcFormat.format(windChillCalc(endTemp, row)));
		}
		System.out.print("\n" + speedIn);
	
		//  Calculate the windchill with the exact value input 
		//from users that previous loops cannot reach to.
		for (double col = startTemp; col <= endTemp; col += 5)
		{
			if (col != endTemp)
				System.out.print("\t\t" + dcFormat.format(windChillCalc(col, speedIn)));
			
		}
		System.out.print("\t\t" + dcFormat.format(windChillCalc(endTemp, speedIn)));

			
	}
  
	// Method to calculate wind chill temperature 
	public double windChillCalc(double temp, double speed)
	{
		double windIndex;
		
		windIndex = 13.12 + (0.6215 * temp) - (11.37 * Math.pow(speed, 0.16)) + (0.3965 * temp * Math.pow(speed, 0.16)); 
		
		return windIndex;
	}
    
	// Method to swap the startTemp and endTemp input when startTemp > endTemp
	public void swap()
	{
		if (startTemp > endTemp)
		{
			double temp = startTemp;
			startTemp = endTemp;
			endTemp = temp;
		}
	}

}
