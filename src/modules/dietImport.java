package modules;

import java.sql.SQLException;

import fitness.*;

import java.util.*;

public class dietImport {
	
	protected ArrayList<String> dietList = new ArrayList<String>();
	//protected ArrayList<Integer> dietCals = new ArrayList<Integer>();
	protected String[] theList;
	protected int currentFoodID;
	protected int currentFoodCal;
	
	protected void startImport(){
		
		connec c = new connec();
		c.connect();
		
		try {
			c.rs = c.st.executeQuery("SELECT * FROM diets");
			int i=0;
			while(c.rs.next()){
				
				dietList.add(c.rs.getString("diet_name"));
				
			}
			
			c.rs.close();
			c.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		theList = new String[dietList.size()];
		dietList.toArray(theList);
		
	}
	
	protected void fetchCalories(){
		
		connec c = new connec();
		c.connect();
		try {
			
			
			c.rs = c.st.executeQuery("SELECT diet_calories FROM diets WHERE diet_id =" + currentFoodID);

			while(c.rs.next()){
				currentFoodCal = c.rs.getInt("diet_calories");
			}
			
			c.rs.close();
			c.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
