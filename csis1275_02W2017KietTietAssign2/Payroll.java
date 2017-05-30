class Payroll extends Pay 
{

	// Overload calc_payroll method from superclass
	// for tax calculation
	public double calc_payroll() 
	{
		double net;
		double gross;
		double tax;
		gross = super.calc_payroll();
		tax = super.tax(super.calc_payroll());

		net = gross * (1 - tax);

		return net;
	}
}
