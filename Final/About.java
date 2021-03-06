package net.codejava.swing;

import javax.swing.*;

/**
 * The About.java class is a menu option that is designed to display the team
 * information once it is clicked. The About class extends JDialog class to
 * create a new window to display the team information.  
 */

public class About extends JDialog {
	
	/** 
	 * The About() creates a new JLabel.
	 */
	public About() {
		JLabel aboutText = new JLabel(setMessage(), SwingConstants.CENTER);
		aboutText.setVerticalAlignment(SwingConstants.CENTER);
		add(aboutText);
	}
	/**
	 * The prepareGUI() sets the title and the size of the window.
	 */
	public void prepareGUI() {
		setTitle("About Us");
		setSize(300, 200);
		setVisible(true);
	}
		
	/**
	 * The setMessage() creates and returns String teamInformation which includes the names of the team members of the project.
	 * @return teamInformation
	 */
	private String setMessage() {
		String teamInformation = "<html>Name, Email<br/>Jeffery Erskine, jserskin@asu.edu<br/>"
				+ "Thao Vu, thaovu@asu.edu<br/>" + "Leif Hilding, lhilding@asu.edu<br/>"
				+ "Jan de Waard, jddewaar@asu.edu<br/>" + "Jocelyn Zaman, jzaman@asu.edu<html>";

		return teamInformation;
	}

}