package Hotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SearchControl {

	static Connection c;
	private ArrayList<Hotel> hotelList;

	/*
	 * Þetta eru einu klasarnir sem við notum frá flughópnum.
	 * 
	 * 1.Ykkar leitarfall mætti bara leita eftir öllu þessvegna en ef við viljum
	 * ekki leita eftir einhverjum ákveðnum parameter þá setjum við bara tóma
	 * strenginn og -1 hjá int parameturunum eða það sem er best fyrir ykkur.
	 * 
	 * 2. hér eru parametrarnir í getResults hjá daytours hópnum sem við köllum
	 * í þegar notandi leitar í viðmótinu okkar. Við setjum bara tóma strenginn
	 * í þann parameter sem við viljum ekki leita í public ArrayList<Tours>
	 * 
	 * getResults(String dur, String type, String diff, String area , String
	 * 
	 * lang, boolean pUp, boolean hCap, int date, int numberOfTickets) best væri
	 * að hafa int breyturnar (wifi,freewifi...) 3.þannig að ef þær eru 0 fra
	 * okkur væri best að fá allar niðurstöður hvort sem er wifi eða ekki, en ef
	 * 1 þá bara niðurstöður sem eru með wifi ekki -1 eins og ég sagði áðan
	 */

	// A method that searches for a hotel with the information in the textarea
	// in the Front class.
	public static ArrayList<Hotel> LeitaHotel(String tmp, String datein,
			String dateout) {
		ArrayList<Hotel> theList = new ArrayList<Hotel>();
		Connection c = sqliteConnection.dbConnector();
		try {

			String qry = "Select * from Hotel,hotelfacilities, room_price where Hotel.id=Hotelfacilities.hotelid AND Hotel.id = Room_price.Hotelid AND ( Hotel.name LIKE'%"
					+ tmp
					+ "%' OR Hotel.city LIKE'"
					+ tmp
					+ "%' OR Hotel.postcode LIKE'"
					+ tmp
					+ "%' OR Hotel.address LIKE'" + tmp + "%');";

			PreparedStatement statement = c.prepareStatement(qry);
			statement.setQueryTimeout(30);
			ResultSet HotelResultSet = statement.executeQuery();

			while (HotelResultSet.next()) {

				String name = HotelResultSet.getString("name");
				String address = HotelResultSet.getString("address");
				String city = HotelResultSet.getString("city");
				String URL = HotelResultSet.getString("URL");
				int id = Integer.parseInt(HotelResultSet.getString("id"));
				int postcode = Integer.parseInt(HotelResultSet
						.getString("postcode"));
				int wifi = Integer.parseInt(HotelResultSet.getString("Wifi"));
				int FreeWifi = Integer.parseInt(HotelResultSet
						.getString("FreeWifi"));
				int Smokearea = Integer.parseInt(HotelResultSet
						.getString("SmokingArea"));
				int SPool = Integer.parseInt(HotelResultSet
						.getString("Swimmingpool"));
				int Gym = Integer.parseInt(HotelResultSet.getString("Gym"));
				int TV = Integer.parseInt(HotelResultSet.getString("TV"));

				Hotel hotelTmp = new Hotel(id, name, address, postcode, city,
						URL, wifi, FreeWifi, Smokearea, SPool, Gym, TV);
				
				int minRooms1 = MinRoomsAvailable(id, datein, dateout, 1);
				int minRooms2 = MinRoomsAvailable(id, datein, dateout, 2);
				int minRooms3 = MinRoomsAvailable(id, datein, dateout, 3);
				hotelTmp.setPriceOfRoomType1(Integer.parseInt(HotelResultSet
						.getString("type1")));
				hotelTmp.setPriceOfRoomType2(Integer.parseInt(HotelResultSet
						.getString("type2")));
				hotelTmp.setPriceOfRoomType3(Integer.parseInt(HotelResultSet
						.getString("type3")));
				hotelTmp.setRoomType1Count(Integer.parseInt(HotelResultSet
						.getString("counttype1")) - minRooms1);
				hotelTmp.setRoomType2Count(Integer.parseInt(HotelResultSet
						.getString("counttype2")) - minRooms2);
				hotelTmp.setRoomType3Count(Integer.parseInt(HotelResultSet
						.getString("counttype3")) - minRooms3);

				theList.add(hotelTmp);

			}

			HotelResultSet.close();
			statement.close();

		} catch (Exception e2) {
			System.out.println(e2);

		}

		return theList;

	}

	public static int MinRoomsAvailable(int hotelid, String dateres,
			String dateout, int roomtype) {
		String name = "0";

		ArrayList<String> myDays = datevinnsla(dateres, dateout);
		for (int i = 0; i < myDays.size(); i++) {
			try {
				c = sqliteConnection.dbConnector();
				String NoOfRoomsTaken = "select *, count(hotelid) as pi from roomreserved where hotelid='"
						+ hotelid
						+ "' and datereserved = '"
						+ myDays.get(i)
						+ "' and roomtype = '"
						+ roomtype
						+ "'  group by datereserved;";
				PreparedStatement statement = c
						.prepareStatement(NoOfRoomsTaken);
				System.out.println(NoOfRoomsTaken + " qry");
				statement.setQueryTimeout(30);
				ResultSet rs = statement.executeQuery();
				;
				while (rs.next()) {
					String tmp = rs.getString("pi");
					if (Integer.parseInt(name) < Integer.parseInt(tmp)) {
						name = tmp;
					}
				}

			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
		System.out.println(name + "       hversu mörg herbergi");
		return Integer.parseInt(name);
	}

	public static ArrayList<String> datevinnsla(String in, String out) {
		String tmpin = in.substring(0, 2);
		String tmpout = out.substring(0, 2);
		int itmp = Integer.parseInt(tmpin);
		int itmpout = Integer.parseInt(tmpout);
		ArrayList<String> dayList = new ArrayList<String>();
		dayList.add(in);
		// System.out.println("in " + in + " out " + out);
		while (itmp != itmpout) {

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

	// A method that adds a certain amount of days to a date.
	// The method adds 'days' days to the date.
	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days); // minus number would decrement the days
		// System.out.println("Nýjasta nýtt " + cal.getTime());
		return cal.getTime();
	}

}
