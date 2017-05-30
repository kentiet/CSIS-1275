import java.util.*;

class Accept
{
    Scanner stdin = new Scanner(System.in); 
    
    // Reading integer input
    public int AcceptInputInt()  
    {
       return(stdin.nextInt());
	}
    
    // Reading character input
    public char AcceptInputChar()
    {
    	return (stdin.nextLine().charAt(0)); 
    }
    
    // Reading String input
    public String AcceptInputString()
    {
    	return (stdin.nextLine());
    }
    
    // Reading Float input
    public float AcceptInputFloat()
	{
    	return (stdin.nextFloat());
	}
    
    // Reading double input
    public double AcceptInputDouble()
    {
    	return (stdin.nextDouble());
    }
}

