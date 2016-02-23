/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness;

import java.sql.SQLException;

/**
 *
 * @author hp
 */
public class temp {
    private static int id = Integer.MIN_VALUE;
    public  String name;
    public  int weight;
    public  int height;
    public  String bod;
    public  String gender;
	public static int getId() {
		return id;
	}
	public static void setId(int id) {
		temp.id = id;
	}
	
	public void detailsUpload(){
		connec c = new connec();
		c.connect();
		try {
			c.rs = c.st.executeQuery("SELECT * FROM user WHERE id='"+getId()+"'");
			
			while(c.rs.next()){
				
				name = c.rs.getString("name");
				setId(c.rs.getInt("id"));
				gender = c.rs.getString("gender");
				height = c.rs.getInt("height");
				weight = c.rs.getInt("weight");
				bod = c.rs.getString("birth_date");
			}
			c.rs.close();
			c.disconnect();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
}
