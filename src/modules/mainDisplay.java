package modules;

import fitness.*;

import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.sun.glass.ui.Pixels.Format;

import java.util.logging.Level;
import java.util.logging.Logger;

public class mainDisplay {
	
	JFrame fr;
	private JPanel contentPane;
	JTextField searchField, txtAddFood;
	JPanel panel_top, panel_Bottom, panel_Central;
	JLabel lblDate, lblSearch;
	JMenuBar menuBar;
	JMenu mnOptions;
	JMenuItem mnitemProfile, mnitemSignOut, mnitemHome;
	static JDatePickerImpl datePicker, datePicker2;
	dietImport DI;
	MachineImport MI;
	CheckValidity cv = new CheckValidity();
	temp details;
	
	public mainDisplay(JFrame jf) {
		// TODO Auto-generated constructor stub
		
		fr = jf;
		
		DI = new dietImport();
		DI.startImport();
		
		MI = new MachineImport();
		MI.startImport();
		
		details = new temp();
		details.detailsUpload();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		fr.add(contentPane);
		
		panel_top = new JPanel();
		panel_top.setBackground(new Color(255,69,0));
		contentPane.add(panel_top, BorderLayout.NORTH);
		panel_top.setLayout(new BorderLayout(0, 0));
		
		lblDate = new JLabel("Fitness Monitor");
		lblDate.setForeground(new Color(255, 255, 255));
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_top.add(lblDate, BorderLayout.CENTER);
			
		menuBar = new JMenuBar();
		panel_top.add(menuBar, BorderLayout.EAST);
		
		mnOptions = new JMenu("Options");
		mnOptions.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		menuBar.add(mnOptions);
		
		mnitemHome = new JMenuItem("Home");
		mnitemHome.setIcon(new ImageIcon("Images\\home.png"));
		mnitemHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				homePaneSwitch();
			}
		});
		mnitemHome.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mnOptions.add(mnitemHome);
		
		mnitemProfile = new JMenuItem("Profile");
		mnitemProfile.setIcon(new ImageIcon("Images\\profile.jpg"));
		mnitemProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profilePaneSwitch();
				
			}
		});
		mnitemProfile.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mnOptions.add(mnitemProfile);
		
		mnitemSignOut = new JMenuItem("Sign Out");
		mnitemSignOut.setIcon(new ImageIcon("Images//images.jpg"));
		
		mnitemSignOut.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				temp.setId(Integer.MIN_VALUE);
				fr.dispose();
				System.gc();
                                JFrame f =new JFrame();
                                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                f.setSize(800,600);
				//Fitness fa = new Fitness();
				login lg = new login(f);	
				
			}
			
		});
		mnitemSignOut.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mnOptions.add(mnitemSignOut);
		
		panel_Bottom = new JPanel();
		contentPane.add(panel_Bottom, BorderLayout.SOUTH);
		
		lblSearch = new JLabel(new ImageIcon("Images\\search.gif"));
		
		lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_Bottom.add(lblSearch);
		
		searchField = new JTextField();
		searchField.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String s[] = searchField.getText().split(" ");
				String URLWord = "https://www.google.co.in/search?q=";
				for(int i=0 ; i<s.length ; i++){
					URLWord += s[i]+"+";
				}
				try{
					// Create Desktop object
		            Desktop d=Desktop.getDesktop();

		            // Browse a URL, for example www.facebook.com
		            d.browse(new URI(URLWord)); 
		            // This open facebook.com in your default browser.
		        }
		        catch(Exception ex){
		            ex.printStackTrace();
		        }				
			}
			
		});
		searchField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_Bottom.add(searchField);
		searchField.setColumns(10);
		
		panel_Central = new JPanel();
		contentPane.add(panel_Central, BorderLayout.CENTER);
		panel_Central.setLayout(new BorderLayout(0, 0));
		
		homePanelMaker();
		profilePanelMaker();
		dietTrackerPanelMaker();
		gymTrackerPanelMaker();
		
		
		}
	
	//Home Panel creator
	
	JPanel panelHome;
	JButton btnGymTracker, btnDietTracker;
	
	protected void homePanelMaker(){
		

		panelHome = new JPanel();
		panel_Central.add(panelHome, BorderLayout.CENTER);
		panelHome.setLayout(new BorderLayout(0, 0));
		
		
		btnGymTracker = new JButton(new ImageIcon("Images\\gym.png"));
		btnGymTracker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				gymTrackerPanelSwitch();
			}
		});
		btnGymTracker.setBorder(new EmptyBorder(9, 9, 9, 9));
		btnGymTracker.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelHome.add(btnGymTracker, BorderLayout.WEST);
		
		btnDietTracker = new JButton(new ImageIcon("Images\\diet.png"));
		
		//diet tracker action listener
		
		btnDietTracker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dietTrackerPanelSwitch();
			}
		});
		btnDietTracker.setBorder(new EmptyBorder(9, 9, 9, 9));
		btnDietTracker.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelHome.add(btnDietTracker, BorderLayout.EAST);
	}
	
	//Profile panel making here.
	
	JPanel panelProfile, panelProfileDisplay, panelProfileExtras;
	JLabel lblUserId, lblUID, lblName, labelNm, lblAge, labelAg, lblSex, labelSX, lblDateOfBirth, labelDOB, lblHeight, labelHT, lblWeight, labelWT;
	JButton changeProfile;
	
	protected void profilePanelMaker(){
		
		panelProfile = new JPanel();
		panelProfile.setLayout(new BoxLayout(panelProfile, BoxLayout.X_AXIS));
		
		panelProfileDisplay = new JPanel();
		panelProfileDisplay.setBackground(new Color(255, 228, 181));
		panelProfile.add(panelProfileDisplay);
		panelProfileDisplay.setLayout(null);
		
		int x = 223, w = 122  , h = 28, y = 120;
		
		lblUserId = new JLabel("User Id :");
		lblUserId.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblUserId.setBounds(223, 120, 122, 28);
		panelProfileDisplay.add(lblUserId);
		
		lblUID = new JLabel("");
		lblUID.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblUID.setBounds(357, 120, w+78, 28);
		panelProfileDisplay.add(lblUID);
		
		y = y+2*h;
		
		lblName = new JLabel("Name :");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblName.setBounds(x, y, w, h);
		panelProfileDisplay.add(lblName);
		
		labelNm = new JLabel("");
		labelNm.setFont(new Font("Tahoma", Font.PLAIN, 21));
		labelNm.setBounds(x+134, y, w+78, h);
		panelProfileDisplay.add(labelNm);
		
		y = y+2*h;
		
		lblAge = new JLabel("Age :");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblAge.setBounds(x, y, w, h);
		panelProfileDisplay.add(lblAge);
		
				
		labelAg = new JLabel("");
		labelAg.setFont(new Font("Tahoma", Font.PLAIN, 21));
		labelAg.setBounds(x+134, y, w+178, h);
		panelProfileDisplay.add(labelAg);
		
		y = y+2*h;
		
		lblSex = new JLabel("Sex :");
		lblSex.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblSex.setBounds(x, y, w, h);
		panelProfileDisplay.add(lblSex);
		
		labelSX = new JLabel("");
		labelSX.setFont(new Font("Tahoma", Font.PLAIN, 21));
		labelSX.setBounds(x+134, y, w+78, h);
		panelProfileDisplay.add(labelSX);
		
		y = y+2*h;
		
		lblDateOfBirth = new JLabel("DOB:");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblDateOfBirth.setBounds(x, y, w, h);
		panelProfileDisplay.add(lblDateOfBirth);
		
		labelDOB = new JLabel("");
		labelDOB.setFont(new Font("Tahoma", Font.PLAIN, 21));
		labelDOB.setBounds(x+134, y, w+78, h);
		panelProfileDisplay.add(labelDOB);
		
		y = y+2*h;
		
		lblHeight = new JLabel("Height :");
		lblHeight.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblHeight.setBounds(x, y, w, h);
		panelProfileDisplay.add(lblHeight);
		
		labelHT = new JLabel("");
		labelHT.setFont(new Font("Tahoma", Font.PLAIN, 21));
		labelHT.setBounds(x+134, y, w+78, h);
		panelProfileDisplay.add(labelHT);
		
		y = y+2*h;
		
		lblWeight = new JLabel("Weight :");
		lblWeight.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblWeight.setBounds(x, y, w, h);
		panelProfileDisplay.add(lblWeight);
		
		labelWT = new JLabel("");
		labelWT.setFont(new Font("Tahoma", Font.PLAIN, 21));
		labelWT.setBounds(x+134, y, w+78, h);
		panelProfileDisplay.add(labelWT);
		
		y = y+2*h;
		
			
		changeProfile = new JButton("Change Profile Details");
		
		changeProfile.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				SwingUtilities.invokeLater(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						ProfileChanger pc = new ProfileChanger();
                                                panelProfileDisplay.repaint();
                                                panelProfileDisplay.revalidate();
						
					}
					
				});
				
			}
			
		});
		
		changeProfile.setFont(new Font("Tahoma", Font.PLAIN, 21));
		changeProfile.setBounds(x, y, w+130, h);
		panelProfileDisplay.add(changeProfile);
		//Profile panel extras
		panelProfileExtras = new JPanel();
		panelProfile.add(panelProfileExtras);
	}
	
	//Diet Tracker
	
	JTabbedPane panelDietMain;
	
	protected void dietTrackerPanelMaker(){
		
		dietTrackerPanelStatusWorks();
		dietTrackerPanelFoodChooser();
				
		panelDietMain = new JTabbedPane();
		panelDietMain.setFont(new Font("Tahoma", Font.PLAIN, 20));
		//panelDietMain.setSelectedIndex(-1);
		//add this pane
		
		panelDietMain.addTab("Diet Tracker", panelDietTrackerControls);
		panelDietMain.addTab("Select Diet", panelFoodSelector);
		//panelDietMain.addTab("Analysis", panelGraphAnalysisDisplayer);
		
		
		
		
	}
	
	//this sets up food selector panel
	
	JPanel panelFoodSelector, panelFoodDetailsHolder, baseDetailsHolder;
	JScrollPane scrollPane;
	JList<String> dietJList;
	JLabel lblCalories, lblCalorieValue, lblCal;
	JButton btnAddFood;
		
		
	// this panel sets up diet tracker "tracker" component
	
	JPanel panelDietTrackerControls;
	JLabel lblTarget, lblTargethere, lblConsumed, lblConsumedDisplayer, lblRemaining, lblShowRemaining;
	JButton btnEditTarget, btnAddTarget, resetDiets;
	Integer remaining, consumed;
	
	protected void dietTrackerPanelStatusWorks(){
		
		panelDietTrackerControls = new JPanel();
		panelDietTrackerControls.setLayout(new GridBagLayout());
		
		lblTarget = new JLabel("Target :");
		lblTarget.setFont(new Font("Tahoma", Font.PLAIN, 19));
		addComponent(panelDietTrackerControls, lblTarget, 1, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		
		lblTargethere = new JLabel("0");
		lblTargethere.setFont(new Font("Tahoma", Font.PLAIN, 19));
		addComponent(panelDietTrackerControls, lblTargethere, 2, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		btnEditTarget = new JButton(new ImageIcon("Images\\edit.png"));
		btnEditTarget.setFont(new Font("Tahoma", Font.PLAIN, 19));
		addComponent(panelDietTrackerControls, btnEditTarget, 3, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		lblConsumed = new JLabel("Consumed :");
		lblConsumed.setFont(new Font("Tahoma", Font.PLAIN, 19));
		addComponent(panelDietTrackerControls, lblConsumed, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		
		lblConsumedDisplayer = new JLabel("0");
		lblConsumedDisplayer.setFont(new Font("Tahoma", Font.PLAIN, 19));
		addComponent(panelDietTrackerControls, lblConsumedDisplayer, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		lblRemaining = new JLabel("Remaining :");
		lblRemaining.setFont(new Font("Tahoma", Font.PLAIN, 19));
		addComponent(panelDietTrackerControls, lblRemaining, 1, 2, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		
		lblShowRemaining = new JLabel("0");
		lblShowRemaining.setFont(new Font("Tahoma", Font.PLAIN, 19));
		addComponent(panelDietTrackerControls, lblShowRemaining, 2, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model,p);
        datePicker2 = new JDatePickerImpl(datePanel2,new DateComponentFormatter());
        addComponent(panelDietTrackerControls, datePicker2, 3, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		btnEditTarget.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String targetInput = JOptionPane.showInputDialog("Enter Target Calories");
				
				if(targetInput != null && targetInput.length() != 0){
					lblTargethere.setText(targetInput);
					consumed = Integer.valueOf(lblConsumedDisplayer.getText());
					remaining = (Integer.valueOf(lblTargethere.getText()) - consumed);
					lblShowRemaining.setText(String.valueOf(remaining));
				}
				
			}
			
		});
		
		btnAddTarget = new JButton("Finish Consumption");
		/*
		if(!(cv.validate("consumption"))){
			btnAddTarget.setEnabled(false);
		}
		*/
		btnAddTarget.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				updateDBDietConsumption();
			}
			
		});
		
		btnAddTarget.setFont(new Font("Tahoma", Font.PLAIN, 19));
		addComponent(panelDietTrackerControls, btnAddTarget, 2, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		resetDiets = new JButton("Reset");
		resetDiets.setFont(new Font("Tahoma", Font.PLAIN , 19));
		resetDiets.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				lblShowRemaining.setText("0");
				lblConsumedDisplayer.setText("0");
				lblTargethere.setText("0");
			}
		});
		
		addComponent(panelDietTrackerControls, resetDiets, 4, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
				
	}
	

	// this sets up graphical analysis panel
	
	JPanel panelGraphAnalysisDisplayer, panelGraphDisplay, panelGraphMode;
	JLabel lblRemoveThisLabel;
	JButton btnSevenDay, btnThirtyDay;
	
	// main GYm GUI
	
	JTabbedPane panelGymMain;
	
	protected void gymTrackerPanelMaker(){
		
		gymTrackerStatusPanel();
		gymEquipmentChooser();
		
		
		panelGymMain = new JTabbedPane();
		panelGymMain.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		panelGymMain.add("Tracker", panelGymStatus);
		panelGymMain.add("Choose Activity", panelGymEquipmentChoose);
		
	}
	
	
	
	// this creates panel to show current gym stats
	
	JPanel panelGymStatus;
	JLabel lblGymTargetShow, lblEnterTarget, lblCaloriesBurned, lblShowCaloriesBurned, lblRemainingCalories, lblShowRemainingCalories;
	JButton btnGymsetTarget, finishWorkout, resetGym;
	
	
	protected void gymTrackerStatusPanel(){
		
		panelGymStatus = new JPanel();
		panelGymStatus.setLayout(new GridBagLayout());
		
		lblGymTargetShow = new JLabel("Target :");
		lblGymTargetShow.setFont(new Font("Tahoma", Font.PLAIN, 19));
		addComponent(panelGymStatus, lblGymTargetShow, 1, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		
		lblEnterTarget = new JLabel("0");
		lblEnterTarget.setFont(new Font("Tahoma", Font.PLAIN, 19));
		addComponent(panelGymStatus, lblEnterTarget, 2, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		btnGymsetTarget = new JButton(new ImageIcon("Images\\edit.png"));	
		btnGymsetTarget.setFont(new Font("Tahoma", Font.PLAIN, 19));
		addComponent(panelGymStatus, btnGymsetTarget, 3, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		resetGym = new JButton("Reset");
		resetGym.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				lblShowCaloriesBurned.setText("0");
				lblEnterTarget.setText("0");
				lblShowRemainingCalories.setText("0");
			}
		});
		resetGym.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		lblCaloriesBurned = new JLabel("Calories Burned :");
		lblCaloriesBurned.setFont(new Font("Tahoma", Font.PLAIN, 19));
		addComponent(panelGymStatus, lblCaloriesBurned, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		
		lblShowCaloriesBurned = new JLabel("0");
		lblShowCaloriesBurned.setFont(new Font("Tahoma", Font.PLAIN, 19));
		addComponent(panelGymStatus, lblShowCaloriesBurned, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		lblRemainingCalories = new JLabel("Remaining Calories :");
		lblRemainingCalories.setFont(new Font("Tahoma", Font.PLAIN, 19));
		addComponent(panelGymStatus, lblRemainingCalories, 1, 2, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		
		lblShowRemainingCalories = new JLabel("0");
		lblShowRemainingCalories.setFont(new Font("Tahoma", Font.PLAIN, 19));
		addComponent(panelGymStatus, lblShowRemainingCalories, 2, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		btnGymsetTarget.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String targetInput = JOptionPane.showInputDialog("Enter Target Calories");
				
				if(targetInput != null && targetInput.length() != 0){
					lblEnterTarget.setText(targetInput);
					burned = Integer.valueOf(lblShowCaloriesBurned.getText());
					remainingToBurn = (Integer.valueOf(lblEnterTarget.getText()) - burned);
					lblShowRemainingCalories.setText(String.valueOf(remainingToBurn));
				}
			}
			
		});
		
		finishWorkout = new JButton("Record Activity Details");
		finishWorkout.setFont(new Font("Tahoma0", Font.PLAIN, 19));
				
		finishWorkout.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				updateDBGymWorkout();
			}
			
		});
		
		
		
		UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        
        JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
        datePicker = new JDatePickerImpl(datePanel,new DateComponentFormatter());
        addComponent(panelGymStatus, datePicker, 3, 3, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        
        addComponent(panelGymStatus, finishWorkout, 2, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
        
		addComponent(panelGymStatus, resetGym, 4, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
        
		panelGymStatus.add(finishWorkout);
	}
	
	
	// Gym Equipment chooser Panel
	
	JPanel panelGymEquipmentChoose, panelBaseCaloriesHolder, panelGymEquipmentsHolder;
	JScrollPane scrollPaneEquipments;
	JTextField textFieldDuration;
	JList<String> equipmentList;
	JLabel lblUnitCMP, lblBurningCapacity, lblCaloriesPerMin;
	JButton btnAddEquipment;
	int remainingToBurn, burned, burningTarget, saveBurned = 0, showBurned = 0;
	
	protected void gymEquipmentChooser(){
		
		panelGymEquipmentChoose = new JPanel();
		panelGymEquipmentChoose.setLayout(new BorderLayout(0, 0));
		
		scrollPaneEquipments = new JScrollPane();
		panelGymEquipmentChoose.add(scrollPaneEquipments, BorderLayout.CENTER);
		
		panelGymEquipmentsHolder = new JPanel();
		scrollPaneEquipments.setViewportView(panelGymEquipmentsHolder);
		panelGymEquipmentsHolder.setLayout(new BorderLayout(0, 0));
		
		equipmentList = new JList<String>(MI.theList);
		equipmentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		equipmentList.setFont(new Font("Tahoma", Font.PLAIN, 32));
		
		equipmentList.setSelectedIndex(-1);
		panelGymEquipmentsHolder.add(equipmentList, BorderLayout.CENTER);
		
		panelBaseCaloriesHolder = new JPanel();
		panelGymEquipmentsHolder.add(panelBaseCaloriesHolder, BorderLayout.SOUTH);
		
		lblBurningCapacity = new JLabel("Burning Capacity :");
		lblBurningCapacity.setFont(new Font("Tahoma", Font.PLAIN, 32));
		panelBaseCaloriesHolder.add(lblBurningCapacity);
		
		lblCaloriesPerMin = new JLabel(" ");
		lblCaloriesPerMin.setFont(new Font("Tahoma", Font.PLAIN, 32));
		panelBaseCaloriesHolder.add(lblCaloriesPerMin);
		
		lblUnitCMP = new JLabel("Calories/min");
		lblUnitCMP.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panelBaseCaloriesHolder.add(lblUnitCMP);
		
		equipmentList.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				int id = equipmentList.getSelectedIndex()+1;
				
				if(id != -1){
					
					MI.currentMachineID = id;
					MI.equipmentBurningCapacity();
					SwingUtilities.invokeLater(new Runnable(){

						@Override
						public void run() {
							// TODO Auto-generated method stub
							lblCaloriesPerMin.setText(String.valueOf(MI.currentCapacity));
						}
						
					});
				}
			}
			
		});
		
		JTextField duration = new JTextField(3); 
		JTextField speed = new JTextField(3);
		JPanel optionPanel = new JPanel();
						
		btnAddEquipment = new JButton("ADD Activity");
		
		btnAddEquipment.addActionListener(new ActionListener(){
			Integer target;
			int dur;
			@Override
			
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(MI.machineType == 1){
					//give logic of time + duration
					optionPanel.removeAll();
					optionPanel.add(new JLabel("Enter Duration in minutes"));
					optionPanel.add(duration);
					optionPanel.add(Box.createHorizontalStrut(15));
					optionPanel.add(new JLabel("Enter speed in Km/Hr"));
					optionPanel.add(speed);
					dur = JOptionPane.showConfirmDialog(null, optionPanel, "Enter the Values", JOptionPane.OK_CANCEL_OPTION);
				}
				else{
					optionPanel.removeAll();
					optionPanel.add(new JLabel("Enter Duration in minutes"));
					optionPanel.add(duration);
					
					dur = JOptionPane.showConfirmDialog(null, optionPanel, "Enter the Values", JOptionPane.OK_CANCEL_OPTION);
				}
				
				System.out.println(MI.machineType);
				if(MI.machineType == 1 && dur == JOptionPane.OK_OPTION && ((!speed.getText().equals("")) || !duration.getText().equals(""))){
					System.out.println("speed");
					if(Integer.valueOf(lblShowCaloriesBurned.getText()) == 0 && saveBurned !=0){
						burned = saveBurned;
					}
					else{
						burned = Integer.valueOf(lblShowCaloriesBurned.getText());
					}
					showBurned = Integer.valueOf(lblShowCaloriesBurned.getText());
					burned = burned + (MI.currentCapacity*details.weight*Integer.valueOf(speed.getText())*Integer.valueOf(duration.getText()))/(2109*MI.param);
					showBurned += (MI.currentCapacity*details.weight*Integer.valueOf(speed.getText())*Integer.valueOf(duration.getText()))/(2109*MI.param);
					lblShowCaloriesBurned.setText(String.valueOf(showBurned));
					burningTarget = Integer.valueOf(lblEnterTarget.getText());
					remainingToBurn = burningTarget - Integer.valueOf(lblShowCaloriesBurned.getText());
					lblShowRemainingCalories.setText(String.valueOf(remainingToBurn));
				}
				else if(MI.machineType == 0 && dur == JOptionPane.OK_OPTION && (!duration.getText().equals(""))){
					System.out.println("weight");
					if(Integer.valueOf(lblShowCaloriesBurned.getText()) == 0 && saveBurned !=0){
						burned = saveBurned;
					}
					else{
						burned = Integer.valueOf(lblShowCaloriesBurned.getText());
					}
					showBurned = Integer.valueOf(lblShowCaloriesBurned.getText());
					System.out.println(MI.currentCapacity);
					burned = burned + ((MI.currentCapacity*details.weight*Integer.valueOf(duration.getText()))/2109);
					showBurned += (MI.currentCapacity*details.weight*Integer.valueOf(duration.getText()))/2109;
					lblShowCaloriesBurned.setText(String.valueOf(showBurned));
					System.out.println(showBurned);
					burningTarget = Integer.valueOf(lblEnterTarget.getText());
					remainingToBurn = burningTarget - Integer.valueOf(lblShowCaloriesBurned.getText());
					System.out.println(remainingToBurn);
					lblShowRemainingCalories.setText(String.valueOf(remainingToBurn));
				}
			}
			
		});
		
		btnAddEquipment.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panelGymEquipmentsHolder.add(btnAddEquipment, BorderLayout.WEST);
		
	}
	
	Integer saveConsumed = 0, showConsumed = 0;
	
	protected void dietTrackerPanelFoodChooser(){
		
		panelFoodSelector = new JPanel();		//tabbed pane panel 2
		panelFoodSelector.setLayout(new BorderLayout(0, 0));
					
		scrollPane = new JScrollPane();
		panelFoodSelector.add(scrollPane, BorderLayout.CENTER);
		
		panelFoodDetailsHolder = new JPanel();
		scrollPane.setViewportView(panelFoodDetailsHolder);
		panelFoodDetailsHolder.setLayout(new BorderLayout(0, 0));
		
		//String[] values = new String[] {"Apple", "Banana", "Milk"};
		
		dietJList = new JList<String>(DI.theList);
		dietJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		dietJList.setFont(new Font("Tahoma", Font.PLAIN, 32));
		panelFoodDetailsHolder.add(dietJList, BorderLayout.CENTER);
		
		baseDetailsHolder = new JPanel();
		panelFoodDetailsHolder.add(baseDetailsHolder, BorderLayout.SOUTH);
		
		lblCalories = new JLabel("Calories : ");
		lblCalories.setFont(new Font("Tahoma", Font.PLAIN, 32));
		baseDetailsHolder.add(lblCalories);
		
		lblCalorieValue = new JLabel(" ");
		lblCalorieValue.setFont(new Font("Tahoma", Font.PLAIN, 32));
		baseDetailsHolder.add(lblCalorieValue);
		
		lblCal = new JLabel("  cal");
		lblCal.setFont(new Font("Tahoma", Font.PLAIN, 30));
		baseDetailsHolder.add(lblCal);
		
		dietJList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent le) {
				
				int id = dietJList.getSelectedIndex()+1;
				if(id != -1){
					
					DI.currentFoodID = id;
					DI.fetchCalories();
					SwingUtilities.invokeLater(new Runnable(){

						@Override
						public void run() {
							// TODO Auto-generated method stub
							lblCalorieValue.setText(String.valueOf(DI.currentFoodCal));
						}
						
					});
				}
			}
		});
		
		btnAddFood = new JButton("Add Food");
		
		btnAddFood.addActionListener(new ActionListener(){

			Integer target;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				String qty = JOptionPane.showInputDialog("Enter Quantity of Selected Item");
				
				if(qty != null || qty.length() !=0 ){
					
					if(Integer.valueOf(lblConsumedDisplayer.getText()) == 0 && saveConsumed != 0){
						consumed = saveConsumed; 
					}
					else{
						consumed = Integer.valueOf(lblConsumedDisplayer.getText());
					}
					showConsumed = Integer.valueOf(lblConsumedDisplayer.getText());
					consumed = consumed + DI.currentFoodCal*(Integer.valueOf(qty));
					showConsumed += DI.currentFoodCal*(Integer.valueOf(qty));
					lblConsumedDisplayer.setText(String.valueOf(showConsumed));
					target = Integer.valueOf(lblTargethere.getText());
					remaining = target - Integer.valueOf(lblConsumedDisplayer.getText());
					lblShowRemaining.setText(String.valueOf(remaining));
				}	
			}
			
		});
		
		btnAddFood.setFont(new Font("Tahoma", Font.PLAIN, 32));
		panelFoodDetailsHolder.add(btnAddFood, BorderLayout.WEST);
		
		
	}
		
	protected void profilePaneSwitch() {
		// TODO Auto-generated method stub
		
		panel_Central.removeAll();
		panel_Central.add(panelProfile);
		profilePageBuildup();
		panelProfile.setVisible(true);
		panel_Central.invalidate();
		panel_Central.validate();
		panel_Central.repaint();
	}
	
	JButton show_Analysis_GYM;
	
	protected void gymTrackerPanelSwitch(){
		
		panel_Central.removeAll();
		
		show_Analysis_GYM = new JButton("Show Analysis");
		show_Analysis_GYM.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		panel_Central.add(panelGymMain, BorderLayout.CENTER);
		panel_Central.add(show_Analysis_GYM, BorderLayout.NORTH);
                show_Analysis_GYM.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        try {
                            new datagrapgh("workout", "calories burned");
                        } catch (SQLException ex) {
                            Logger.getLogger(mainDisplay.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                });
		panel_Central.revalidate();
		panel_Central.repaint();
	}
	
	JButton show_Analysis_DIET;
	
	protected void dietTrackerPanelSwitch() {
		
		panel_Central.removeAll();
		show_Analysis_DIET = new JButton("show Analysis");
		show_Analysis_DIET.setFont(new Font("Tahoma", Font.PLAIN, 19));
		panel_Central.add(panelDietMain, BorderLayout.CENTER);	//think
		panel_Central.add(show_Analysis_DIET, BorderLayout.NORTH);
                show_Analysis_DIET.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        try {
                            new datagrapgh("consumption", "calories gained");
                        } catch (SQLException ex) {
                            Logger.getLogger(mainDisplay.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
		panelDietMain.setVisible(true);
		panel_Central.invalidate();
		panel_Central.validate();
		panel_Central.repaint();
	}
	
	protected void homePaneSwitch() {
		
		panel_Central.removeAll();
		panel_Central.add(panelHome);
		panelHome.setVisible(true);
		panel_Central.invalidate();
		panel_Central.validate();
		panel_Central.repaint();
		
	}
	
	protected void  profilePageBuildup(){
		
		
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
                                details.detailsUpload();
				// TODO Auto-generated method stub
				lblUID.setText(details.getId()+"");
				labelNm.setText(details.name);
				//labelAg.setText(pm.);
				labelSX.setText(details.gender);
				labelHT.setText(details.height+"");
				labelWT.setText(details.weight+"");
				labelDOB.setText(details.bod);
				Calendar cal = Calendar.getInstance();
				DateFormat df =new SimpleDateFormat("dd/MM/yyyy");
				String g =df.format(cal.getTime());
				Date d1=null,d2=null;
				try {
					d1=df.parse(g);
					d2=df.parse(details.bod);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				int d11=d1.getDay();
				int d22= d2.getDay();
				int m11=d1.getMonth();
				int m22= d2.getMonth();
				int y11=d1.getYear();
				int y22=d2.getYear();
				int diff=y11-y22;
                                int months;
                                months=m11-m22;
                                if(months<0)
                                {
                                    months+=12;
                                }
				if (m22>m11 || ((m22==m11) && d22>d11))
				{
                                    diff--;
				}
				labelAg.setText(Integer.toString(diff)+"YEARS "+Integer.toString(months)+" MONTHS");
				
				
			}
			
		});
		
	}
	
	static boolean recDIET = false;

	
	protected void updateDBDietConsumption() {
		// TODO Auto-generated method stub
		
		
		Calendar cal = Calendar.getInstance();
		int day = cal.getTime().getDate();
		int month = cal.getTime().getMonth();
		int year = cal.getTime().getYear();
		DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
        cal.add(Calendar.DATE,0);
        String s = df.format(cal.getTime());
                        
        Date dt = (Date) datePicker2.getModel().getValue();
        
        if(dt == null){
        	JOptionPane.showMessageDialog(fr, "Please enter date");
        }
               
        else if((day - dt.getDate()) <0|| (day - dt.getDate()) > 7|| (dt.getMonth() != month || dt.getYear() != year)){
        	
        	JOptionPane.showMessageDialog(fr, "Record Updation Limit has Passed you cannot make this Entry");
        	
        }
        else{
        	String oldDate = df.format(dt);
        	int dc = 0;
        	if(Integer.valueOf(lblShowRemaining.getText())>=0){
        		dc = 1;
        	}
        
        	if(Integer.valueOf(lblTargethere.getText())==0){
        		JOptionPane.showMessageDialog(fr, "Target can't be empty");
        		return;
        	}
        
        	if(Integer.valueOf(lblConsumedDisplayer.getText()) == 0){
        	
        		int response = JOptionPane.showConfirmDialog(fr, "You have not consumed any Calories yet, do you still want to Continue?");
        		if(response == JOptionPane.CANCEL_OPTION || response == JOptionPane.CLOSED_OPTION || response == JOptionPane.NO_OPTION){
        			return;
        		}
        	}
         
        	boolean flag = false;
        	Integer calories = Integer.valueOf(lblConsumedDisplayer.getText()), dietTarget = 0;
        	connec c = new connec();
        	c.connect();
        	try {
        		//System.out.println(s);
        		//c.st.executeUpdate("UPDATE consumption SET `date` ='"+s+"', `calories` = "+Integer.valueOf(lblConsumedDisplayer.getText())+ ", `diet_target` ="+Integer.valueOf(lblTargethere.getText())+", `target_complete` ="+dc+ " WHERE uid ="+ temp.getId());
        		c.rs = c.st.executeQuery("SELECT * FROM `consumption` WHERE `uid` = '"+temp.getId()+"' and `date` = '"+oldDate+"'");
			
        		while(c.rs.next()){
        			if(s.equalsIgnoreCase(c.rs.getString("date"))){
        				flag = true;
        				calories = calories + (Integer.valueOf((int) c.rs.getFloat("calories")));
        				dietTarget += (Integer.valueOf((int) c.rs.getFloat("diet_target")));
        			}
        		}
        		c.rs.close();
        		if(flag){
        			c.st.executeUpdate("UPDATE `consumption` SET `calories`= "+calories+",`diet_target`= "+dietTarget+" WHERE `uid` = "+temp.getId()+" and `date` = '"+s+"'");
        		}
        		else{
        			c.st.executeUpdate("INSERT INTO consumption (`uid`, `date`, `calories`, `diet_target`, `target_complete`) VALUES ("+temp.getId()+", '"+oldDate+"', "+Integer.valueOf(lblConsumedDisplayer.getText())+", "+Integer.valueOf(lblTargethere.getText())+", "+dc+")");
        		}
        		c.st.close();
        	} catch (SQLException e) {
			// TODO Auto-generated catch block
        		e.printStackTrace();
        	}
		
        	JOptionPane.showMessageDialog(fr, "Your consumption was recorded");
        	saveConsumed = Integer.valueOf(lblConsumedDisplayer.getText());
			lblConsumedDisplayer.setText("0");
        }
	}
	
		
	protected void updateDBGymWorkout() {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		int day = cal.getTime().getDate();
		int month = cal.getTime().getMonth();
		int year = cal.getTime().getYear();
		DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
        cal.add(Calendar.DATE,0);
        String s = df.format(cal.getTime());
                        
        Date dt = (Date) datePicker.getModel().getValue();
        String oldDate = "";
        if(dt == null){
        	JOptionPane.showMessageDialog(fr, "Please enter date");
        }
               
        else if((day - dt.getDate()) <0|| (day - dt.getDate()) > 7|| (dt.getMonth() != month || dt.getYear() != year)){
        	
        	JOptionPane.showMessageDialog(fr, "Record Updation Limit has Passed you cannot make this Entry");
        	
        }
        else{
        oldDate = df.format(dt);
        System.out.println(oldDate);
        int dc = 0;
        if(Integer.valueOf(lblShowRemainingCalories.getText())<=0){
        	dc = 1;
        }
        
        if(Integer.valueOf(lblEnterTarget.getText())==0){
        	JOptionPane.showMessageDialog(fr, "Target can't be empty");
        	return;
        }
        
        if(Integer.valueOf(lblShowCaloriesBurned.getText()) == 0){
        	
        	int response = JOptionPane.showConfirmDialog(fr, "You have not Burned any Calories yet, do you still want to Continue?");
        	if(response == JOptionPane.CANCEL_OPTION || response == JOptionPane.CLOSED_OPTION || response == JOptionPane.NO_OPTION){
        		return;
        	}
        }
         
        boolean flag = false;
        Integer calories = Integer.valueOf(lblShowCaloriesBurned.getText());
        Integer targetGym = 0;
		connec c = new connec();
		c.connect();
		try {
			c.rs = c.st.executeQuery("SELECT * FROM `workout` WHERE `uid` = '"+temp.getId()+"' and `date` = '"+oldDate+"'");
			while(c.rs.next()){
				if(s.equalsIgnoreCase(c.rs.getString("date"))){
					flag = true;
					calories = calories + (Integer.valueOf((int) c.rs.getFloat("calories_burned")));
					targetGym += (Integer.valueOf((int) c.rs.getFloat("gym_target")));
				}
			}
			c.rs.close();
			if(flag){
				c.st.executeUpdate("UPDATE `workout` SET `calories_burned`= "+calories+",`gym_target`= "+targetGym+" WHERE `uid` = "+temp.getId()+" and `date` = '"+s+"'");
			}
			else{
				System.out.println("hello");
				c.st.executeUpdate("INSERT INTO workout (`uid`, `date`, `calories_burned`, `gym_target`, `target_complete`) VALUES ("+temp.getId()+", '"+oldDate+"', "+Integer.valueOf(lblShowCaloriesBurned.getText())+", "+Integer.valueOf(lblEnterTarget.getText())+", "+dc+")");
			}
			c.rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JOptionPane.showMessageDialog(fr, "Your Workout Details was recorded");
		saveBurned = Integer.valueOf(lblShowCaloriesBurned.getText());
		lblShowCaloriesBurned.setText("0");
        }
	}
	
	//GridBag addComponent layout
	private static void addComponent(Container con , Component comp, int gridX, int gridY, int gridWidth,
			int gridHeight, int anchor, int fill){
		
		GridBagConstraints gbc = new GridBagConstraints(gridX, gridY, gridWidth, gridHeight, 1.0, 1.0, anchor, fill, new Insets(0,0,0,0), 0, 0);
		con.add(comp, gbc);
	}
	
}
