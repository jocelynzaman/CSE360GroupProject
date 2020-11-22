// package net.javacode.swing;

import java.awt.*;
import javax.swing.*;

public class MainMenu extends JFrame {

    static JMenuBar menuBar;
    static JMenu fileMenu, aboutMenu;
    static JMenuItem loadARoasterItem, addAttendanceItem, saveItem, plotDataItem;

    public MainMenu() {
        createMenuBar();
        // prepareGUI();

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

        // create controller
        MenuOptionListener controller = new MenuOptionListener(this, loadARoasterItem, addAttendanceItem, saveItem, plotDataItem);
        aboutMenu.addMenuListener(controller);
        loadARoasterItem.addActionListener(controller);
        addAttendanceItem.addActionListener(controller);
        saveItem.addActionListener(controller);
        plotDataItem.addActionListener(controller);

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
}