package modules;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class mainFrame extends JFrame{
	
			
	public mainFrame() {
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024, 768);
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mainFrame jf = new mainFrame();
				mainDisplay md = new mainDisplay(jf);
				jf.setVisible(true);
			}
			
		});

	}

}
