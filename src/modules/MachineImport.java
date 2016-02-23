package modules;

import java.sql.SQLException;
import java.util.ArrayList;

import fitness.connec;

public class MachineImport {
	
	protected ArrayList<String> machineList = new ArrayList<String>();
	protected String[] theList;
	protected int currentMachineID;
	protected int currentCapacity;
	protected int machineType;
	protected int param;
	
	protected void startImport(){
		
		connec c = new connec();
		c.connect();
		
		try {
			c.rs = c.st.executeQuery("SELECT * FROM machines");
			int i=0;
			while(c.rs.next()){
				
				machineList.add(c.rs.getString("equip_name"));
				
			}
			
			c.rs.close();
			c.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		theList = new String[machineList.size()];
		machineList.toArray(theList);
	}
	
	protected void equipmentBurningCapacity(){
		
		connec c = new connec();
		c.connect();
		try {
			
			
			c.rs = c.st.executeQuery("SELECT `burning_capacity`, `type`, `param` FROM machines WHERE machine_id =" + currentMachineID);

			while(c.rs.next()){
				currentCapacity = c.rs.getInt("burning_capacity");
				machineType = c.rs.getInt("type");
				param = c.rs.getInt("param");
			}
			
			c.rs.close();
			c.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
