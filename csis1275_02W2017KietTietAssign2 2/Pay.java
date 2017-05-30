class Pay 
{
	// Instance variables
	private double hours;
	private double rate;
	private int hrsStr;
	
	public void setHours(double a) 
	{
		hours = a;
	}

	public void setRate(double a) 
	{
		rate = a;
	}

	public void setHrsStr(int a) 
	{
		hrsStr = a;
	}

	public double getHours() 
	{
		return hours;
	}

	public double getRate() 
	{
		return rate;
	}

	public int getHrsStr() 
	{
		return hrsStr;
	}
	
	//Method to calculate payroll
	public double calc_payroll() 
	{
		double gross;
		if (hrsStr != 0.0) // When the working hours straight are not 0
		{
			if (hours > hrsStr)
				gross = ((hours - hrsStr) * (rate * 1.77)) + (hrsStr * rate);
			else
				gross = hours * rate;
		} 
		else 
			gross = rate * hours;
		return gross;
	}
	
	//Method to calculate tax
	public double tax(double gross)
	{
		double tax;
		if ((gross >= 0.00) && (gross <= 399.99))
			tax = 0.07;
		else if ((gross >= 400.00) && (gross <= 899.99))
			tax = 0.1;
		else
			tax = 0.16;

		return tax;
	}

}
