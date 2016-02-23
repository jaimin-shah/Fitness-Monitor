/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Calendar;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import java.util.Date;
import fitness.*;

/**
 *
 * @author hp
 */
public class ProfileChanger extends JPanel implements ActionListener{
    JFrame frame;
    JPanel sign;
    JTextField name,height,weight;
    //JPasswordField password,verifypass;
    JLabel lname,lheight,lweight,invdate,nul;
    JComboBox gen;
    JButton submit;
     String s[] = {"Male","Female"};
    JDatePickerImpl datePicker;
    
    ProfileChanger()
    {
        frame= new JFrame("Update Profile");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        sign=new JPanel();
        sign.setLayout(null);
        name=new JTextField(25);
       // id=new JTextField(25);
        height=new JTextField(10);
        weight=new JTextField(10);
      //  password=new JPasswordField(25);
      //  verifypass=new JPasswordField(25);
        lname = new JLabel("NAME");
      //  lid = new JLabel("enter desired id");
        lheight= new JLabel("HEIGHT");
        lweight=new JLabel("WEIGHT");
     //   lpassword=new JLabel("PASSWORD");
     //   lverifypass=new JLabel("Renter PASSWORD");
        submit = new JButton("Update");
        submit.setActionCommand("submit");
       
        gen = new JComboBox(s);
        
        submit.addActionListener(this);
        lname.setBounds(10, 10, 100, 20);
        name.setBounds(150, 10, 100, 20);
      //  lid.setBounds(10,40,100,20);
      //  id.setBounds(150,40,100,20);
        lheight.setBounds(10,40,100,20);
        height.setBounds(150,40,100,20);
        JLabel hunit= new JLabel("CM");
        hunit.setBounds(250, 40, 100, 20);
        sign.add(hunit);
        lweight.setBounds(10,70,100,20);
        weight.setBounds(150,70,100,20);
        JLabel wunit = new JLabel("KG");
        wunit.setBounds(250, 70, 100, 20);
        sign.add(wunit);
      //  uid = new JLabel("");
      //  uid.setBounds(250, 40, 150, 20);
      //  sign.add(uid);
        gen.setBounds(150,100,100,20);
        JLabel genl =new JLabel("Gender");
        genl.setBounds(10,100,100,20);
      //  lpassword.setBounds(10,200,100,20);
      //  password.setBounds(150,200,100,20);
      //  lverifypass.setBounds(10,230,110,20);
      //  verifypass.setBounds(150,230,100,20);
        submit.setBounds(70,200,80,30);
        sign.add(gen);
        sign.add(genl);
        sign.add(lname);
        sign.add(name);
     //   sign.add(lid);
     //   sign.add(id);
        sign.add(lheight);
        sign.add(height);
        sign.add(lweight);
        sign.add(weight);
    //    sign.add(lpassword);
    //    sign.add(password);
    //    sign.add(lverifypass);
    //    sign.add(verifypass);
    //    sign.add(submit);
    //     passmatch = new JLabel("");
    //    passmatch.setBounds(250, 230, 150, 20);
    //    sign.add(passmatch);
        nul =new JLabel("");
        nul.setBounds(70, 170, 200, 20);
        sign.add(nul);
        invdate= new JLabel("");
        invdate.setBounds(300, 170, 100, 20);
        sign.add(invdate);
         UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
         datePicker = new JDatePickerImpl(datePanel,new DateComponentFormatter());
         JLabel dob = new JLabel("Birth Date");
         dob.setBounds(10, 130, 150, 30);
         sign.add(dob);
        datePicker.setBounds(150, 130, 150, 30);
        sign.add(datePicker);
        sign.add(submit);
        sign.repaint();
        frame.add(sign);
        sign.setVisible(true);
        frame.setVisible(true);
        frame.repaint();
        frame.revalidate();
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
    	String strr=null;
        Date birth;
        try
        {
            connec c= new connec();
            String gender=(String) gen.getSelectedItem();
            //System.out.println(s1);
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String date = df.format(new Date());
            String s[]=new String[3];
            s=date.split("/", 3);
            //System.out.println(s[1]);
            String na,we,he;
            
            na=name.getText();
            we=weight.getText();
            he=height.getText();
        //    pass=password.getText();
        //    vpass=verifypass.getText();
        //    id1=id.getText();
            birth= (Date) datePicker.getModel().getValue();
            int day= datePicker.getModel().getDay();
            int month = datePicker.getModel().getMonth()+1;
            int year = datePicker.getModel().getYear();
            if(na.equals("") || we.equals("")||birth.equals("") || he.equals(""))
            {
                nul.setText("All Fields Mandatory");
                invdate.setText("");
         //       passmatch.setText("");
         //       uid.setText("");
            }
            else if((Integer.parseInt(s[0])<day && Integer.parseInt(s[1])==month && (Integer.parseInt(s[2])-15)==year)||(Integer.parseInt(s[1])<month && (Integer.parseInt(s[2])-15)==year) || ((Integer.parseInt(s[2])-15)<year))
            {
                invdate.setText("Invalid Date");
                nul.setText("");
         //       passmatch.setText("");
         //       uid.setText("");
            }
            
            else 
            {
                    //passmatch.setText("");
                   // c.disconnect();
                    double w=(double)Integer.parseInt(we);
                    double h=(double)Integer.parseInt(he);
                    Double BMI;
                    BMI=w/Math.pow(h/100, 2);
                    if(h<0 || w<0 || BMI<15 || BMI>40)
                    {
                    nul.setText("Enter valid height and weight");
                    //passmatch.setText("");
                    //uid.setText("");
                    invdate.setText("");    
                    }
                    else
                    {
                    c.connect();  
                    String findob=df.format(birth);
                    strr = findob;
                    c.st.executeUpdate("UPDATE `user` SET `name`='"+na+"',`gender`='"+gender+"',`height`="+h+",`weight`="+w+",`birth_date`='"+findob+"' WHERE id="+temp.getId());
                    c.disconnect();
                    JOptionPane.showMessageDialog(null, "Profile Updated Successfully");
                    frame.dispose();
                    }
            }
                
                
            } catch (SQLException ex) {
            Logger.getLogger(ProfileChanger.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
   
    
}
