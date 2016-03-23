package Hotel;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;



public class sqliteConnection {
	
	static ResultSet rs;
	static Connection conn=null;
	static String qry;
	public static Connection dbConnector(){
		
		try{
			
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:src/Database/Hotel.db");
			JOptionPane.showMessageDialog(null, "Tengingin t�kst");
			
			return conn;
		}catch(Exception e){
			
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	

	
	public static void LeitaHotel(String tmp){
		try{
			
			if(Front.somethingWritten==false) { 
				qry = "Select * from Hotel, hotelfacilities where hotel.id=hotelfacilities.hotelid;";
			}else{
				qry = "Select * from Hotel,hotelfacilities where Hotel.id=Hotelfacilities.hotelid AND ( Hotel.name LIKE'%"+tmp+"%' OR Hotel.city LIKE'"+tmp+"%' OR Hotel.postcode LIKE'"+tmp+"%' OR Hotel.address LIKE'"+tmp+"%');";
				//select * from hotel, hotelfacilities where hotel.id = hotelfacilities.hotelid;
			}
			//System.out.println(qry + " tmp= " + tmp);
			
			PreparedStatement statement = Front.connection.prepareStatement(qry);
			statement.setQueryTimeout(30);
			rs = statement.executeQuery();
			//System.out.println(qry);
			
					while (rs.next()) {
						
						int id = Integer.parseInt(rs.getString("id"));
						String name = rs.getString("name");
						String address = rs.getString("address");
						int postcode = Integer.parseInt(rs.getString("postcode"));
						String city = rs.getString("city");
						String URL = rs.getString("URL");
						int wifi = Integer.parseInt(rs.getString("Wifi"));
						int FreeWifi = Integer.parseInt(rs.getString("FreeWifi"));
						int Smokearea = Integer.parseInt(rs.getString("SmokingArea"));
						int SPool = Integer.parseInt(rs.getString("Swimmingpool"));
						int Gym = Integer.parseInt(rs.getString("Gym"));
						int TV = Integer.parseInt(rs.getString("TV"));
						
						Hotel hotelTmp = new Hotel(id, name, address, postcode, city, URL,wifi,FreeWifi,Smokearea,SPool,Gym,TV);
						Front.resultHotel.add(hotelTmp);
						
						//System.out.println("hæhæ" +id + " " + name + " "  +address + " " +city + " " + URL +  "\n" );
						
					}
					//sqliteConnection.closeConnection(rs, statement, Front.connection);
			
		}catch(Exception e2){
			System.out.println(e2);
		}
		
	}
	
	public static void closeConnection(ResultSet results, Statement statement, Connection conn){
		try{
			if(results!=null){
				results.close();
			}
			if(statement!=null){
				statement.close();
			}
			if(conn!=null){
				conn.close();
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
	

	
}
