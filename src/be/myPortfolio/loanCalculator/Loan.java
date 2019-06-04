package be.myPortfolio.loanCalculator;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Loan {

	/*
	 * This will store the loan amount.
	 */
	private double loanAmount;

	/*
	 * This stores the amount of periods the loan will be payed.
	 */
	private int paymentPeriods;

	/*
	 * This store the amount that should be payed every period of the loan duration.
	 */
	private double monthlyPeriodPayAmount;

	/*
	 * This store the amount interest that will be applied every year.
	 */
	private double yearlyPeriodPayAmount;

	/*
	 * This stores the amount of interest, in percent fraction, is applied.
	 */
	private float interestRate;

	/**
	 * This stores the total interest that will be applied.
	 */
	private double totalDue;

	/*
	 * This stores how much of the loaned amount is paid.
	 */
	private double capitalPaymentAmonth;

	/*
	 * This stores how much of the interest amount is paid.
	 */
	private double interestPaymentAmonth;

	/*
	 * This stores the total interest that is going to be paid.
	 */
	private double totalInterest;

	/*
	 * Because we work with numbers with decimal points and do some calculations,
	 * the possibility that We'll get a number that has a lot of digits after the
	 * decimal point is very high so We'll need to format it in a more user friendly
	 * readable number. The DecimalFormat class can help us with that.
	 */
	private static NumberFormat formatter = new DecimalFormat("#0.00");

	/**
	 * Class constructor.
	 * 
	 * @param loanAmount            - The amount that the user wants to loan.
	 * @param paymentPeriodsInYears - The payment periods that the user wants to pay
	 *                              (1 year is 12 terms).
	 * @param interestRate          - The interest rate that is going to be applied
	 *                              on the loan.
	 */
	public Loan(double loanAmount, float paymentPeriodsInYears, float interestRate) {
		this.setLoanAmount(loanAmount);
		this.setInterestRate(interestRate);

		// Change the payment periods from years to months
		this.setPaymentPeriods((int) paymentPeriodsInYears * 12);
	}

	/**
	 * Set the loan amount.
	 * 
	 * @param amount
	 */
	public void setLoanAmount(double amount) {
		this.loanAmount = amount;
	}

	/**
	 * Set the payment periods.
	 * 
	 * @param periods
	 */
	public void setPaymentPeriods(int periods) {
		this.paymentPeriods = periods;
	}

	/**
	 * Set the monthly period amount.
	 * 
	 * @param amount
	 */
	public void setMonthlyPeriodAmount(double amount) {
		this.monthlyPeriodPayAmount = amount;
	}

	/**
	 * Set the yearly period amount.
	 * 
	 * @param yearlyPeriodAmount
	 */
	public void setYearlyPeriodAmount(double yearlyPeriodAmount) {
		this.yearlyPeriodPayAmount = yearlyPeriodAmount;
	}

	/**
	 * Set the interest rate.
	 * 
	 * @param rate
	 */
	public void setInterestRate(float rate) {
		this.interestRate = rate / 100;
	}

	public void setTotalDue() {
		this.totalDue = this.monthlyPeriodPayAmount * this.paymentPeriods;
	}

	/**
	 * @return double
	 */
	public double getLoanAmount() {
		return this.loanAmount;
	}

	/**
	 * @return integer
	 */
	public int getPaymentPeriods() {
		return this.paymentPeriods;
	}

	/**
	 * Print the amount of monthly pay.
	 */
	public void printMonthlyPeriodAmount() {
		System.out.println("The monthly payments will be: " + formatter.format(this.monthlyPeriodPayAmount));
	}

	/**
	 * @return double
	 */
	public double getetYearlyPeriodAmount() {
		return this.yearlyPeriodPayAmount;
	}

	/**
	 * @return float
	 */
	public float getInterestRate() {
		return this.interestRate;
	}

	/**
	 * @return double
	 */
	public double getTotalDue() {
		return this.totalDue;
	}

	/**
	 * This will calculate the loan.
	 */
	public void calculate() {
		// Create a variable that holds the amount of months there are in a year
		int periodsAyear = 12;

		/*
		 * Calculate the yearly payment.
		 * 
		 * ex: yearly payment = 0.04 / (1 - (1 + 0.04 in negative years power)) * 100000
		 */
		this.setYearlyPeriodAmount(this.interestRate
				/ (1 - this.claculatePower((1 + this.interestRate), (this.paymentPeriods / periodsAyear), true))
				* this.loanAmount);
		this.setMonthlyPeriodAmount(this.yearlyPeriodPayAmount / periodsAyear);
		this.setTotalDue();
		this.setTotalInterest();
		this.calculatePayments();
	}

	/**
	 * This will print a complete overview how much capital and interest every month
	 * is left of the loan.
	 */
	public void getPaymentOverview() {
		double capitalPayed = 0;
		double interestPayed = 0;
		System.out.println("             Open capital | Open interest");
		System.out.println("---------------------------------------------");
		System.out.println("Total due: " + formatter.format(this.loanAmount) + " | "
				+ formatter.format(this.totalInterest));
		System.out.println("---------------------------------------------");
		for (int i = 1; i <= this.paymentPeriods; i++) {
			capitalPayed = this.capitalPaymentAmonth * i;
			interestPayed = this.interestPaymentAmonth * i;
			System.out.println("Payed after month " + i + ": " + formatter.format(capitalPayed) + " | "
					+ formatter.format(interestPayed));
			System.out.println("---------------------------------------------");
		}
	}

	/**
	 * Set the total interest of the loan.
	 */
	private void setTotalInterest() {
		this.totalInterest = this.totalDue - this.loanAmount;
	}

	/**
	 * Calculate how much from the loan and how much from the interest is going to
	 * be paid.
	 */
	private void calculatePayments() {
		this.capitalPaymentAmonth = this.loanAmount / this.paymentPeriods;
		this.interestPaymentAmonth = this.totalInterest / this.paymentPeriods;
	}

	/**
	 * Calculate the power of a number.
	 * 
	 * @param numberToBeCalculated
	 * @param powerAmount
	 * @param isNegativePower
	 * @return double - The calculated power of the given number
	 */
	private double claculatePower(float numberToBeCalculated, int powerAmount, boolean isNegativePower) {
		/*
		 * Create the might and set it first to the number that has to be calculated
		 */
		float power = numberToBeCalculated;

		/**
		 * Loop through the amount of times the might of the given number should be
		 * calculated
		 */
		for (int i = 0; i < powerAmount; i++) {
			if (isNegativePower) {
				/*
				 * If its a negative might, we'll have to divide the might by the given number
				 */
				power /= numberToBeCalculated;
			} else {
				/*
				 * If its a positive might, we'll have to multiply the might with the given
				 * number
				 */
				power *= numberToBeCalculated;
			}
		}

		/**
		 * If we need a negative might, we'll have to divide one more time extra. This
		 * because in a negative might the first time its divided, we'll have the 0th
		 * might of the number.
		 */
		if (isNegativePower) {
			power /= numberToBeCalculated;
		}

		return power;
	}
}
