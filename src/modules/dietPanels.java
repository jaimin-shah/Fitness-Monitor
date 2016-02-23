package modules;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class dietPanels extends JFrame{
	
	JFrame fr;
	JPanel panelDietMain, panelChooser;
	JButton btnTracker, btnAnalysis;
	
	public dietPanels(JFrame jf) {
		// TODO Auto-generated constructor stub
		fr = jf;
		
		panelDietMain = new JPanel();
		
		fr.getContentPane().add(panelDietMain, BorderLayout.CENTER);
		panelDietMain.setLayout(new BorderLayout(0, 0));
		
		panelChooser = new JPanel();
		panelDietMain.add(panelChooser, BorderLayout.NORTH);
		panelChooser.setLayout(new BorderLayout(0, 0));
		
		btnTracker = new JButton("Tracker");
		btnTracker.setBorder(new EmptyBorder(9, 9, 9, 9));
		btnTracker.setFont(new Font("Tahoma", Font.PLAIN, 19));
		panelChooser.add(btnTracker, BorderLayout.WEST);
		
		btnAnalysis = new JButton("Analysis");
		btnAnalysis.setBorder(new EmptyBorder(9, 9, 9, 9));
		btnAnalysis.setFont(new Font("Tahoma", Font.PLAIN, 19));
		panelChooser.add(btnAnalysis, BorderLayout.EAST);
		
		panelDietMain.setVisible(true);
		fr.revalidate();
		fr.setVisible(true);
		fr.repaint();
	}

}
