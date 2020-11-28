// package net.javacode.swing;

import javax.swing.*;
import java.util.ArrayList;

public class AttendenceList {

    //AttendanceList attributes
    private ArrayList<Attendance> aList;
    private Roster sRoster;
    private int size;
    private CSVReader fileReader;
    JDialog message = new JDialog();
    JLabel text = new JLabel("", SwingConstants.CENTER);

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
        text.setText(current.getMessage());
        message.add(text);
        message.setSize(300, 200);
        message.setVisible(true);
    }

    //add new Attendance object
    public void addAttendence(int month, int day, int year, String fileName)
    {
        if (validAttendanceFile(fileName))
        {
            Attendance newAttendance = new Attendance(month, day, year, sRoster.getSize(), fileName);
            newAttendance.fill(sRoster, fileReader);
            aList.add(newAttendance);
            displayMessage(newAttendance);
        }
    }

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

    public ArrayList<Attendance> getAttendance()
    {
        return aList;
    }

    public int getSize()
    {
        return size;
    }

}



