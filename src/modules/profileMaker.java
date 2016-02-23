package modules;

import java.sql.SQLException;

import fitness.*;

class profileMaker extends temp{
	
	protected void connectAndFetch(){
		
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
