package resources.P4MorseCodeTranslator;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() { // Invoke later ensures that the GUI is created and updated in a thread-safe manner
            @Override
            public void run() {
                new MorseCodeTranslatorGUI().setVisible(true);
            }
        });
    }
}
