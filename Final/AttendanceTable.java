package net.javacode.swing;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;


public class AttendanceTable {


    public AttendanceTable(){
        // prepareGUI();
    }


    public JScrollPane prepareGUI(){

        String[][] dataCollected = setTableData();

        if (dataCollected.length > 0) {
            JTable attendanceTable = new JTable(dataCollected, setTableHeader().toArray());
            JScrollPane paneGUI = new JScrollPane(attendanceTable);
            attendanceTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            paneGUI.setSize(300, 300);
            paneGUI.setVisible(true);
            return paneGUI;
        }

        return null;
    }

    private ArrayList<String> setTableHeader(){
        ArrayList<String> dynamicColumnHeader = new ArrayList<String>();
        String[] initialHeaders = {"ID", "First Name", "Last Name", "Program", "Level", "ASURITE"};
        dynamicColumnHeader.addAll(Arrays.asList(initialHeaders));
        return dynamicColumnHeader;
    }

    private String[][] setTableData(){
        Search searchFile = new Search();
        String fileName = searchFile.search();
        CSVReader readFile = new CSVReader();
        ArrayList<ArrayList<String>> dynamicdataCollected = readFile.read(fileName);
        String [][] dataCollected = dynamicdataCollected.stream().map(l -> l.stream().toArray(String[]::new)).toArray(String[][]::new);
        System.out.println(dynamicdataCollected.size());

        return dataCollected;
    }

}