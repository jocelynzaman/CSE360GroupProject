//package net.javacode.swing;

import javax.swing.*;

public class About extends JDialog {

    public About(){
        // prepareGUI();
    }

    public void prepareGUI(){

        setTitle("About Us");
        setModalityType(ModalityType.APPLICATION_MODAL); //forces user to close Dialog box before selecting anything else
        JLabel aboutText = new JLabel(setMessage());
        add(aboutText);
        setSize(200, 200);
        setVisible(true);

    }

    private String setMessage(){

        String teamInformation = "<html>Name, Email<br/>Jeffery Erskine, jserskin@asu.edu<br/>" +
                "Thao Vu, thaovu@asu.edu<br/>" + "your name, email<br/>" + "your name, email<br/>" + "Jocelyn Zaman, jzaman@asu.edu<html>";

        return teamInformation;
    }

}
