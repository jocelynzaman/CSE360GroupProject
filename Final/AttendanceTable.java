
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class generate GUI table for collected data when a roster csv file is loaded
 * and update the table when an attendance csv file is selected
 */

public class AttendanceTable{
    CSVReader readFile;
    DefaultTableModel tableModel;
    JTable attendanceTable;
    String[][] dataCollected;
    JScrollPane paneGUI;
    ArrayList<String> dynamicColumnHeader;
    ArrayList<ArrayList<String>> dynamicdataCollected;
    Roster loadARoster;
    AttendenceList attendanceList;

    //Error Dialog box
    JOptionPane errorDialog = new JOptionPane();

    //flag for duplicate date
    boolean duplicateDate = false;

    //flag for invalid attendance file
    boolean validAttendance = false;

    boolean FileNotFound = false;
    private String ExistedFileName = "";


    /**
     * Constructor for Attendance Table class: make a new JTable
     */
    public AttendanceTable(){
        // prepareGUI();
        attendanceTable = new JTable();
    }


    /**
     * @return GUI for attendance table in a JTable, then add it in a JScrollPane
     */
    public JScrollPane prepareGUI(){
        // dynamicColumnHeader = setTableHeader();
        // dataCollected = setTableData();

        if (dataCollected.length > 0) {
            paneGUI = new JScrollPane(attendanceTable);
            attendanceTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            paneGUI.setSize(300, 500);
            paneGUI.setVisible(true);
            return paneGUI;
        }

        return null;
    }

    /**
     *  This method always et up the roster table with the input data from roster csv file
     */
    public void setTableRoster()
    {
        dynamicColumnHeader = setTableHeader();
        dataCollected = setTableData();
        tableModel = new DefaultTableModel(dataCollected, dynamicColumnHeader.toArray());
        attendanceTable.setModel(tableModel);
    }

    /**
     * This method always set up headers for roster table
     * @return an array of column headers for 6 fields
     */
    private ArrayList<String> setTableHeader(){
        ArrayList<String> columnHeader = new ArrayList<String>();
        String[] initialHeaders = {"ID", "First Name", "Last Name", "Program", "Level", "ASURITE"};
        columnHeader.addAll(Arrays.asList(initialHeaders));
        return columnHeader;
    }

    /**
     * This method always generate data for Roster table if the input file from user is valid
     * @return a fixed size 2D array of table's data
     */
    private String[][] setTableData(){
        FileNotFound = false;
        Search searchFile = new Search();
        String fileName = searchFile.search();
        if (fileName == "FILE_NOT_OPEN") {
            if (ExistedFileName != "") {
                System.out.println("Reached this");
                fileName = ExistedFileName;
            }

        }
        if (fileName != "FILE_NOT_OPEN"){
            ExistedFileName = fileName;
            readFile = new CSVReader();
            loadARoster = new Roster(readFile);
            dynamicdataCollected = loadARoster.fill(fileName);
            if (dynamicdataCollected.size() > 0) {
                dataCollected = dynamicdataCollected.stream().map(l -> l.stream().toArray(String[]::new)).toArray(String[][]::new);
                attendanceList = new AttendenceList(loadARoster, loadARoster.getSize(), readFile);
            }
            System.out.println(dynamicdataCollected.size());
        } else {
            FileNotFound = true;
        }

        return dataCollected;
    }


    /**
     * This method update the table with attendace data from attendance csv file and add in the table GUI
     * a separate column for the date the attendance is collected
     * @param month : user input for the month picked
     * @param day : user input for the day picked
     * @param year : user input for the year picked
     */
    public void updateTableData(int month, int day, int year)
    {
        Search searchFile = new Search();
        String fileName = searchFile.search();
        if (fileName != "FILE_NOT_OPEN")
        {
            //check for duplicate dates
            for (int i = 0; i < attendanceList.getAttendance().size(); i++)
            {
                if (attendanceList.getAttendance().get(i).getMonth().equals(attendanceList.getAttendance().get(i).convertMonth(month)) 
                && attendanceList.getAttendance().get(i).getDay() == day && attendanceList.getAttendance().get(i).getYear() == year)
                {
                    duplicateDate = true;
                }
            }
            if (!duplicateDate)
            {
                validAttendance = attendanceList.addAttendence(month, day, year, fileName);
                if (validAttendance)
                {
                    String header = attendanceList.getAttendance().get(attendanceList.getAttendance().size()-1).convertMonth(month) + " " + day + " " + year;
                    Object columnData[] = attendanceList.getAttendance().get(attendanceList.getAttendance().size()-1).getData();
                    tableModel.addColumn(header, columnData);

                    //when attendance is added, plot needs to be updated
                    plot.createDataset(attendanceList.getAttendance().get(attendanceList.getAttendance().size()-1).convertMonth(month), day, year, attendanceList.getAttendance().get(attendanceList.getAttendance().size()-1).getTimes());
                }
                else
                {
                    errorDialog.showMessageDialog(new JFrame(), "Invalid attendance file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }  
            else
            {
                errorDialog.showMessageDialog(new JFrame(), "This date has already been added to the table.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            duplicateDate = false;
        }
    }

    /**
     * This method save the table and export the table's data and headers into a separated
     * csv file named AttendanceTableCollected in the user's local machine
     * @throws IOException
     */
    public void exportTable() throws IOException {
        File exportFile = new File("AttendanceTableCollected.csv");
        TableModel model = attendanceTable.getModel();
        FileWriter out = new FileWriter(exportFile);

        out.write(model.getColumnName(0));
        for (int i = 1; i < model.getColumnCount(); i++) {
            out.write(", " + model.getColumnName(i));
        }
        out.write("\n");
        for (int i = 0; i < model.getRowCount(); i++) {
            out.write(model.getValueAt(i, 0).toString());
            for (int j = 1; j < model.getColumnCount(); j++) {
                out.write( ", " + model.getValueAt(i, j).toString());
            }
            out.write("\n");
        }
        out.close();
        System.out.println("write out to: " + exportFile);
    }

}