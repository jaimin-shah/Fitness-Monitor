/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author hp
 */
public class connec {
    
    
        final String URL = "jdbc:mysql://localhost/"; 
        String DATABASE = "fitness";
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
        final String USER = "root";
        final String PASS = "";
        Connection conn;
        public Statement st;
        public ResultSet rs;
    

     public void connect()
    {
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to DataBase");
            conn = DriverManager.getConnection(URL+DATABASE,USER,PASS);
            System.out.println("Connection Established");
            st = conn.createStatement();
        }
        catch (Exception e) {
		  e.printStackTrace();
		  }

    }
     public void disconnect() throws SQLException
     {
    	 //rs.close();
    	 st.close();
         conn.close();
     }
}

