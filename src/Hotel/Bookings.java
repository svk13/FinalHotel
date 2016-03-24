package Hotel;

import java.sql.Statement;


public class Bookings {
	private int hotelID;
	private int reservationID;
	private String dateins;
	private String dateouts;
	private int nrOfRooms;
	private String clientid;
	private String client_passw;
	
	public Bookings(int id, int resid, String datein1, String dateout1, int nrRoom, String client, String passw){
		hotelID = id;
		reservationID = resid;
		dateins = datein1;
		dateouts = dateout1;
		nrOfRooms=nrRoom;
		clientid=client;
		client_passw=passw;
		sqlWorkBench.makebooking(hotelID, reservationID, dateins, dateouts, nrOfRooms,clientid,client_passw);
	}
	
}
