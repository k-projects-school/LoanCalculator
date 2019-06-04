package be.myPortfolio.loanCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoanGui extends JFrame{
	// Set the title of the application
	final static String TITLE = "Loan calculator";
	// Set the text fields that are needed
	private JTextField loanAmountTxtField = new JTextField();
	private JTextField interestRateTxtField = new JTextField();
	private JTextField loanDurationTxtField = new JTextField();
	// Set the buttons
	private JButton calculateBtn = new JButton("Calculate");
	private JButton clearBtn = new JButton("Clear fields");
	
	public LoanGui() {
		setTitle(TITLE);
		setSize(500, 1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		createWindow();
		setVisible(true);
	}

	private void createWindow() {
		add(createLabel(), BorderLayout.NORTH);
		add(createLabel(), BorderLayout.WEST);
		add(createLabel(), BorderLayout.EAST);
		add(createLabel(), BorderLayout.SOUTH);
	}

	private JLabel createLabel() {
		JLabel label = new JLabel("");
		label.setPreferredSize(new Dimension(10, 10));
		return label;
	}
}
