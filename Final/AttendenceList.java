import java.util.ArrayList;
import javax.swing.*;

public class AttendenceList extends ArrayList<Attendance>{

    //AttendanceList attributes
    //private ArrayList<Attendance> aList;
    private Roster sRoster;
    private int size;
    private CSVReader fileReader;

    //constructor
    public AttendenceList(Roster sRoster, int size, CSVReader reader)
    {
        this.sRoster = sRoster;
        this.size = size;
        fileReader = reader;
    }

    //display pop-up window with attendee information
    public void displayMessage(Attendance current)
    {
        JDialog message = new JDialog();
        JLabel text = new JLabel(current.getMessage(), SwingConstants.CENTER);
        message.add(text);
        message.setSize(300, 200);
        message.setVisible(true);
    }

    //add new Attendance object
    public void addAttendence(int month, int day, String fileName)
    {
        Attendance newAttendance = new Attendance(month, day, sRoster.getSize(), fileName);
        newAttendance.fill(sRoster, fileReader);
        add(newAttendance);
        displayMessage(newAttendance);
    }

    public int getSize()
    {
        return size;
    }
}



