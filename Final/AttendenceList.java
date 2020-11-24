package net.javacode.swing;

import javax.swing.*;
import java.util.ArrayList;

public class AttendenceList {

    //AttendanceList attributes
    private ArrayList<Attendance> aList;
    private Roster sRoster;
    private int size;
    private CSVReader fileReader;

    //constructor
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
        aList.add(newAttendance);
        displayMessage(newAttendance);
        size++;
        // setChanged();
        // notifyObservers();
    }

    public ArrayList<Attendance> getAttendance()
    {
        return aList;
    }

    public int getSize()
    {
        return size;
    }

}



