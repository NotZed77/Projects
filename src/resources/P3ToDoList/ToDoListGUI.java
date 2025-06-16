package resources.P3ToDoList;

import javax.swing.*; // Import of tools like JFrame, JLabel, BoxLayout, BorderFactory etc.
import java.awt.*; // Import of tools like Font, Color, Cursor, Graphics, Dimension
import java.awt.event.ActionEvent; // Action Performed
import java.awt.event.ActionListener; // Action Listened
import java.io.File; // File customFontFile = new File(filePath);



public class ToDoListGUI extends JFrame implements ActionListener {
    // TaskPanel will act as the container for the taskComponentPanel,
    // TaskComponentPanel will store all the taskComponents
    private JPanel taskPanel, taskComponentPanel;
    public ToDoListGUI(){
        super("To Do List Application"); // It adds text to the title bar
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Exits the program when window is closed.
        setPreferredSize(CommonConstants.GUI_SIZE); // Set Size for GUI
        pack(); // It wraps the window around the application
        setLocationRelativeTo(null); // It centers the window on the screen.
        setResizable(false); // Doesn't allow to resize
        setLayout(null); // We dont need to Set Layout as we are manually setting the bounds for the application
        getContentPane().setBackground(Color.decode("#A9A9A9")); // Set the background color of the content pane to dark gray

        addGuiComponents(); // Components inside the Application

    }
    private void addGuiComponents(){
        // Banner Text
        JLabel bannerLabel = new JLabel("To Do List"); // Heading of the Application
        bannerLabel.setFont(createFont("resources/LEMONMILK-Light.otf",36f)); // Using a imported font
        bannerLabel.setBounds(
                (CommonConstants.GUI_SIZE.width - bannerLabel.getPreferredSize().width)/2, // Setting the Layout bounds manually
                15, // Y = 15
                CommonConstants.BANNER_SIZE.width, // Banners width
                CommonConstants.BANNER_SIZE.height // Height
        );
        // TaskPanel
        taskPanel = new JPanel();


        // TaskComponentPanel
        taskComponentPanel = new JPanel(); // Creating an object
        taskComponentPanel.setLayout(new BoxLayout(taskComponentPanel, BoxLayout.Y_AXIS)); //  Sets the layout manager of the taskComponentPanel to BoxLayout
        taskPanel.add(taskComponentPanel);

        JScrollPane scrollPane = new JScrollPane(taskPanel); // Scrolling of taskPanel
        scrollPane.setBounds(8, 70 , CommonConstants.TASKPANEL_SIZE.width, CommonConstants.TASKPANEL_SIZE.height); // The space the scrollable task area will occupy within your frame
        scrollPane.setBorder(BorderFactory.createLoweredBevelBorder()); // Creating a border frame
        scrollPane.setMaximumSize(CommonConstants.TASKPANEL_SIZE); // Set Size for Task Panel
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Vertical Bar Limits
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Horizontal Bar Limits

        // Changing the speed of the scroll bar
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(20);

        // Add task button
        JButton addTaskButton = new JButton("Add Task");
        addTaskButton.setFont(createFont("resources/LEMONMILK-Light.otf",18f));
        addTaskButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // This  is used to change the mouse cursor when it moves over a specific component.
        addTaskButton.setBounds(-5, CommonConstants.GUI_SIZE.height - 88, // Set Size for Task Button
        CommonConstants.ADDTASK_BUTTON_SIZE.width,CommonConstants.ADDTASK_BUTTON_SIZE.height);
        addTaskButton.addActionListener(this); // Implementing the Task Button

        // Adding the components like bannerLabel, scrollPane, addTaskButton to frame
        this.getContentPane().add(bannerLabel);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(addTaskButton);
    }

    // Loading a custom font called "LEMONMILK-Light.otf"
    private Font createFont(String resources, float size){
        // Get the font file path
        String filePath = getClass().getClassLoader().getResource(resources).getPath();

        // Check to see if the path contains a folder with space in them
        if(filePath.contains("%20")){
            filePath = getClass().getClassLoader().getResource(resources).getPath()
                    .replaceAll("%20","");
        }

        try{
            File customFontFile = new File(filePath);
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, customFontFile).deriveFont(size);
            return customFont;
        }catch(Exception e){
            System.out.println("Error: "+ e);
        }
        return null;
    } 

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equalsIgnoreCase("Add Task")){
            // Create a task Component
            TaskComponent taskComponent = new TaskComponent(taskComponentPanel);
            taskComponentPanel.add(taskComponent);

            // Make the Previous task appear disabled
            if(taskComponentPanel.getComponentCount()>1){
                TaskComponent previousTask = (TaskComponent) taskComponentPanel.getComponent(
                        taskComponentPanel.getComponentCount() - 2);
                previousTask.getTaskField().setBackground(null);
            }



            // Make the task field request focus after creation
            taskComponent.getTaskField().requestFocus(); // After a task is created, the user’s keyboard input will automatically be directed to the task field.
            repaint(); // It makes sure that the new task field (or any other components added) is visible on the screen.
            revalidate(); // It ensures the layout updates accordingly, adjusting the sizes and positions of components.
        }
    }
}
