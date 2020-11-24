// package net.javacode.swing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

public class AttendanceTable{
    DefaultTableModel tableModel;
    JTable attendanceTable;
    String[][] dataCollected;
    JScrollPane paneGUI;
    ArrayList<String> dynamicColumnHeader;
    ArrayList<ArrayList<String>> dynamicdataCollected;
    Roster loadARoster;
    AttendenceList attendanceList;


    public AttendanceTable(){
        // prepareGUI();
        attendanceTable = new JTable();
    }


    public JScrollPane prepareGUI(){
        // dynamicColumnHeader = setTableHeader();
        // dataCollected = setTableData();

        if (dataCollected.length > 0) {
            paneGUI = new JScrollPane(attendanceTable);
            attendanceTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            paneGUI.setSize(300, 300);
            paneGUI.setVisible(true);
            return paneGUI;
        }

        return null;
    }

    public void setTableRoster()
    {
        dynamicColumnHeader = setTableHeader();
        dataCollected = setTableData();
        tableModel = new DefaultTableModel(dataCollected, dynamicColumnHeader.toArray());
        attendanceTable.setModel(tableModel);

    }

    private ArrayList<String> setTableHeader(){
        dynamicColumnHeader = new ArrayList<String>();
        String[] initialHeaders = {"ID", "First Name", "Last Name", "Program", "Level", "ASURITE"};
        dynamicColumnHeader.addAll(Arrays.asList(initialHeaders));
        return dynamicColumnHeader;
    }

    private String[][] setTableData(){
        Search searchFile = new Search();
        String fileName = searchFile.search();
        CSVReader readFile = new CSVReader();
        loadARoster = new Roster(readFile);
        dynamicdataCollected = loadARoster.fill(fileName);
        dataCollected = dynamicdataCollected.stream().map(l -> l.stream().toArray(String[]::new)).toArray(String[][]::new);
        System.out.println(dynamicdataCollected.size());

        return dataCollected;
    }


    public void updateTableData(int month, int day, PlotData plot)
    {
        Search searchFile = new Search();
        String fileName = searchFile.search();
        CSVReader readFile = new CSVReader();
        attendanceList = new AttendenceList(loadARoster, loadARoster.getSize(), readFile);
        attendanceList.addAttendence(month, day, fileName);
        String header = attendanceList.getAttendance().get(attendanceList.getAttendance().size()-1).convertMonth(month) + " " + day;
        Object columnData[] = attendanceList.getAttendance().get(attendanceList.getAttendance().size()-1).getData();
        tableModel.addColumn(header, columnData);

        //when attendance is added, plot needs to be updated
        plot.createDataset(attendanceList.getAttendance().get(attendanceList.getAttendance().size()-1).convertMonth(month), day, attendanceList.getAttendance().get(attendanceList.getAttendance().size()-1).getTimes());
    }
}