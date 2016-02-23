package modules;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import fitness.*;

public class CheckValidity extends temp{
	
	boolean validate(String param){
		
		Calendar cal = Calendar.getInstance();
		DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
        cal.add(Calendar.DATE,0);
        String s = df.format(cal.getTime());
        
		connec c = new connec();
		c.connect();
		try {
			c.rs = c.st.executeQuery("SELECT `date` FROM "+param+" WHERE uid='"+getId()+"'");
			while(c.rs.next()){
				if(s.equals(c.rs.getString("date"))){
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
       // System.out.println(s);
		return true;
	}

}
