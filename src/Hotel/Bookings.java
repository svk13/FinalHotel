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
		makebooking();
		
		
		
	}
	
	public java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
	    return new java.sql.Date(date.getTime());
	}
	
	public void makebooking(){
		
		try{	
			
			String qry = "INSERT INTO Room_Bookings Values("+hotelID+","+reservationID+",'"+dateins+"','"+dateouts+"',"+
					nrOfRooms+",'"+clientid+"','"+client_passw+"');";
			System.out.println(qry);
			
			Statement stmt = Front.connection.createStatement();
			stmt.setQueryTimeout(30);
			stmt.executeUpdate(qry);
				
					//sqliteConnection.closeConnection(rs, statement, Front.connection)
		}catch(Exception e2){
			System.out.println(e2);
		}
	}
	
	
}
