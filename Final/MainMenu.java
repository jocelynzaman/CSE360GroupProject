package net.codejava.swing;

import java.awt.*;
import javax.swing.*;

/**
 * The MainMenu.java class contains the main method for the program. The
 * MainMenu Class extends the JFrame class which is used to display all of the
 * options of the program. This class utilizes the JMenuBar for the GUI to
 * display the two JMenu options, file and about. The file JMenu option has four
 * JMenuItem options LoadARoster, addAttendance, save, and plotData which are
 * added to the menu in the createMenuBar(). The prepareGUI() sets the title and
 * size of the GUI and adds the controlPanel.
 */

public class MainMenu extends JFrame {

	static JMenuBar menuBar;
	static JMenu fileMenu, aboutMenu;
	static JMenuItem loadARosterItem, addAttendanceItem, saveItem, plotDataItem;

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
		loadARosterItem = new JMenuItem("Load A Roster");
		addAttendanceItem = new JMenuItem("Add Attendance");
		saveItem = new JMenuItem("Save");
		plotDataItem = new JMenuItem("Plot");

		// create controller
		MenuOptionListener controller = new MenuOptionListener(this, loadARosterItem, addAttendanceItem, saveItem,
				plotDataItem);
		aboutMenu.addMenuListener(controller);
		loadARosterItem.addActionListener(controller);
		addAttendanceItem.addActionListener(controller);
		saveItem.addActionListener(controller);
		plotDataItem.addActionListener(controller);

		// add menu item to each menu options
		fileMenu.add(loadARosterItem);
		fileMenu.addSeparator();
		fileMenu.add(addAttendanceItem);
		fileMenu.addSeparator();
		fileMenu.add(saveItem);
		fileMenu.addSeparator();
		fileMenu.add(plotDataItem);

		// add menu options to menu bar
		menuBar.add(fileMenu);
		menuBar.add(aboutMenu);
	}

	public static void main(String[] argv) {
		MainMenu mainMenu = new MainMenu();
		mainMenu.prepareGUI();
	}
}