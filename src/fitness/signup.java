/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness;

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

/**
 *
 * @author hp
 */
public class signup extends JPanel implements ActionListener{
    JFrame frame;
    JPanel sign;
    JTextField name,id,height,weight;
    JPasswordField password,verifypass;
    JLabel lname,lid,lheight,lweight,lpassword,lverifypass, passmatch,invdate,nul,uid;
    JComboBox gen;
    JButton submit;
     String s[] = {"Male","Female"};
    JDatePickerImpl datePicker;
    
    signup(JFrame fra)
    {
        frame=fra;
        sign=new JPanel();
        sign.setLayout(null);
        name=new JTextField(25);
        id=new JTextField(25);
        height=new JTextField(10);
        weight=new JTextField(10);
        password=new JPasswordField(25);
        verifypass=new JPasswordField(25);
        lname = new JLabel("NAME");
        lid = new JLabel("enter desired id");
        lheight= new JLabel("HEIGHT");
        lweight=new JLabel("WEIGHT");
        lpassword=new JLabel("PASSWORD");
        lverifypass=new JLabel("Renter PASSWORD");
        submit = new JButton("SUBMIT");
        submit.setActionCommand("submit");
       
        gen = new JComboBox(s);
        
        submit.addActionListener(this);
        lname.setBounds(10, 10, 100, 20);
        name.setBounds(150, 10, 100, 20);
        lid.setBounds(10,40,100,20);
        id.setBounds(150,40,100,20);
        lheight.setBounds(10,70,100,20);
        height.setBounds(150,70,100,20);
        JLabel hunit= new JLabel("CM");
        hunit.setBounds(250, 70, 100, 20);
        sign.add(hunit);
        lweight.setBounds(10,100,100,20);
        weight.setBounds(150,100,100,20);
        JLabel wunit = new JLabel("KG");
        wunit.setBounds(250, 100, 100, 20);
        sign.add(wunit);
        uid = new JLabel("");
        uid.setBounds(250, 40, 150, 20);
        sign.add(uid);
        gen.setBounds(150,130,100,20);
        JLabel genl =new JLabel("Gender");
        genl.setBounds(10,130,100,20);
        lpassword.setBounds(10,200,100,20);
        password.setBounds(150,200,100,20);
        lverifypass.setBounds(10,230,110,20);
        verifypass.setBounds(150,230,100,20);
        submit.setBounds(70,300,80,30);
        sign.add(gen);
        sign.add(genl);
        sign.add(lname);
        sign.add(name);
        sign.add(lid);
        sign.add(id);
        sign.add(lheight);
        sign.add(height);
        sign.add(lweight);
        sign.add(weight);
        sign.add(lpassword);
        sign.add(password);
        sign.add(lverifypass);
        sign.add(verifypass);
        sign.add(submit);
         passmatch = new JLabel("");
        passmatch.setBounds(250, 230, 150, 20);
        sign.add(passmatch);
        nul =new JLabel("");
        nul.setBounds(70, 260, 200, 20);
        sign.add(nul);
        invdate= new JLabel("");
        invdate.setBounds(300, 165, 200, 20);
        sign.add(invdate);
         UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
        datePicker = new JDatePickerImpl(datePanel,new DateComponentFormatter());
        JLabel dob = new JLabel("Birth Date");
        dob.setBounds(10, 160, 150, 30);
        sign.add(dob);
        datePicker.setBounds(150, 160, 150, 30);
        sign.add(datePicker);
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
            String na,we,he,pass,vpass,id1;
            boolean flag = false;
            int idn = -1000;
            Date birth;
            na=name.getText();
            we=weight.getText();
            he=height.getText();
            pass=password.getText();
            vpass=verifypass.getText();
            id1=id.getText();
            birth= (Date) datePicker.getModel().getValue();
            int day= datePicker.getModel().getDay();
            int month = datePicker.getModel().getMonth()+1;
            int year = datePicker.getModel().getYear();
            if(na.equals("") || we.equals("")||pass.equals("")||vpass.equals("")||id1.equals("")||birth.equals(""))
            {
                nul.setText("All Fields Mandatory");
                invdate.setText("");
                passmatch.setText("");
                uid.setText("");
            }
            else if((Integer.parseInt(s[0])<day && Integer.parseInt(s[1])==month && (Integer.parseInt(s[2])-15)==year)||(Integer.parseInt(s[1])<month && (Integer.parseInt(s[2])-15)==year) || ((Integer.parseInt(s[2])-15)<year))
            {
                invdate.setText("Age criteria not satisfied");
                nul.setText("");
                passmatch.setText("");
                uid.setText("");
            }
            else 
            {
                double w=(double)Integer.parseInt(we);
                double h=(double)Integer.parseInt(he);
                Double BMI;
                BMI=w/Math.pow(h/100, 2);
                c.connect();
                try{
                	idn=Integer.parseInt(id1);
                }catch(NumberFormatException nm){
                	uid.setText("Please select different id");
                	flag = true;
                }
                c.rs=c.st.executeQuery("SELECT password FROM user WHERE id='"+idn+"'");
                if((c.rs.next()))
                {
                 nul.setText("");
                 passmatch.setText("");
                 uid.setText("Please select different id");
                 invdate.setText("");
                }
                else if(h<0 || w<0 || BMI<15 || BMI>40)
                {
                nul.setText("Enter valid height and weight");
                passmatch.setText("");
                uid.setText("");
                invdate.setText("");    
                }
                else if(!pass.equals(vpass))
                {
                nul.setText("");
                passmatch.setText("Password didn't Match");
                uid.setText("");
                invdate.setText("");  
                }
                else 
                {
                	if(!flag){
                    passmatch.setText("");
                    c.disconnect();
                    c.connect();
                    String findob=df.format(birth);
                    strr = findob;
                    c.st.executeUpdate("INSERT INTO user (`password`,`name`, `id`, `gender`, `height`, `weight`, `birth_date`) VALUES ('"+pass+"','"+na+"','"+idn+"','"+gender+"','"+h+"','"+w+"','"+findob+"')");
                    
                    c.disconnect();
                    JOptionPane.showMessageDialog(null, "Signup Sucessful");
                    frame.remove(sign);
                    frame.repaint();
                    frame.revalidate();
                    new login(frame);
                	}
                }
                
            }
            
        }
         catch (SQLException ex) {
                    Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println(strr);
                }
        
                  
        
    }
   
    
}
