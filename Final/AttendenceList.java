/**
 * This class contains an arrayList of Attendance objects and can add new
 * attendance objects
 */

import javax.swing.*;
import java.util.ArrayList;

public class AttendenceList {

    //AttendanceList attributes
    private ArrayList<Attendance> aList; //arrayList of attendance list objects
    private Roster sRoster; //This class uses a roster as an attribute
    private int size;
    private CSVReader fileReader;
    JDialog message = new JDialog(); //message that will be displayed when new attendance object is added
    JLabel text = new JLabel("", SwingConstants.CENTER);

    /**
     * Constructor for the attendenceList class
     * @param sRoster, used to initialize sRoster attribute, roster that will be used for all attendance objects
     * @param size, used to initialize size attrbute
     * @param reader, used to initialize fileReader attribute
     */
    public AttendenceList(Roster sRoster, int size, CSVReader reader)
    {
        aList = new ArrayList<Attendance>();
        this.sRoster = sRoster;
        this.size = size;
        fileReader = reader;
    }

    /**
     * displays a pop-up window with attendee information
     * @param current, current attendabce object whose data is going to be displayed
     */
    public void displayMessage(Attendance current)
    {
        text.setText(current.getMessage());
        message.add(text);
        message.setSize(300, 200);
        message.setVisible(true);
    }

    /**
     * Adds new Attendance object into aList arrayList
     * @param month, used to initialize month attribute of Attendance class
     * @param day, used to initialize day attribute of Attendance class
     * @param year, used to initialize year attribute of Attendance class
     * @param fileName, used to initialize fileName attribute of Attendance class
     * @return true if attendance is added successfully and the the file provided is a valid attendance file, false otherwise
     */
    public boolean addAttendence(int month, int day, int year, String fileName)
    {
        if (validAttendanceFile(fileName))
        {
            Attendance newAttendance = new Attendance(month, day, year, sRoster.getSize(), fileName);
            newAttendance.fill(sRoster, fileReader);
            aList.add(newAttendance);
            displayMessage(newAttendance);
            return true;
        }
        return false;
    }

    /**
     * Checks if the inputed file is a valid attendence file by ensuring that
     * its width does not exceed 2, returns true if valid, false otherwise
     * @param fileName, name of the file that will checked
     * @return true if the file is valid, false otherwise
     */
    public boolean validAttendanceFile(String fileName)
    {
        ArrayList<ArrayList<String>> table = fileReader.read(fileName);
        for (int i = 0; i < table.size(); i++)
        {
            if (table.get(i).size() > 2)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Accessor method of the aList attribute
     * @return aList
     */
    public ArrayList<Attendance> getAttendance()
    {
        return aList;
    }

    /**
     * Acessor method for the size attribute
     * @return size attibute
     */
    public int getSize()
    {
        return size;
    }

}


