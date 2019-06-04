package be.myPortfolio.loanCalculator;

import java.util.*;

public class LoanApp {

	/**
	 * This will calculate the payments of a loan.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// new LoanGui(); // Never mind this for now ;-)

		// New instance of the Scanner class.
		Scanner kbd = new Scanner(System.in);

		/*
		 * Ask the amount of the loan. In the HigherLower class You'll see that We use
		 * the println method and here the print method. The difference between the two
		 * is that the println will add the newline ASCII code to end the line and the
		 * user will enter His/Her anwser on the next line in the console, and the print
		 * method won't and the user will enter His/Her answer next to the question.
		 */
		System.out.print("How much are you going to loan?: ");

		/*
		 * Because We ask for an loan amount We'll need to get a number that has a
		 * decimal point. The nextDouble method will get that from the input of the
		 * user.
		 */
		double loanAmount = kbd.nextDouble();

		// Ask what the interest rate is.
		System.out.print("What is the interest rate? (in %): ");

		/*
		 * Because We ask for an interest rate We'll need to get a number that has a
		 * decimal point. The nextFloat method will get that from the input of the user.
		 * The difference between float and double is the size of the number that can be
		 * stored. Float can't take a number that is to high.
		 */
		float interestRate = kbd.nextFloat();

		// Ask for how many years the user want's to pay.
		System.out.print("How many terms You want to pay? (in years)(1 year is 12 terms): ");

		// Get the user input.
		float period = kbd.nextFloat();

		/*
		 * Create a new instance of the Loan class. This class has a constructor that
		 * will set the given parameters in the instance.
		 */
		Loan loan = new Loan(loanAmount, period, interestRate);

		// Call the method calculate of the loan object.
		loan.calculate();

		// Print the amount of monthly pay.
		loan.printMonthlyPeriodAmount();
		
		// Get the payment overview.
		loan.getPaymentOverview();

		// Close the Scanner.
		kbd.close();
	}

}
