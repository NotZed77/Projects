package resources.P3ToDoList;
import java.awt.*;

public class CommonConstants {
    // Frame Config
    public static final Dimension GUI_SIZE = new Dimension(540,760);

    // Banner Config
    public static final Dimension BANNER_SIZE = new Dimension(GUI_SIZE.width, 50);

    // Task Panel Config
    public static final Dimension TASKPANEL_SIZE = new Dimension(GUI_SIZE.width - 30, GUI_SIZE.height - 175);

    // Add Task Button Config
    public static final Dimension ADDTASK_BUTTON_SIZE = new Dimension(GUI_SIZE.width, 50);

    // Task Component Config
    public static final Dimension TASKFIELD_SIZE = new Dimension((int)(TASKPANEL_SIZE.width*0.80),50);

    // Check Box Config
    public static final Dimension CHECKBOX_SIZE = new Dimension((int)(TASKPANEL_SIZE.width*0.05),50);

    // Delete Button Config
    public static final Dimension DELETE_BUTTON_SIZE = new Dimension((int)(TASKPANEL_SIZE.width*0.12),50);
}
