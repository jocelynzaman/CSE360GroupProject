// package net.javacode.swing;

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

    //Constructor for the attendenceList class
    public AttendenceList(Roster sRoster, int size, CSVReader reader)
    {
        aList = new ArrayList<Attendance>();
        this.sRoster = sRoster;
        this.size = size;
        fileReader = reader;
    }

    //display pop-up window with attendee information
    public void displayMessage(Attendance current)
    {
        text.setText(current.getMessage());
        message.add(text);
        message.setSize(300, 200);
        message.setVisible(true);
    }

    //add new Attendance object into aList arrayList
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

    //Checks if the inputed file is a valid attendence file by ensuring that
    //its width does not exceed 2, returns true if valid, false otherwise
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

    //Returns aList attribute
    public ArrayList<Attendance> getAttendance()
    {
        return aList;
    }

    //Returns size attribute
    public int getSize()
    {
        return size;
    }

}


