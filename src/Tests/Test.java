package Tests;
	import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;

import Hotel.Front;
import Hotel.Hotel;
import Hotel.sqlWorkBench;
import Hotel.sqliteConnection;

//Komment
public class Test {

	String hotel=null;
	
	@org.junit.Test
	public void test() {
		
		 assertEquals("Hotel Hilton", hotel);
		 //assertEquals("Not gonna happen",hotel);
		//fail("Not yet implemented");
	}
	
	@Before
	public void setUp() {
		Front.connection = sqliteConnection.dbConnector();
		hotel = sqlWorkBench.HotelName("1");
		System.out.println(hotel);
	}
	@After
	public void tearDown() {
		hotel=null;
		System.out.println(hotel);
		
	}
	
	Hotel thehotel;
	@Before
	public void setUp2() {
		Front.connection = sqliteConnection.dbConnector();
		sqlWorkBench.LeitaHotel("");
		//Hotel newHotel = new Hotel(32, "The name", "The address", 32, "The city" , "www.theurrl.com",1, 1, 1, 1, 1, 1);
		thehotel = Front.resultHotel.get(0);
		
		System.out.println(hotel);
	}
	
	@org.junit.Test
	public void test2() {
		 
		 assertEquals(Hotel.class, thehotel.getClass());
		 
	}
	
	
	@After
	public void tearDown2() {
		thehotel=null;
		System.out.println(hotel);
		
	}
	

}
