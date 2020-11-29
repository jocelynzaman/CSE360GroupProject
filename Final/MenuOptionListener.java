//package net.javacode.swing; Package throws error for me commenting this out.

import org.jdatepicker.DatePicker;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;


//Listeners which controll how the menuitems behave, uses action listeners and 
//mouse listeners to listen for clicks and action events on selected roster items
class MenuOptionListener implements MenuListener, ActionListener {
    About aboutDialog = new About();
    MainMenu mainView;
    JMenuItem loadARosterItem, addAttendanceItem, saveItem, plotDataItem;
    JScrollPane tableGUI;
    AttendanceTable attendanceTable = new AttendanceTable();
    PlotData plotData = new PlotData();

    // Date Picker
    JDialog dateDialog = new JDialog();
    JPanel datePanel = new JPanel();
    JPanel DatePanel = new JPanel();
    DatePicker picker = new JDatePicker();
    JButton dateButton = new JButton("Confirm");

    // flag to check if roster has been loaded
    boolean rosterLoaded = false;

    // Error Dialog box
    JOptionPane errorDialog = new JOptionPane();

    public MenuOptionListener(MainMenu menu, JMenuItem load, JMenuItem add, JMenuItem save, JMenuItem plot) {
        mainView = menu;
        loadARosterItem = load;
        addAttendanceItem = add;
        saveItem = save;
        plotDataItem = plot;
    }

    //Checks for actions performed on the fileMenu items
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        //If the menu item load roster is clicked then a File explorer is opened and the selected roster file is loaded and displayed
        if (actionEvent.getSource() == loadARosterItem) {
            JScrollPane tempTableGUI = null;
            try {
                attendanceTable.setTableRoster(); //calls the setTableRoster() method to create a new roster
                if (!attendanceTable.FileNotFound) {

                    try {
                        mainView.remove(tableGUI); //remove any existing tableGUI
                        plotData.clearDataset(); // need to clear plot if new roster is loaded
                    } catch (NullPointerException e) {
                        // There is no tableGUI in mainView.
                        // Catches the case that the is no initial table GUI
                    }
                    try {
                        tableGUI = attendanceTable.prepareGUI(); //prepares a new GUI with the new info
                        rosterLoaded = true;
                    } catch (NullPointerException e) {
                        // Invalid Column Space
                        rosterLoaded = false;
                    }
                    mainView.add(tableGUI); //failing to create a new gui will add the old gui back

                }
            } catch (NullPointerException pipipupu) { //Exception Handling

                if (tempTableGUI != null) {
                    tableGUI = tempTableGUI;
                    rosterLoaded = true;
                    mainView.add(tableGUI);
                } else {
                    pipipupu.printStackTrace();
                    rosterLoaded = false;
                }

            }
        }
        //If the menu item add attendance is clicked then a file explorer is opened which accepts attendance files
        if (actionEvent.getSource() == addAttendanceItem) {
            //Similar to the load roster method , however this one simplt adds attendance columns to the Roster
            if (rosterLoaded) {
                // Show Date Picker
                picker.setTextEditable(true);
                picker.setShowYearButtons(true);
                datePanel.add((JComponent) picker);
                datePanel.setSize(100, 100);
                dateButton.setSize(50, 50);

                DatePanel.setLayout(new BorderLayout());
                DatePanel.add(datePanel);
                dateDialog.setLayout(new GridLayout(0, 1));
                dateDialog.setSize(300, 100);
                dateDialog.add(datePanel);
                dateDialog.add(dateButton);
                dateDialog.setVisible(true);

                MainMenu.fileMenu.repaint();

                //Listerner of if a date is selected and the Continue button is pressed.

                dateButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent a) {
                        if (picker.getModel().isSelected()) {
                            picker.getModel().setSelected(false); //resets the state of isSelected boolean to prevent crossover between instances of DatePicker
                            try { //Removes existing roster item (same as in Roster)
                                mainView.remove(tableGUI);
                            } catch (NullPointerException e) {
                                // There is no tableGUI in mainView.
                            }
                            //Updates the roster to have an attendance
                            attendanceTable.updateTableData(picker.getModel().getMonth() + 1,
                                    picker.getModel().getDay(), picker.getModel().getYear(), plotData);
                            dateDialog.setVisible(false);
                            tableGUI = attendanceTable.prepareGUI();
                            mainView.add(tableGUI);
                        }
                    }
                });
            } else {
                errorDialog.showMessageDialog(new JFrame(), "Need to load a roster first", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        //If save is selected. It checks that there is an existing roster and there has been attendance added
        if (actionEvent.getSource() == saveItem) {
            if (rosterLoaded) {
                if (attendanceTable.attendanceList.getAttendance().size() > 0) {
                    try {
                        attendanceTable.exportTable();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Need to add attendance first", "Error",
                            JOptionPane.WARNING_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Need to load a roster first", "Error",
                        JOptionPane.WARNING_MESSAGE);
            }

        }
        //plots data checks that there is a roster and that attendance has been added
        if (actionEvent.getSource() == plotDataItem) {
            if (rosterLoaded) {
                if (attendanceTable.attendanceList.getAttendance().size() > 0) {
                    plotData.prepareGUI();
                } else {
                    JOptionPane.showMessageDialog(null, "Need to add attendance first", "Error",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Need to load a roster first", "Error",
                        JOptionPane.WARNING_MESSAGE);
            }
        }

    }

    //Overrides the menuSelected method in mouseListener and checks that the about menu item is clicked.
    //A mouse event listener is needed because otherwise hovering About after selecting File will trigger aboutSelected methods.
    @Override
    public void menuSelected(MenuEvent menuEvent) {
        JMenu menuSelected = (JMenu) menuEvent.getSource();

        menuSelected.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                JMenu menuClicked = (JMenu) mouseEvent.getSource();
                if (menuClicked.getText().equals("About")) {
                    aboutDialog.prepareGUI();
                }

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

    }

    @Override
    public void menuDeselected(MenuEvent menuEvent) {
    }

    @Override
    public void menuCanceled(MenuEvent menuEvent) {
    }

}