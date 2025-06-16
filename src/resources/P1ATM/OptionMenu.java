package resources.P1ATM;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;


public class OptionMenu extends Account {
    Scanner menuInput = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$',###,##0.00");

    HashMap<Integer, Integer> data = new HashMap<Integer, Integer>(); // Usage Of Hashmap for Correct CustomerNumber, PinNumber

    public void getLogin() throws IOException{
        int x = 1;

        do{ // Running the ATM if the data given below is the PinNumber and Password or else Printing "Wrong Input" Messages
            try{
                data.put(9876543,9876);
                data.put(8989898,1929);
                data.put(9811822,4021);

                System.out.println("Welcome to the ATM Project!");

                System.out.print("Enter your Customer Number: ");
                setCustomerNumber(menuInput.nextInt());

                System.out.print("Enter your Pin Number: ");
                setPinNumber(menuInput.nextInt());
            } catch(Exception e){ // Used to catch exceptions in the try block
                System.out.println("\n" + "Invalid character(s) . Only numbers." + "\n");
                x = 2;
            }
            for (Entry<Integer,Integer> entry : data.entrySet()) {
                if (entry.getKey() == getCustomerNumber() && entry.getValue() == getPinNumber()) {
                    getAccountType();
                }
            }
            System.out.println("\n" + "Wrong Customer Number or Pin Number." + "\n");
        } while (x == 1);
    }

    public void getAccountType() { // Interface Menu for Accessing Different Accounts and Other Options
        System.out.println("Select the Account you want to Access: ");
        System.out.println("Type 1 - Checking Account");
        System.out.println("Type 2 - Saving Account");
        System.out.println("Type 3 - Exit");
        System.out.print("Choice: ");

        int selection = menuInput.nextInt();

        switch(selection) {
            case 1:
                getChecking();
                break;

            case 2:
                getSaving();
                break;

            case 3:
                System.out.println("Thank you for using the ATM,bye!");
                break;

            default:
                System.out.println("\n"+"Invalid Choice."+"\n");
                getAccountType();
        }
    }

    public void getChecking() { // Interface Menu for Accessing Checking Account Functions
        System.out.println("Checking Account: ");
        System.out.println("Type 1 - View Balance");
        System.out.println("Type 2 - Withdraw Funds");
        System.out.println("Type 3 - Deposit Funds");
        System.out.println("Type 4 - Exit");
        System.out.print("Choice: ");

        int selection = menuInput.nextInt();

        switch(selection) {
            case 1:
                System.out.println("Checking Account Balance: "+ moneyFormat.format(getCheckingBalance()));
                getAccountType();
                break;

            case 2:
                getCheckingWithdrawInput();
                getAccountType();
                break;

            case 3:
                getCheckingDepositInput();
                getAccountType();
                break;

            case 4:
                System.out.println("Thank you for using the ATM,bye!");
                break;

            default:
                System.out.println("\n" + "Invalid choice." + "\n");
                getChecking();
        }

    }
    public void getSaving() { // Interface Menu for Accessing Saving Account Functions
        System.out.println("Saving Account: ");
        System.out.println("Type 1 - View Balance");
        System.out.println("Type 2 - Withdraw Funds");
        System.out.println("Type 3 - Deposit Funds");
        System.out.println("Type 4 - Exit");
        System.out.print("Choice: ");

        int selection = menuInput.nextInt();

        switch(selection) {
            case 1:
                System.out.println("Checking Account Balance: "+ moneyFormat.format(getSavingBalance()));
                getAccountType();
                break;

            case 2:
                getSavingWithdrawInput();
                getAccountType();
                break;

            case 3:
                getSavingDepositInput();
                getAccountType();
                break;

            case 4:
                System.out.println("Thank you for using the ATM,bye!");
                break;

            default:
                System.out.println("\n" + "Invalid choice." + "\n");
                getSaving();
        }
    }
}

