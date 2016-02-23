/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author hp
 */
public class forgotpass implements ActionListener{

    JFrame frame;
    JPanel sign;
    JTextField name,id,height,weight;
    JPasswordField password,verifypass;
    JLabel lname,lid,lheight,lweight,lpassword,lverifypass, passmatch,invdate,nul,uid;
    JComboBox gen;
    JButton submit;
     String s[] = {"Male","Female"};
    JDatePickerImpl datePicker;

    public forgotpass() 
    {
        frame= new JFrame();
        frame.setBounds(0, 0, 500, 500);
        sign=new JPanel();
        sign.setLayout(null);
        name=new JTextField(25);
        id=new JTextField(25);
        height=new JTextField(10);
        weight=new JTextField(10);
        password=new JPasswordField(25);
        verifypass=new JPasswordField(25);
        lname = new JLabel("NAME");
        lid = new JLabel("enter id");
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
        //uid = new JLabel("");
        //uid.setBounds(250, 40, 150, 20);
        //sign.add(uid);
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
        //invdate= new JLabel("");
        //invdate.setBounds(300, 165, 200, 20);
        //sign.add(invdate);
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
            String gender=(String) gen.getSelectedItem();
            String na,we,he,pass,vpass,id1;
            String dna,dwe,dhe,dgender,ddate;
            int idn;
            Date birth;
            na=name.getText();
            we=weight.getText();
            he=height.getText();
            pass=password.getText();
            vpass=verifypass.getText();
            id1=id.getText();
            idn=Integer.parseInt(id1);
            birth= (Date) datePicker.getModel().getValue();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String date = df.format(birth);
            connec c =new connec();
            c.connect();
        try 
        {
            c.rs=c.st.executeQuery("SELECT * FROM user WHERE id='"+idn+"'"); 
            if((c.rs.next()))
            {
                dna=c.rs.getString("name");
                dwe=c.rs.getString("weight");
                dhe=c.rs.getString("height");
                dgender=c.rs.getString("gender");
                ddate=c.rs.getString("birth_date");
                System.out.println(dna+"  "+dwe+"  "+dhe+"  "+dgender+"  "+ddate);
                System.out.println(na+"  "+we+"  "+he+"  "+gender+"  "+date);
                if(dna.equals(na) && dwe.equals(we) && dhe.equals(he) && dgender.equals(gender) && date.equals(ddate) && pass.equals(vpass))
                {
                    change(pass, idn);
                    JOptionPane.showMessageDialog(null,"Password Changed");
                    frame.dispose();
                }
                else
                {
                 nul.setText("Invalid data"); 
                }
               
            }
            else
            {
               nul.setText("Invalid data");
            }
        } catch (SQLException ex) {
            Logger.getLogger(forgotpass.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    private void change(String pass, int idn) throws SQLException
    {
        connec c = new connec();
        c.connect();
        c.st.executeUpdate("UPDATE `user` SET `password`="+pass+" WHERE `id`="+idn);
        c.disconnect();
    }
    
}
