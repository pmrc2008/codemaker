package util;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateTest {
	
	
	public static void main(String[] args) {
		String ss = "Sat Jun 04 22:45:28 CST 2016";
		//String mm = "Mon Mar 30 00:00:00 CST 2015";
		SimpleDateFormat sdf1= new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
		SimpleDateFormat sdf2= new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			System.out.println(sdf2.format(sdf1.parse(ss)));  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	}

}
