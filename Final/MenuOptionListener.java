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

class MenuOptionListener implements MenuListener, ActionListener{
    About aboutDialog = new About();
    MainMenu mainView;
    JMenuItem loadARoasterItem, addAttendanceItem, saveItem, plotDataItem;
    JScrollPane tableGUI;
    AttendanceTable attendanceTable = new AttendanceTable();
    PlotData plotData = new PlotData();
    
    //Date Picker
    JDialog dateDialog = new JDialog();
    JPanel datePanel = new JPanel();
    DatePicker picker = new JDatePicker();

    public MenuOptionListener(MainMenu menu, JMenuItem load, JMenuItem add, JMenuItem save, JMenuItem plot)
    {
        mainView = menu;
        loadARoasterItem = load;
        addAttendanceItem = add;
        saveItem = save;
        plotDataItem = plot;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (actionEvent.getSource() == loadARoasterItem){
            try {
                mainView.remove(tableGUI);
                plotData.clearDataset(); //need to clear plot if new roster is loaded
            } catch (NullPointerException e) {
                //There is no tableGUI in mainView.
            }
            attendanceTable.setTableRoster();
            tableGUI = attendanceTable.prepareGUI();
            mainView.add(tableGUI); //prepareGUI returns NULL if user exits menu, does not cause error as far as I can see
        }
        if (actionEvent.getSource() == addAttendanceItem){
            //Show Date Picker
            picker.setTextEditable(true);
            picker.setShowYearButtons(true);
            datePanel.add((JComponent)picker);
            JPanel DatePanel = new JPanel();
            DatePanel.setLayout(new BorderLayout());
            DatePanel.add(datePanel, BorderLayout.WEST);
            BorderLayout fb = new BorderLayout();
            dateDialog.setSize(300,100);
            dateDialog.add(datePanel);
            dateDialog.setVisible(true);
            
            //Setup updated JTable
            if (picker.getModel().isSelected())
            {
                try {
                    mainView.remove(tableGUI);
                } catch (NullPointerException e) {
                    //There is no tableGUI in mainView.
                }
                attendanceTable.updateTableData(picker.getModel().getMonth()+1, picker.getModel().getDay(), plotData);
                tableGUI = attendanceTable.prepareGUI();
                mainView.add(tableGUI);
                mainView.validate();
                mainView.repaint();
            }
        }
        if (actionEvent.getSource() == saveItem){
            try {
                attendanceTable.exportTable();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (actionEvent.getSource() == plotDataItem){
            plotData.prepareGUI();
        }


    }

    @Override
    public void menuSelected(MenuEvent menuEvent) {
        JMenu menuSelected = (JMenu) menuEvent.getSource();
        menuSelected.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                JMenu menuClicked = (JMenu) mouseEvent.getSource();
                if(menuClicked.getText().equals("About")){
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