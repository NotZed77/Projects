package resources.P1ATM;
import java.io.IOException;

public class ATM extends OptionMenu {
    public static void main(String[] args) throws IOException { // IO Exception for methods like getLogin()
        OptionMenu optionMenu = new OptionMenu(); // Creates a new object to use methods like getLogin() given below

        optionMenu.getLogin();
    }
}
