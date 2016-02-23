/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness;
import java.sql.*;

import modules.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
/**
 *
 * @author hp
 */
public class login extends JPanel implements ActionListener{
    JFrame fra;
    JPanel panel;
    JTextField username;
    JTextField pass;
    JLabel user,pas,notmatch;
    String nullus="",nullpass="";
    JButton sign,login;
     static String r=null;
    public login(JFrame frame)
    {
        fra=frame;
        fra.setLayout(new BorderLayout(0,BoxLayout.Y_AXIS));
        panel = new JPanel();
        panel.setLayout(null);
        username = new JTextField(15);
        user=new JLabel("Enter your user id"+nullus);
        pas=new JLabel("enter your password"+nullpass);
        pass = new JPasswordField(15);
        sign= new JButton("SIGNUP");
        login=new JButton("LOGIN");
        sign.setActionCommand("signup");
        login.setActionCommand("login");
        sign.setBounds(50,100,100,30);
        login.setBounds(170, 100, 70, 30);
        user.setBounds(10, 10, 200, 20);
        username.setBounds(200,10,100,20);
        pas.setBounds(10, 50, 200, 20);
        pass.setBounds(200,50,100,20);
        username.addActionListener(this);
        pass.addActionListener(this);
        sign.addActionListener(this);
        login.addActionListener(this);
        panel.add(user);
        panel.add(pas);
        panel.add(username);
        panel.add(pass);
        panel.add(login);
        panel.add(sign);
        JButton forgot=new JButton("Forgot Password");
        forgot.setBounds(70, 140, 150, 30);
        panel.add(forgot);
        forgot.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                new forgotpass();
            }
        });
        panel.setBackground(Color.lightGray);
       // panel.setForeground(Color.blue);
        panel.setVisible(true);
        fra.add(panel);
        fra.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            String comm =ae.getActionCommand();
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(comm.equals("login"))
                {
                    String s = username.getText();
                     int j=-1;
                    if (!s.equals(""))
                        j= Integer.parseInt(s);
                    String p = pass.getText();
                    connec c = new connec();
                    c.connect();
                     c.rs = c.st.executeQuery("SELECT password FROM user WHERE id='"+j+"'");
                     if(c.rs.next()==true)
                     {
                            r = c.rs.getString("password");
                            c.disconnect();
                     }
                     c.disconnect();

                    if((p.equals(r)))
                    {
                        temp.setId(j);
                        //new datagrapgh("consumption", "clories gained");
                        fra.dispose();
                        System.gc();
                        SwingUtilities.invokeLater(new Runnable(){

							@Override
							public void run() {
								// TODO Auto-generated method stub
								mainFrame mf = new mainFrame();
		                        mainDisplay md = new mainDisplay(mf);
		        				mf.setVisible(true);
								
							}
                        	
                        });
                        
                    }
                    else 
                    {
                     notmatch = new JLabel("invalid credentials");
                     notmatch.setBounds(100, 80, 250, 10);
                     panel.add(notmatch);
                     panel.repaint();
                        if(s.equals(""))
                        {
                            nullus=" *";
                            user.setText("Enter your user id "+nullus);
                        }
                        else 
                        {
                            nullus="";
                            user.setText("Enter your user id "+nullus);    
                        }

                        if(p.equals(""))
                        {
                            nullpass=" *";
                            pas.setText("enter your password "+nullpass);
                        }
                        else 
                        {
                            nullpass="";
                             pas.setText("enter your password "+nullpass);
                        }

                        }
                }
            else if (comm.equals("signup"))
            {
                fra.remove(panel);
                fra.revalidate();
                fra.repaint();
                new signup(fra);
            }
                } catch (SQLException ex) {
                    Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    
    
    
}
