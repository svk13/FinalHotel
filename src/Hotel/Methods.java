package Hotel;

import java.awt.Desktop;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

public class Methods {

	static class MyComparator implements Comparator<Hotel> {
		@Override
		public int compare(Hotel o1, Hotel o2) {
			int[] a = o1.getPrice();
			int[] b = o2.getPrice();
		    if (a[2] > b[2]) {
		        return -1;
		    } else if (a[2]<b[2]) {
		        return 1;
		    }
		    return 0;
		    }
		}
	static class MyComparator2 implements Comparator<Hotel> {
		@Override
		public int compare(Hotel o1, Hotel o2) {
			int[] a = o1.getPrice();
			int[] b = o2.getPrice();
		    if (a[2] < b[2]) {
		        return -1;
		    } else if (a[2]>b[2]) {
		        return 1;
		    }
		    return 0;
		    }
		}
	static class MyComparator3 implements Comparator<Hotel> {
		@Override
		public int compare(Hotel o1, Hotel o2) {
			String a = o1.getName();
			String b = o2.getName();
		    if (a.compareTo(b) < 0) {
		        return -1;
		    } else if (a.compareTo(b)>0) {
		        return 1;
		    }
		    return 0;
		    }
		}
	static class MyComparator4 implements Comparator<Hotel> {
		@Override
		public int compare(Hotel o1, Hotel o2) {
			String a = o1.getName();
			String b = o2.getName();
		    if (a.compareTo(b) > 0) {
		        return -1;
		    } else if (a.compareTo(b)<0) {
		        return 1;
		    }
		    return 0;
		    }
		}
		
	private void internetURL(String urlid) {
		String tmp = urlid.substring(0,urlid.length());
		try {
		    Desktop.getDesktop().browse(new URL("http://"+tmp).toURI());
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	static String info(int a, int b, int c, int d, int e, int f){
		String string="";
		if(a==1) string+="Wifi \n";
		if(a==1) string+="Free Wifi \n";
		if(c==1) string+="Smoking Area \n";
		if(d==1) string+="Swimming pool \n";
		if(e==1) string+="Gym \n";
		if(f==1) string+="TV";
		return string;
	}
	
	public static ArrayList<String> datevinnsla(String in, String out ){
		String tmpin = in.substring(0, 2);
		String tmpout = out.substring(0, 2);
		int itmp = Integer.parseInt(tmpin);
		int itmpout = Integer.parseInt(tmpout);
		ArrayList<String> dayList= new ArrayList<String>();
		dayList.add(in);		
		//System.out.println("in " + in + " out " + out);
		while(itmp!=itmpout){
		
			SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy"); 
			Date myDate;
			
			try {
				myDate = format.parse(in);
				myDate = addDays(myDate, 1);
				String newdate = format.format(myDate);
				tmpin = newdate.substring(0, 2);
				itmp = Integer.parseInt(tmpin);	
				dayList.add(newdate);
				in = newdate;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
			return dayList;
	}

	// A method that adds a certain amount of days to a date. 
	// The method adds 'days' days to the date.
    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        //System.out.println("Nýjasta nýtt " + cal.getTime());
        return cal.getTime();
    }
}
