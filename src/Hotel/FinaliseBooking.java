package Hotel;

/**
 * FinaliseBooking inniheldur eina meginaðferð. Hún sér um að uppfæra allar töflur í databaseinum
 * fyrir hverja bókun. Restin af föllunum eru einungis hjálparföll fyrir meginfallið. 
 * 
 * @author Atli Þór Jóhansson, Hlynur Logi Þorsteinsson, Sindri Ingólfsson og Sigurbjörn Viðar Karlsson
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FinaliseBooking {

	/*
	 *Notkun:
	 *
	 * updateAllTheDataBase er fall sem sér um allar uppfærslur á database. Þ.a. öll föll fyrir neðan
	 * það eru í raun bara hjálparföll. 
	 * 
	 *  Fallið updateAllTheDataBase tekur inn:
	 *  1) id fyrir hótelið sem pantað er á.
	 *  2) reservationID er fyrir okkar verkefni, þ.a. það er automatískt á 0 hjá ykkur.
	 *  3) datein er innritunardagsetning. Athugið að dagsetningarnar eru á forminu(format)
	 *     "dd MMM yyyy". Ég bæti við falli neðst sem breytir Date hlut í Streng fyrir ykkur.
	 *     Þá nægir ykkur ef þið eruð að nota Date hluti að gera:
	 *     
	 *     String datein = dateToString(ykkarDate);
	 *   4) Sama gildir um dateout.
	 *   5) nrOfRooms er fjöldi herbergja í pöntuninni.
	 *   6) clientid er fyrir okkur. Ef þið viljið halda utan um kúnnana ykkar. Annars bara tómi strengur.
	 *   7) Sama og 6)
	 *   8) roomType erhvernig herbergi er pantað. 1, 2 eða 3. 
	 *   
	 *   Eftir: 
	 *   
	 *   Búið er að uppfæra allar töflur í databaseinu.
	 */
	public static void updateAllTheDataBase(int hotelID, int reservationID, String datein, String dateout,int nrOfRooms,String clientid,String client_passw, int roomType){
	try{	
		
			//Bý til pöntun í databaseinu.
			String qry = "INSERT INTO Room_Bookings Values("+hotelID+","+reservationID+",'"+datein+"','"+dateout+"',"+
					nrOfRooms+",'"+clientid+"','"+client_passw+"');";
			
			
			Statement stmt = Front.connection.createStatement();
			stmt.setQueryTimeout(5);
			stmt.executeUpdate(qry);
			stmt.close();
		
	
			//Uppfæri í leiðinni RoomReserved töfluna þ.a. herbergi eru 
			//frátekin á hótelinu á öllum dagsetningum á milli in og out
			//dagsetninganna.
			datevinnsla(hotelID, reservationID, datein, dateout, roomType, nrOfRooms);
			
			
			stmt.close();
			
			
		}catch(Exception e2){
			System.out.println(e2+" eða er villan hér ?");
		}
		datevinnsla(hotelID, 0, datein, dateout, roomType, nrOfRooms);
	}

	//-----------------------------------------------------------------------------------------
	//Hjálparföllin sem þið þurfið ábyggilega ekki að pæla í.
	
	/* Usage: datevinnsla(hotelId, resId, in,out, type, nrOfRooms);
	 * Pre: in is a specific date in String form and so is out.
	 * 		hotelId is the hotel id. resid is 0. type is the 
	 * 		type of room. nrOfRooms is the number of rooms in the order.
	 * Post: The method has added the dates of the reservation
	 * 		 that the client has chosen in his booking to the database.
	 */ 
	public static void datevinnsla(int HotelId, int resid, String in, String out, int type, int nrOfRooms ){
	    
		String tmpin = in.substring(0, 2);
		String tmpout = out.substring(0, 2);
		int itmp = Integer.parseInt(tmpin);
		int itmpout = Integer.parseInt(tmpout);
		
		updateReservedRoomTable(HotelId,resid , in, type, nrOfRooms);
		
		while(itmp!=itmpout){
			
			SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy"); 
			Date myDate;
			try {
				myDate = format.parse(in);
				
				myDate = addDays(myDate, 1);

				String newdate = format.format(myDate);
				tmpin = newdate.substring(0, 2);
				itmp = Integer.parseInt(tmpin);
				in = newdate;				
				for(int i = 0; i<nrOfRooms;i++){
					updateReservedRoomTable(HotelId,resid , in, type, nrOfRooms);
				
				}
				

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	
	// A method that inserts into roomreserved in the database. So that the program can 
	// later see if a hotel is fully booked.
	public static void updateReservedRoomTable(int hotelid, int reservationid, String date, int roomtype, int numberOfRooms){
		
		try{
			
			
			;
			String upDateRooms = "Insert into RoomReserved VALUES ('"+hotelid+"','"+reservationid+"','"+date+"','"+roomtype+"');";
			
			PreparedStatement statement = Front.connection.prepareStatement(upDateRooms);
			
			
			statement.setQueryTimeout(5);
			statement.executeUpdate();
			
			//c.close();
			statement.close();
			
		}catch(Exception e2){
			System.out.println(e2 + "Hér er villan maður");
		}
	}

	/* Usage: addDays(date, days);
	 * Pre: date is a Date and days is the number 
	 *  	of days that you want to add to your new date.
	 * Post: New date is returned that is 'days' many days later
	 *  	 than the original date. 
	 */ 
    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
       
        return cal.getTime();
    }
    
    //Hjálparfall til að breyta Date í Streng.
    //Notkun: s = dateToString(date);
    //Fyrir: date er Date. s er strengur.
    //Eftir: date er komið á String form.
	public static String dateToString(Date indate) {
		String dateString = null;
		SimpleDateFormat sdfr = new SimpleDateFormat("dd MMM yyyy");
		
		try {
			dateString = sdfr.format(indate);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return dateString;
	}
}
