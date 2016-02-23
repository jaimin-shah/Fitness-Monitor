package modules;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.Color;

import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import javax.swing.JTabbedPane;


public class Display extends JFrame {

	private JPanel contentPane;
	private JTextField searchField;
	private JTextField textFieldDuration;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Display frame = new Display();
					frame.validate();
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
	public Display() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel_top = new JPanel();
		panel_top.setBackground(new Color(255, 69, 0));
		contentPane.add(panel_top, BorderLayout.NORTH);
		panel_top.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDate = new JLabel("Date and Time");
		lblDate.setForeground(new Color(255, 255, 255));
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_top.add(lblDate, BorderLayout.CENTER);
		
		JLabel lblTitle = new JLabel("Fitness Monitor");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_top.add(lblTitle, BorderLayout.WEST);
		
		JMenuBar menuBar = new JMenuBar();
		panel_top.add(menuBar, BorderLayout.EAST);
		
		JMenu mnOptions = new JMenu("Options");
		mnOptions.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		menuBar.add(mnOptions);
		
		JMenuItem mnitemProfile = new JMenuItem("Profile");
		mnitemProfile.setIcon(new ImageIcon("C:\\Users\\a\\workspace\\project Work\\Images\\profile.jpg"));
		mnitemProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JMenuItem mnitemHome = new JMenuItem("Home");
		mnitemHome.setIcon(new ImageIcon("C:\\Users\\a\\workspace\\project Work\\Images\\home.png"));
		mnitemHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnitemHome.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mnOptions.add(mnitemHome);
		mnitemProfile.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mnOptions.add(mnitemProfile);
		
		JMenuItem mnitemSignOut = new JMenuItem("Sign Out");
		mnitemSignOut.setIcon(new ImageIcon("C:\\Users\\a\\workspace\\project Work\\Images\\search.gif"));
		mnitemSignOut.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mnOptions.add(mnitemSignOut);
		
		JPanel panel_Bottom = new JPanel();
		contentPane.add(panel_Bottom, BorderLayout.SOUTH);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_Bottom.add(lblSearch);
		
		searchField = new JTextField();
		searchField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_Bottom.add(searchField);
		searchField.setColumns(10);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
		
		
		
		//		JButton btnTryButton = new JButton(new ImageIcon("Images\\nirma.png"));

		// till here all constant
		

	
	}
}
