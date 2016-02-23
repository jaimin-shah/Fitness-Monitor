package modules;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class PracticeWithDate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Calendar cal = Calendar.getInstance();
		//System.out.println(cal);
		DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
        cal.add(Calendar.DATE,0);
        //cal.add(Calendar.DATE, 1);
        
        String s=df.format(cal.getTime());
        System.out.println(s);
	}

}
