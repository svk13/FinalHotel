package Hotel;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;



public class sqliteConnection{
	
	static ResultSet rs;
	static Connection conn=null;
	static String qry;
	public static Connection dbConnector(){
		
		try{
			
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:src/Database/Hotel.db");
			JOptionPane.showMessageDialog(null, "Tengingin tókst");
			
			return conn;
		}catch(Exception e){
			
			JOptionPane.showMessageDialog(null, e);
			return null;
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
