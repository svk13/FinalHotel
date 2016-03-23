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

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class sqlWorkBench {

	static String qry = "";
	static String qry2="";
	
	
	/*
	 * Fall til þess að búa til random tölur. Notaði það til að henda inn í databaseinn. 
	 */
	 private static int showRandomInteger(int aStart, int aEnd, Random aRandom){
		    if (aStart > aEnd) {
		      throw new IllegalArgumentException("Start cannot exceed End.");
		    }
		    //get the range, casting to long to avoid overflow problems
		    long range = (long)aEnd - (long)aStart + 1;
		    // compute a fraction of the range, 0 <= frac < range
		    long fraction = (long)(range * aRandom.nextDouble());
		    int randomNumber =  (int)(fraction + aStart);    
		    return randomNumber;
		   
		  }
	
	//Fall til að bæta inn í Facilities töfluna í databasenum. 
	public static void insertIntoFacilities(){
		Random randomGenerator = new Random();
	    for (int idx = 1; idx <= 10; ++idx){
	      int randomInt = randomGenerator.nextInt(100);
	     
	    }
		try{
			
			for(int count=1; count<=31;count++){
				
				int r1=0;
				int[] rnd = new int[6];
				for(int i=0;i<6;i++){
				
					if(Math.random()<0.5) r1=0;
					else r1=1;
				rnd[i]=r1;
					
				}
				
				Random random = new Random();
			   int one = showRandomInteger(35000,55000,random);
			   Random random1 = new Random();
			   int one1 = showRandomInteger(18000,35000,random1);
			   Random random2 = new Random();
			   int one2 = showRandomInteger(4000,18000,random2);
			   Random random3 = new Random();
			   int one3 = showRandomInteger(2,8,random3);
			   Random random4 = new Random();
			   int one4 = showRandomInteger(15,35,random4);
			   Random random5 = new Random();
			   int one5 = showRandomInteger(15,60,random5);
			
			   qry = "INSERT INTO room_price Values("+count+","+one+","+one1+","+one2+","+
					one3+","+one4+","+one5+");";
			   Statement stmt = Front.connection.createStatement();
			   stmt.setQueryTimeout(30);
			   stmt.executeUpdate(qry);
			}
				
		}catch(Exception e2){
			System.out.println(e2);
		}
	}
	
	// Fall sem hlustar á textaboxið í Front klasanum. Hægt er að gera jcombobox sem eins konar autocorrect 
	// með þessu falli.
	public static Object[] object(Boolean breyta, String word){

		Object[] myObjects=null;
		try{
				
			if(breyta==true){
			qry = "Select Hotel.city from Hotel Group by city;";
			qry2 = "Select count(city) as pi from(select Hotel.city from Hotel Group by city);";
			}else{
				qry="Select Hotel.city from Hotel where city LIKE '%"+ word+"%' Group by city;";
				qry2="Select count(city) as pi from (Select Hotel.city from Hotel where city LIKE '%"+ word+"%' Group by city);";
			}
			
			//System.out.println(qry + "\n" + qry2 );
			PreparedStatement statement = Front.connection.prepareStatement(qry);
			PreparedStatement statement2 = Front.connection.prepareStatement(qry2);
			statement.setQueryTimeout(30);
			ResultSet rs = statement.executeQuery();
			ResultSet rs2 = statement2.executeQuery();
			String thecount = rs2.getString("pi");
			int tmp1 = Integer.parseInt(thecount);
			//System.out.println(" hóhó" + thecount);
			myObjects = new Object[tmp1+1];
			myObjects[0]="";
			int count = 1;
					while (rs.next()) {
						
						String name = rs.getString("city");
						//System.out.println(name);
						Object tmp = name;
						myObjects[count] = tmp;
						count++;
						
					}
					//sqliteConnection.closeConnection(rs, statement, Front.connection);
			
		}catch(Exception e2){
			System.out.println(e2);
		}
	return myObjects;
	}
	
	//Fall til þess að eyða viðskiptavinum út úr database.
	public static void clientDelete(String resID){


		try{
			//Update room_price SET counttype2 = counttype2+1 WHERE hotelID = 31;
			qry = "Delete from room_bookings where reservationID='" + resID +"';";
			PreparedStatement statement = Front.connection.prepareStatement(qry);
			statement.setQueryTimeout(30);
			statement.executeUpdate();
			
			System.out.println("Búið að deleta bókunum");
			
		}catch(Exception e2){
			System.out.println(e2);
		}
		}
	
	public static void reservedroom(int hotelid, int reservationid, String date, int roomtype){


		try{
			//Update room_price SET counttype2 = counttype2+1 WHERE hotelID = 31;
			qry = "Insert into RoomReserved VALUES ('"+hotelid+"','"+reservationid+"','"+date+"','"+roomtype+"');";
			System.out.println("QRY = " + qry);
			PreparedStatement statement = Front.connection.prepareStatement(qry);
			statement.setQueryTimeout(30);
			statement.executeUpdate();
			
			System.out.println("Búið að uppfæra");
			
		}catch(Exception e2){
			System.out.println(e2);
		}
	}
	
	//Þegar pantað er þá uppfærist fjöldi lausra herbergja.
	public static void updateRoomBookings(String date){

		helpDelete(date);
		try{
			//Update room_price SET counttype2 = counttype2+1 WHERE hotelID = 31;
			System.out.println(date+ " Hér er date-ið");
			qry = "Delete from room_bookings where date_out<'" + date +"';";
			PreparedStatement statement = Front.connection.prepareStatement(qry);
			statement.setQueryTimeout(30);
			statement.executeUpdate();
			
			System.out.println("Búið að deleta bókunum");
			
		}catch(Exception e2){
			System.out.println(e2);
		}
	}
	//Hjálpar fall fyrir updateRoombooking
	public static void helpDelete(String date){
		try{
			//Update room_price SET counttype2 = counttype2+1 WHERE hotelID = 31;
			System.out.println(date+ " Hér er date-ið");
			
			qry = "Select hotelid from room_bookings where date_out<'"+date+"';";
			PreparedStatement statement = Front.connection.prepareStatement(qry);
			statement.setQueryTimeout(30);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				String hotelid = rs.getString("hotelid");
				System.out.println(hotelid+ " hotelid");
				int del = Integer.parseInt(hotelid);
				System.out.println(del+ "Hér í updatetableafterdelete");
				//updateTableAfterDelete(del);
			}
			System.out.println("Búið að uppfæra hótelherbergjafjölda");
			
		}catch(Exception e2){
			System.out.println("virkaði ekki");
		}		
	}
	
	// Uppfærir töfluna eftir að það er búið að deleta.
	public static void updateTableAfterDelete(int id){
		try{
					//Update room_price SET counttype2 = counttype2+1 WHERE hotelID = 31;
					qry = "Update room_price SET counttype3 = counttype3+1 WHERE hotelID ='"+id+"';";
					
					PreparedStatement statement = Front.connection.prepareStatement(qry);
					statement.setQueryTimeout(30);
					statement.executeUpdate();;
					System.out.println("Herbergjum hefur fækkað í hotelid: "+ id);
					
				}catch(Exception e2){
					System.out.println(e2);
				}
			}
	
	
	
	public static void login(JTextField textField,  JPasswordField p){
		try{
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
					//sqliteConnection.closeConnection(rs, statement, Front.connection);
			
		}catch(Exception e2){
			System.out.println(e2);
		}
	}

	//Fall sem skilar fjölda viðskiptavina.
	public static int RoomsAvailable(int hotelid, String dateres, String dateout){
		String theday;
		String name = "0";
		ArrayList<String> myDays= datevinnsla(dateres,dateout); 
		for(int i = 0; i<myDays.size();i++){
			try{
				qry = "select datereserved, count(hotelid) as pi from roomreserved where hotelid='"+hotelid+"' and datereserved = '"+myDays.get(i)+"' group by datereserved;";
				System.out.println(myDays.get(i) + " get i");
				PreparedStatement statement = Front.connection.prepareStatement(qry);
				statement.setQueryTimeout(30);
				ResultSet rs = statement.executeQuery();;
						while (rs.next()) {
							theday = rs.getString("datereserved");
							String tmp = rs.getString("pi");
							if(Integer.parseInt(name)<Integer.parseInt(tmp)){
								name = tmp;
							}
						}
						
			}catch(Exception e2){
				System.out.println(e2);
			}
		}
		System.out.println(name + "FinalDATE");
		return Integer.parseInt(name);
	}
	

	public static ArrayList<String> datevinnsla(String in, String out ){
		System.out.println("DATEVINNSLA");
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
	
    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        //System.out.println("Nýjasta nýtt " + cal.getTime());
        return cal.getTime();
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
					//sqliteConnection.closeConnection(rs, statement, Front.connection);
			
		}catch(Exception e2){
			System.out.println(e2);
		}
		return 0;
	}
	
	//Fall sem skilar nafni Hótels.
	public static String HotelName(String id){
		try{
			qry = "select Hotel.name as pi from room_bookings, Hotel where Hotel.id = '"+ id+"';";
			System.out.println(qry);
			PreparedStatement statement = Front.connection.prepareStatement(qry);
			statement.setQueryTimeout(30);
			ResultSet rs = statement.executeQuery();;
					while (rs.next()) {
						
						String name = rs.getString("pi");
						//System.out.println(name);
						return name;
					}
					//sqliteConnection.closeConnection(rs, statement, Front.connection);
			
		}catch(Exception e2){
			System.out.println(e2);
		}
		return "Unknown Hotel";
	}
	
	//Fall sem leitar að verði eftir hótelherbergi.
	public static String priceSearch(){
		
		String returnedString="";
		for(int i = 0; i<Front.resultHotel.size()-1;i++){
			Hotel tmp = Front.resultHotel.get(i);
			int[] pricesandcount = tmp.getPrice();
			for(int j = 0; j<6;j++){
					returnedString += pricesandcount[j]; 
				}
			
		}
	
		return returnedString;
	}
	
	
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
			//System.out.println(tmp+" "+ wifi + " " + i);
			if(facilities==1){
				hoteltmp.add(tmp);
				System.out.println("Búið að adda");
			}
		}
		Front.resultHotel = hoteltmp;
		//System.out.println(Front.resultHotel.size() + " er stærðin");
		return Front.resultHotel;
	}
	
	public static int[] price(int id){
		int[] i= new int[6];
		try{
		int Hotelid = id;
		//System.out.println(Hotelid);
		
		String qry = "Select * from Room_price where hotelid="+Hotelid+";";
			
		//System.out.println("Kemst ég hingað? " + qry);
		
		try{
		PreparedStatement statement = Front.connection.prepareStatement(qry);
		//System.out.println(statement + " Hér ertu  inní price í sqlworkbench" );
		//System.out.println("Kemst ég hingað? " + statement);
		ResultSet rs = statement.executeQuery();
		
		//System.out.println("Kemst ég hingað?");				
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
					//System.out.println("Kemst ég hingað?");
					//Front.textArea.append(id + " " + name + " "  +address + " " +city + " " + URL +  "\n");
					
				}
				
		}catch(Exception e){
			System.out.println("WHÆÆÆÆ");
		}
	}catch(Exception e2){
		System.out.println("prump");
	}
		return i;
		}
	
}

