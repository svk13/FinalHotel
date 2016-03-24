package Hotel;

import java.awt.Desktop;
import java.net.URL;
import java.util.Comparator;

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
	
}
