// package net.javacode.swing;

import javax.swing.*;

public class About extends JDialog {

    public About(){
        // prepareGUI();
        JLabel aboutText = new JLabel(setMessage(), SwingConstants.CENTER);
        aboutText.setVerticalAlignment(SwingConstants.CENTER);
        add(aboutText);
    }

    public void prepareGUI(){

        setTitle("About Us");
        setSize(300, 200);
        setVisible(true);
    }

    private String setMessage(){

        String teamInformation = "<html>Name, Email<br/>Jeffery Erskine, jserskin@asu.edu<br/>" +
                "Thao Vu, thaovu@asu.edu<br/>" + "Leif Hilding, lhilding@asu.edu<br/>" + "Jan de Waard, jddewaar@asu.edu<br/>" + "Jocelyn Zaman, jzaman@asu.edu<html>";

        return teamInformation;
    }

}