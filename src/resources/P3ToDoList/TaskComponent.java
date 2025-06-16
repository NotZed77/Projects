package resources.P3ToDoList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

// This File contains all the functions in the Task Component like checkBox, taskField, DeleteButton etc.

public class TaskComponent extends JPanel implements ActionListener {
    private JCheckBox checkBox;
    private JTextPane taskField;
    private JButton deleteButton;

    public JTextPane getTaskField() { // Getter for TaskField
        return taskField;
    }

    // This panel is used so that we can make updates to the task component panel when deleting tasks
    private JPanel parentPanel;

    public TaskComponent(JPanel parentPanel){
        this.parentPanel = parentPanel; // Creating an object

        // Task Field
        taskField = new JTextPane();
        taskField.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Color for the Task Field
        taskField.setPreferredSize(CommonConstants.TASKFIELD_SIZE); // Size for the Task Field
        taskField.setContentType("text/html"); // It sets the text field to interpret the content as HTML, allowing you to use HTML formatting in the text.
        taskField.addFocusListener(new FocusListener() {
            // Indicate which task field is currently being edited
            @Override
            public void focusGained(FocusEvent e) {
                taskField.setBackground(Color.WHITE); // Color for the selected task
            }

            @Override
            public void focusLost(FocusEvent e) {
                taskField.setBackground(null); // Color for the unselected task
            }
        });

        // Check Box
        checkBox = new JCheckBox();
        checkBox.setPreferredSize(CommonConstants.CHECKBOX_SIZE); // Size for the CheckBox
        checkBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // This  is used to change the mouse cursor when it moves over a specific component.
        checkBox.addActionListener(this); // Implementing the use of Check Box with the help of Action Listener

        // Delete Button
        deleteButton = new JButton("X");
        deleteButton.setPreferredSize(CommonConstants.DELETE_BUTTON_SIZE); // Size for the Delete Button
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // This  is used to change the mouse cursor when it moves over a specific component.
        deleteButton.addActionListener(this); // Implementing the use of Delete Button with the help of Action Listener

        add(checkBox); // Adding the components to Parent Panel
        add(taskField);
        add(deleteButton);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(checkBox.isSelected()){
            String taskText = taskField.getText().replaceAll("<[^>]*>",""); // Replaces all html tags to empty string to grab the main text

            // It strips the HTML tags from the text but leaves the text as is
            taskField.setText("<html><s>"+taskText+ "</s></html>");

        }else if(!checkBox.isSelected()){
            String taskText = taskField.getText().replaceAll("<[^>]*>",""); // Refer to Line 67
            taskField.setText(taskText); // It updates the text displayed in the taskField with the content of taskText
        }

        if(e.getActionCommand().equalsIgnoreCase("X")){
            parentPanel.remove(this); // Delete this component from the parent panel
            parentPanel.repaint(); // It makes sure that the new task field (or any other components added) is visible on the screen.
            parentPanel.revalidate(); // It ensures the layout updates accordingly, adjusting the sizes and positions of components.

        }
    }

}
