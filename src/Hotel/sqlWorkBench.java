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
	public static ArrayList<Hotel> LeitaHotel(String tmp){
		ArrayList<Hotel> theList = new ArrayList<Hotel>();
		
		try{
			
				qry = "Select * from Hotel,hotelfacilities, room_price where Hotel.id=Hotelfacilities.hotelid AND Hotel.id = Room_price.Hotelid AND ( Hotel.name LIKE'%"+tmp+"%' OR Hotel.city LIKE'"
						+tmp+"%' OR Hotel.postcode LIKE'"+tmp+"%' OR Hotel.address LIKE'"+tmp+"%');";
			
			System.out.println("QRY = " + qry);
			PreparedStatement statement = Front.connection.prepareStatement(qry);
			statement.setQueryTimeout(30);
			HotelResultSet = statement.executeQuery();
			
			while (HotelResultSet.next()) {

				String name = HotelResultSet.getString("name");
				String address = HotelResultSet.getString("address");
				String city = HotelResultSet.getString("city");
				String URL = HotelResultSet.getString("URL");
				int id = Integer.parseInt(HotelResultSet.getString("id"));
				int postcode = Integer.parseInt(HotelResultSet.getString("postcode"));
				int wifi = Integer.parseInt(HotelResultSet.getString("Wifi"));
				int FreeWifi = Integer.parseInt(HotelResultSet.getString("FreeWifi"));
				int Smokearea = Integer.parseInt(HotelResultSet.getString("SmokingArea"));
				int SPool = Integer.parseInt(HotelResultSet.getString("Swimmingpool"));
				int Gym = Integer.parseInt(HotelResultSet.getString("Gym"));
				int TV = Integer.parseInt(HotelResultSet.getString("TV"));
				
				Hotel hotelTmp = new Hotel(id, name, address, postcode, city, URL,wifi,FreeWifi,Smokearea,SPool,Gym,TV);
				hotelTmp.setPriceOfRoomType1(Integer.parseInt(HotelResultSet.getString("type1")));
				hotelTmp.setPriceOfRoomType2(Integer.parseInt(HotelResultSet.getString("type2")));
				hotelTmp.setPriceOfRoomType3(Integer.parseInt(HotelResultSet.getString("type3")));
				hotelTmp.setRoomType1Count(Integer.parseInt(HotelResultSet.getString("counttype1")));
				hotelTmp.setRoomType2Count(Integer.parseInt(HotelResultSet.getString("counttype2")));
				hotelTmp.setRoomType3Count(Integer.parseInt(HotelResultSet.getString("counttype3")));
				theList.add(hotelTmp);
		
			}
			
		}catch(Exception e2){
			System.out.println(e2);
			
		}
		return theList;
		
	}
	
	/* Usage:
 	 * Pre:
 	 * Post:
	 */
	public static void makebooking(int hotelID, int reservationID, String dateins, String dateouts,int nrOfRooms,String clientid,String client_passw){
		
		try{	
			
			String qry = "INSERT INTO Room_Bookings Values("+hotelID+","+reservationID+",'"+dateins+"','"+dateouts+"',"+
					nrOfRooms+",'"+clientid+"','"+client_passw+"');";
			
			Statement stmt = Front.connection.createStatement();
			stmt.setQueryTimeout(30);
			stmt.executeUpdate(qry);
			stmt.close();
					
		}catch(Exception e2){
			System.out.println(e2);
		}
	}
	
	
	//Fall til �ess a� ey�a vi�skiptavinum �t �r database.
	public static void clientDelete(String resID){
		try{
		
			//Update room_price SET counttype2 = counttype2+1 WHERE hotelID = 31;
			qry = "Delete from room_bookings where reservationID='" + resID +"';";
			PreparedStatement statement = Front.connection.prepareStatement(qry);
			statement.setQueryTimeout(30);
			statement.executeUpdate();
			
			System.out.println("B�i� a� deleta b�kunum");
			statement.close();
			
		}catch(Exception e2){
			System.out.println(e2);
		}
	}
	
	// A method that inserts into roomreserved in the database. So that the program can 
	// later see if a hotel is fully booked.
	public static void reservedroom(int hotelid, int reservationid, String date, int roomtype){
		try{
			
			qry = "Insert into RoomReserved VALUES ('"+hotelid+"','"+reservationid+"','"+date+"','"+roomtype+"');";
			PreparedStatement statement = Front.connection.prepareStatement(qry);
			statement.setQueryTimeout(30);
			statement.executeUpdate();
			
			
			statement.close();
			
		}catch(Exception e2){
			System.out.println(e2);
		}
	}

	public static void updateDB(String date){
		SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy"); 
		Date myDate;
		try {
			myDate = format.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if (format.parse(date).before(new Date())) {
				System.out.println("prump");
			    }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}
	
	//�egar panta� er �� uppf�rist fj�ldi lausra herbergja.
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
						//String name = rs.getString("pi");
						//System.out.println(name)
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

	//A method that returns an in that is the amount of rooms taken at a certain
	//hotel from date dateres to dateout.
	public static int RoomsAvailable(int hotelid, String dateres, String dateout){
		String name = "0";
		ArrayList<String> myDays= Methods.datevinnsla(dateres,dateout); 
		for(int i = 0; i<myDays.size();i++){
			try{
				
				qry = "select datereserved, count(hotelid) as pi from roomreserved where hotelid='"+hotelid+"' and datereserved = '"+myDays.get(i)+"' group by datereserved;";
				PreparedStatement statement = Front.connection.prepareStatement(qry);
				statement.setQueryTimeout(30);
				ResultSet rs = statement.executeQuery();;
						while (rs.next()) {
							String tmp = rs.getString("pi");
							if(Integer.parseInt(name)<Integer.parseInt(tmp)){
								name = tmp;
							}
						}
						
			}catch(Exception e2){
				System.out.println(e2);
			}
		}
		return Integer.parseInt(name);
	}
	

	//Fall sem skilar fj�lda vi�skiptavina.
		public static void clientIDs(){
			try{
				;
				qry = "select client_id as pi from room_bookings;";
				
				PreparedStatement statement = Front.connection.prepareStatement(qry);
				statement.setQueryTimeout(30);
				ResultSet rs = statement.executeQuery();;
						while (rs.next()) {
							
							String name = rs.getString("pi");
							//System.out.println(name);
							System.out.println(name);
						}
						//sqliteConnection.closeConnection(rs, statement, Front.connection.);
				
			}catch(Exception e2){
				System.out.println(e2);
			}
			
		}
	
	//Fall sem skilar fj�lda vi�skiptavina.
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
	
	//Fall sem skilar nafni H�tels.
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
				System.out.println("WH����");	
			}
		}catch(Exception e2){
			System.out.println("prump");
		}
		return i;
	
	}
	
}

