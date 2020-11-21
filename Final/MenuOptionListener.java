package net.javacode.swing;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class MenuOptionListener implements MenuListener {
    About aboutDialog = new About();

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