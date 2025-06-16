package resources.P1ATM;

import java.text.DecimalFormat; // This class is used to format decimal numbers into strings with a specific pattern
import java.util.Scanner; // This class is used to read input

public class Account {
    Scanner input = new Scanner(System.in); // Read inputs
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00"); //Referring to line 3

    public int setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
        return customerNumber;                     // Setter for CustomerNumber
    }

    public int getCustomerNumber() {
        return customerNumber;                    // Getter for CustomerNumber
    }

    public int setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;               // Setter for PinNumber
        return pinNumber;
    }

    public int getPinNumber() {
        return pinNumber;                        // Getter for PinNumber
    }

    public double getCheckingBalance() {
        return checkingBalance;                 // Getter for Checking Balance
    }

    public double getSavingBalance() {
        return savingBalance;                  // Getter for Saving Balance
    }

    public double calcCheckingWithdraw(double amount) {
        checkingBalance = (checkingBalance - amount);
        return checkingBalance;              // Withdrawing Money from Checking Balance
    }

    public double calcSavingWithdraw(double amount) {
        savingBalance = (savingBalance - amount);
        return savingBalance;               // Withdrawing Money from Saving Balance
    }

    public double calcCheckingDeposit(double amount) {
        checkingBalance = (checkingBalance + amount);
        return checkingBalance;           // Depositing Money to Checking Balance
    }

    public double calcSavingDeposit(double amount) {
        savingBalance = (savingBalance + amount);
        return savingBalance;            // Depositing Money to Saving Balance
    }

    public void getCheckingWithdrawInput() {  // Withdrawing Money Input from Checking Balance
        System.out.println("Checking Account Balance: " + moneyFormat.format(checkingBalance));
        System.out.println("Amount you want to withdraw from Checking Account: ");
        double amount = input.nextDouble();  // Tells the input amount

        if((checkingBalance - amount) >=0) {
            calcCheckingWithdraw(amount);
            System.out.println("New Checking Account balance: " + moneyFormat.format(checkingBalance));
        } else {
            System.out.println("Balance cannot be negative." + "\n"); // Withdrawing Money Output and verifying Balance from Checking Balance
        }
    }

    public void getSavingWithdrawInput() {  // Withdrawing Money Input from Saving Balance
        System.out.println("Saving Account Balance: " + moneyFormat.format(savingBalance));
        System.out.println("Amount you want to withdraw from Saving Account: ");
        double amount = input.nextDouble(); // Tells the input amount

        if((savingBalance - amount) >=0) {
            calcSavingWithdraw(amount);
            System.out.println("New Saving Amount balance: " + moneyFormat.format(savingBalance));
        } else {
            System.out.println("Balance cannot be negative." + "\n"); // Withdrawing Money Output and verifying Balance from Saving Balance
        }
    }

    public void getCheckingDepositInput() {  // Depositing Money Input to Saving Balance
        System.out.println("Checking Account Balance: " + moneyFormat.format(checkingBalance));
        System.out.println("Amount you want to be deposit to Checking Account: ");
        double amount = input.nextDouble();

        if ((checkingBalance + amount) >=0) {
            calcCheckingDeposit(amount);
            System.out.println("New Checking Amount balance: " + moneyFormat.format(checkingBalance));
        } else {
            System.out.println("Balance cannot be Negative." + "\n"); // Depositing Money Output and verifying Balance from Saving Balance
        }
    }

    public void getSavingDepositInput() { // Depositing Money Input to Saving Balance
        System.out.println("Saving Account Balance: " + moneyFormat.format(savingBalance));
        System.out.println("Amount you want to be deposit to Saving Account: ");
        double amount = input.nextDouble();

        if ((savingBalance + amount) >=0) {
            calcSavingDeposit(amount);
            System.out.println("New Saving Amount balance: " + moneyFormat.format(savingBalance));
        } else {
            System.out.println("Balance cannot be Negative." + "\n"); // Depositing Money Output and verifying Balance from Saving Balance
        }
    }

    private int customerNumber;
    private int pinNumber;                              // Hidden from the other classes
    private double checkingBalance = 0;
    private double savingBalance = 0;
}
