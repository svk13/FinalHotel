package Hotel;

import java.util.Date;





public class BookingInfo {

	public Date DateIn;
    private Date DateOut;
    private String DateInS;
    private String DateOutS;
    private int NrGuests;
    private int NrRooms;
	
	public BookingInfo(Date datein, Date dateout, String dateins, String dateouts, int nrofguests, int nrofrooms){
	
		DateIn=datein;
		DateOut=dateout;
		DateInS=dateins;
		DateOutS=dateouts;
		NrGuests = nrofguests;
		NrRooms = nrofrooms;
		
	}
	
	Date getDateIn(){
		return DateIn;
	}
	Date getDateout(){
		return DateOut;
	}
	String getDateInString(){
		return DateInS;
	}
	String getDateOutString(){
		return DateOutS;
	}
	int getNumberOfRooms(){
		return NrRooms;
	}
	int getNumberOfGuests(){
		return NrGuests;
	}

}
