package Hotel;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class sqlWorkBench {

	static String qry = "";
	static String qry2="";
	static ResultSet HotelResultSet;

	// A method that searches for a hotel with the information in the textarea in the Front class.
	
	

	
	//Fall til þess að eyða viðskiptavinum út úr database.
	public static void clientDelete(String resID){
		try{
		
			//Update room_price SET counttype2 = counttype2+1 WHERE hotelID = 31;
			qry = "Delete from room_bookings where reservationID='" + resID +"';";
			PreparedStatement statement = Front.connection.prepareStatement(qry);
			statement.setQueryTimeout(30);
			statement.executeUpdate();
			
			
			statement.close();
			
		}catch(Exception e2){
			System.out.println(e2);
		}
	}
	


	
	//Þegar pantað er þá uppfærist fjöldi lausra herbergja.
	public static void updateRoomBookings(String date){


		try{
			//Update room_price SET counttype2 = counttype2+1 WHERE hotelID = 31;
			
			qry = "Delete from room_bookings where date_out<'" + date +"';";
			qry2 = "Select * from room_bookings whete date_out='" + date +"';"; 
		
			PreparedStatement statement = Front.connection.prepareStatement(qry);
			PreparedStatement statement2 = Front.connection.prepareStatement(qry2);
			
			ResultSet rs =statement2.executeQuery();
			while(rs.next()){
				String id = rs.getString("hotelID");
				String dateout = rs.getString("date_out");
				
			}
			rs.close();
			statement.setQueryTimeout(30);
			statement.executeUpdate();
			Front.connection.clearWarnings();
			
		}catch(Exception e2){
			System.out.println(e2);
		}
	}
	
	// A method that enables a client to login to the system and see/cancel his
	// reservation.
	public static void login(JTextField textField,  JPasswordField p){
		try{
			;
			String id = null,reservationID = null,datein = null,dateout = null,finishedstring = null;
			qry = "select * from room_bookings where client_id=? AND client_passw = ?;";
			PreparedStatement statement = Front.connection.prepareStatement(qry);
			statement.setQueryTimeout(30);
			statement.setString(1, textField.getText());
			statement.setString(2,p.getText());
			ResultSet rs = statement.executeQuery();;
			int count = 0;		
			while (rs.next()) {
						count +=1;
						id = rs.getString("hotelID");
						reservationID = rs.getString("reservationID");
						datein = rs.getString("date_in");
						dateout = rs.getString("date_out");
						finishedstring="Username and password are correct\n Your reservation at hotel "+ id+" from " + datein +" to " + dateout;
						
					}
			if(count==1){
				String idname = HotelName(id);
				ClientLogin log = new ClientLogin(idname, reservationID, datein, dateout);
				log.setVisible(true);
				//JOptionPane.showMessageDialog(null, finishedstring);
			}else if(count>1){
				JOptionPane.showMessageDialog(null, "Duplicate Username and password");
			}else{
				JOptionPane.showMessageDialog(null, "Incorrect username or password");
			}
					//sqliteConnection.closeConnection(rs, statement, Front.connection.);
			
			
		}catch(Exception e2){
			System.out.println(e2);
		}
	}
	
	
	//Fall sem skilar fjölda viðskiptavina.
		public static void clientIDs(){
			try{
				;
				qry = "select client_id as pi from room_bookings;";
				
				PreparedStatement statement = Front.connection.prepareStatement(qry);
				statement.setQueryTimeout(30);
				ResultSet rs = statement.executeQuery();;
						while (rs.next()) {
							
							String name = rs.getString("pi");
							
							System.out.println(name);
						}
						//sqliteConnection.closeConnection(rs, statement, Front.connection.);
				
			}catch(Exception e2){
				System.out.println(e2);
			}
			
		}
	
	//Fall sem skilar fjölda viðskiptavina.
	public static int NrOfClients(){
		try{
			
			qry = "select count(client_id) as pi from room_bookings;";
			
			PreparedStatement statement = Front.connection.prepareStatement(qry);
			statement.setQueryTimeout(30);
			ResultSet rs = statement.executeQuery();;
					while (rs.next()) {
						
						String name = rs.getString("pi");
						//System.out.println(name);
						return Integer.parseInt(name);
					}
					//sqliteConnection.closeConnection(rs, statement, Front.connection.);
			
		}catch(Exception e2){
			System.out.println(e2);
		}
		return 0;
	}
	
	//Fall sem skilar nafni Hótels.
	public static String HotelName(String id){
		try{
	
			qry = "select Hotel.name as pi from room_bookings, Hotel where Hotel.id = '"+ id+"';";
			PreparedStatement statement = Front.connection.prepareStatement(qry);
			statement.setQueryTimeout(30);
			ResultSet rs = statement.executeQuery();;
					while (rs.next()) {	
						String name = rs.getString("pi");
						return name;
					}
					
		}catch(Exception e2){
			System.out.println(e2);
		}
		throw new IllegalArgumentException("Error: Does not exist in database.");
	}
	
	
	/* Usage: detailedSearch(hotelList, var); 
	 * Pre: hotelList is an ArrayList<Hotel> and var is an integer.
	 * Post: The method returns a new ArrayList. It contains the 
	 * 		 Hotels from hotelList that have specific facilities
	 *  	 that the client is looking for. i.e. wifi, gym, etFront.connection.
	 */
	public static ArrayList<Hotel> detailedSearch(ArrayList<Hotel> hotelList, int var){
	
		ArrayList<Hotel> hoteltmp = new ArrayList<Hotel>();
		for(int i = 0; i<hotelList.size()-1;i++){
			int facilities;
			Hotel tmp = hotelList.get(i);
			if(var==0){ 
				facilities = tmp.getWifi();
			}else if(var==1){
				facilities=tmp.getFreeWifi();
			}else if(var==2){
				facilities=tmp.getPool();
			}else if(var==3){
				facilities=tmp.getGym();
			}else if(var==4){
				facilities=tmp.getTV();
			}else{
				facilities=tmp.getSmoke();
			}
			if(facilities==1){
				hoteltmp.add(tmp);
			}
		}
		Front.resultHotel = hoteltmp;
		
		return Front.resultHotel;
	}
	
	// Method that returns int[] with information about what
	// facilities a certain Hotel has. 1 means it has it, 0 not.
	public static int[] price(int id){
		int[] i= new int[6];
		try{
		int Hotelid = id;
		String qry = "Select * from Room_price where hotelid="+Hotelid+";";

			try{
				Connection c = sqliteConnection.dbConnector();
			PreparedStatement statement = Front.connection.prepareStatement(qry);
			ResultSet rs = statement.executeQuery();
					
					while (rs.next()) {
						
						String type1=rs.getString("type1");
						String type2=rs.getString("type2");
						String type3=rs.getString("type3");
						String counttype1=rs.getString("counttype1");
						String counttype2=rs.getString("counttype2");
						String counttype3=rs.getString("counttype3");
						i[0] = Integer.parseInt(type1);
						i[1] = Integer.parseInt(type2);
						i[2] = Integer.parseInt(type3);
						i[3] = Integer.parseInt(counttype1);
						i[4] = Integer.parseInt(counttype2);
						i[5] = Integer.parseInt(counttype3);	
					}
					
			}catch(Exception e){
				System.out.println(e);	
			}
		}catch(Exception e2){
			System.out.println("prump");
		}
		return i;
	
	}
	
}

