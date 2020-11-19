package net.codejava.swing;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class About1 extends JPanel {

	/**
	 * Create the panel.
	 */
	public void About2() {

		
		JFrame TeamIn = new JFrame("Team Information");
        
		TeamIn.setSize( 300, 300 );
		Color yella = new Color(255,255,153);
		
        JLabel label = new JLabel("<html>Name, Email<br/>Jeffery Erskine, jserskin@asu.edu<br/>"
        		+ "Thao Vu, thaovu@asu.edu<br/>your name, email<br/>your name, email<br/>Jocelyn Zaman, jzaman@asu.edu<html>");
        
        JPanel panel = new JPanel();
        panel.add(label);
        panel.setBackground(yella);
        TeamIn.getContentPane().add(panel);
        TeamIn.setVisible(true);	
		
	}
	
}
