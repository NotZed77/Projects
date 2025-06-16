package resources.P3ToDoList;

import javax.swing.*;

public class App {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() { // Schedules the creation of your GU
            @Override
            public void run() {
                new ToDoListGUI().setVisible(true); // Makes the GUI Visible.
            }
        });
    }
}
