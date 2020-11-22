// package net.javacode.swing;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

class MenuOptionListener implements MenuListener, ActionListener{
    About aboutDialog = new About();
    MainMenu mainView;
    JMenuItem loadARoasterItem, addAttendanceItem, saveItem, plotDataItem;

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
            AttendanceTable attendanceTable = new AttendanceTable();
            mainView.add(attendanceTable.prepareGUI()); //prepareGUI returns NULL if user exits menu, does not cause error as far as I can see
        }
        if (actionEvent.getSource() == addAttendanceItem){

        }
        if (actionEvent.getSource() == saveItem){

        }
        if (actionEvent.getSource() == plotDataItem){
            PlotData plotData = new PlotData();
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