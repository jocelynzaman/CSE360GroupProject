

import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MainMenu extends JFrame implements ActionListener {

    static JMenuBar menuBar;
    static JMenu fileMenu, aboutMenu;
    static JMenuItem loadARoasterItem, addAttendanceItem, saveItem, plotDataItem;

    public MainMenu() {
        createMenuBar();
        prepareGUI();

    }

    private void prepareGUI() {
        setTitle("CSE360 Final Project");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setJMenuBar(menuBar);
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        add(controlPanel);
        setVisible(true);

    }

    private void createMenuBar() {
        // create a menu bar
        menuBar = new JMenuBar();

        // create menu options
        fileMenu = new JMenu("File");
        aboutMenu = new JMenu("About");

        // create menu items
        loadARoasterItem = new JMenuItem("Load A Roaster");
        addAttendanceItem = new JMenuItem("Add Attendance");
        saveItem = new JMenuItem("Save");
        plotDataItem = new JMenuItem("Plot");

        aboutMenu.addMenuListener(new MenuOptionListener());
        aboutMenu.setMnemonic(KeyEvent.VK_M);

        loadARoasterItem.addActionListener(this);
        addAttendanceItem.addActionListener(this);
        saveItem.addActionListener(this);
        plotDataItem.addActionListener(this);

        //add menu item to each menu options
        fileMenu.add(loadARoasterItem);
        fileMenu.addSeparator();
        fileMenu.add(addAttendanceItem);
        fileMenu.addSeparator();
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(plotDataItem);

        //add menu options to menu bar
        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);


    }

    public static void main(String[] argv){
        MainMenu mainMenu = new MainMenu();
        mainMenu.prepareGUI();
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (actionEvent.getSource() == loadARoasterItem){
            AttendanceTable attendanceTable = new AttendanceTable();
            add(attendanceTable.prepareGUI()); //prepareGUI returns NULL if user exits menu, does not cause error as far as I can see
        }
        if (actionEvent.getSource() == addAttendanceItem){

        }
        if (actionEvent.getSource() == saveItem){

        }
        if (actionEvent.getSource() == plotDataItem){

        }


    }    

}

