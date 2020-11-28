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

// import java.util.Properties;

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

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (actionEvent.getSource() == loadARosterItem){
            JScrollPane tempTableGUI = null;
            try {
                attendanceTable.setTableRoster();
                //System.out.println("Size of Dynamic " + attendanceTable.dynamicdataCollected.size());
                if (!attendanceTable.FileNotFound) {

                    //System.out.println("reached");
                    try {
                        mainView.remove(tableGUI);
                        plotData.clearDataset(); //need to clear plot if new roster is loaded
                    } catch (NullPointerException e) {
                        //There is no tableGUI in mainView.
                    }
                    try {
                        tableGUI = attendanceTable.prepareGUI();
                        rosterLoaded = true;
                    } catch (NullPointerException e) {
                        //Invalid Column Space
                        rosterLoaded = false;
                    }
                    mainView.add(tableGUI);

                }
            } catch(NullPointerException pipipupu) {

                if (tempTableGUI != null) {
                    tableGUI = tempTableGUI;
                    rosterLoaded = true;
                    mainView.add(tableGUI);
                }
                else {
                    pipipupu.printStackTrace();
                    rosterLoaded = false;
                }


            }
        }
        if (actionEvent.getSource() == addAttendanceItem) {
            
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

                dateButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent a) {
                        if (picker.getModel().isSelected()) {
                            picker.getModel().setSelected(false);
                            try {
                                mainView.remove(tableGUI);
                            } catch (NullPointerException e) {
                                // There is no tableGUI in mainView.
                            }
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
        if (actionEvent.getSource() == saveItem) {
            if (rosterLoaded) {
                if (attendanceTable.attendanceList.getAttendance().size() > 0) {
                    try {
                        attendanceTable.exportTable();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Need to add attendance first", "Error", JOptionPane.WARNING_MESSAGE);
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Need to load a roster first", "Error", JOptionPane.WARNING_MESSAGE);
            }
            
        }
        if (actionEvent.getSource() == plotDataItem) {
            if (rosterLoaded) {
                if (attendanceTable.attendanceList.getAttendance().size() > 0) {
                    plotData.prepareGUI();
                } else {
                    JOptionPane.showMessageDialog(null, "Need to add attendance first", "Error", JOptionPane.WARNING_MESSAGE);
                } 
            } else {
                JOptionPane.showMessageDialog(null, "Need to load a roster first", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }

    }

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
        //System.out.println("Menu Deselected");
    }

    @Override
    public void menuCanceled(MenuEvent menuEvent) {
        //System.out.println("Menu Canceled");
    }

}