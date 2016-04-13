package Hotel;

import java.util.Date;

public class BookingInfo {

	public Date DateIn;
    private Date DateOut;
    private String DateInS;
    private String DateOutS;
    private int NrGuests;
    private int NrRooms;
    private int howManyDays;
	
    /* BookingInfo is a class that is created in Front 
     * when the client searches for hotels. DateIn and DateOut
     * are Dates that determine the days the client signs in and
     * out of the hotel. NrGuests shows the number of guests for 
     * that specific reservation and NrRooms the number of rooms
     * for that specific reservation.
     */
    
	public BookingInfo(Date datein, Date dateout, String dateins, String dateouts, int nrofguests, int nrofrooms){
		DateIn=datein;
		DateOut=dateout;
		DateInS=dateins;
		DateOutS=dateouts;
		NrGuests = nrofguests;
		NrRooms = nrofrooms;
	
	}
	
	/* Usage: .getDateIn();
	 * Pre:  
	 * Post: returns DateIn 
	 */
	Date getDateIn(){
		return DateIn;
	}
	/* Usage: .getDateOut();
	 * Pre: 
	 * Post: returns DateOut
	 */
	Date getDateout(){
		return DateOut;
	}
	/* Usage: .getDateInS();
	 * Pre: 
	 * Post: returns DateInS
	 */
	String getDateInString(){
		return DateInS;
	}
	/* Usage: .getDateOutS();
	 * Pre: 
	 * Post: returns DateOutS
	 */
	String getDateOutString(){
		return DateOutS;
	}
	/* Usage: .getNumberOfRooms();
	 * Pre: 
	 * Post: returns NrRooms
	 */
	int getNumberOfRooms(){
		return NrRooms;
	}
	int howManyDays(){
		return howManyDays;
	}
	void sethowManyDays(int x){
		
		howManyDays = x;
	}
	
	/* Usage: .getNumberOfGuests();
	 * Pre: 
	 * Post: returns NrGuests
	 */
	int getNumberOfGuests(){
		return NrGuests;
	}
	/* Usage: .setNumberOfRooms(i);
	 * Pre: i is an integer
	 * Post: Sets the number of Rooms in
	 *  	 this booking to i
	 */
	void setNumberOfRooms(int i){
		System.out.println(i);
		NrRooms=i;
	}
	void setNumberOfGuests(int i){
		NrGuests=i;
		System.out.println(i);
	}
	void setDateIn(Date date){
		DateIn = date;
		System.out.println(date);
	}
	void setDateOut(Date date){
		DateOut = date;
		System.out.println(date);
	}
	void setDateInS(String date){
		DateInS = date;
		System.out.println(date);
	}
	void setDateOutS(String date){
		DateOutS = date;
		System.out.println(date);
	}
	
}
