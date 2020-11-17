package net.codejava.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ProjS extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjS frame = new ProjS();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProjS() {
		setTitle("CSE360 Final Project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 417);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Load a Roster");
		mnNewMenu.add(mntmNewMenuItem);
		
		JSeparator separator_1 = new JSeparator();
		mnNewMenu.add(separator_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Add Attendance");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JSeparator separator_2 = new JSeparator();
		mnNewMenu.add(separator_2);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Save");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JSeparator separator_3 = new JSeparator();
		mnNewMenu.add(separator_3);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Plot Data");
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_1 = new JMenu("About");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Team Information");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				JFrame TeamIn = new JFrame("Team Information");
               
				TeamIn.setSize( 300, 300 );
				Color yella = new Color(255,255,153);
				
                JLabel label = new JLabel("<html>Name, Email<br/>Jeffery Erskine, jserskin@asu.edu<br/>"
                		+ "your name, email<br/>your name, email<br/>your name, email<br/>your name, email<html>");
//                JLabel label1 = new JLabel("<html>Jeffery Erskine, jserskin@asu.edu");
//                JLabel label2 = new JLabel("your name, email");
//                JLabel label3 = new JLabel("your name, email");
//                JLabel label4 = new JLabel("your name, email");
//                JLabel label5 = new JLabel("your name, email");
                
                JPanel panel = new JPanel();
                panel.add(label);
//                panel.add(label1);
//                panel.add(label2);
//                panel.add(label3);
//                panel.add(label4);
//                panel.add(label5);
                panel.setBackground(yella);
                TeamIn.add(panel);
				
                TeamIn.setVisible(true);		
				
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_4);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// window.setVisible(true);
	}

}
