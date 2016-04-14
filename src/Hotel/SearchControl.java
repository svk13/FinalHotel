package Hotel;
/**
 * SearchControl inniheldur fj�rar a�fer�ir. Eina a�ala�fer� sem finnur �au H�tel sem uppfylla
 * skilyr�i notanda. Og �rj� hj�lparf�ll sem a�ala�fer�in notar til �ess a� vinna me� dagsetningar.
 * @author Atli ��r J�hansson, Hlynur Logi �orsteinsson, Sindri Ing�lfsson og Sigurbj�rn Vi�ar Karlsson
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SearchControl {

	
	/**
	 * SearchControl klasinn s�r um alla leit a� H�telum � databasenum.
	 * � honum eru v��v�ru breyturnar c, sem er Connection til �ess a�
	 * tengjast databasenum. hotelList sem er ArrayListi fyrir Hotel
	 * hluti. 
	 * 
	 * � SearchControl eru fj�gur f�ll. LeitaHotel sem skilar Hotel listanum
	 * sem fer eftir eftirspurn notandans. 
	 * 
	 * LeitaHotel notar svo �rj� hj�lparf�ll fyrir vinnslu � dagsetningum.
	 * �st��an fyrir �essu er s� a� ef notandi leitar a� herbergjum fr� 
	 * dagsetningu 1 til 3. �� getur �kve�i� h�tel haft laust herbergi
	 * � dagsetningu 1, dagsetningu 3 en ekki dagsetningu 2. �.a. 
	 * MinRoomsAvailable skilar svo h�marksfj�lda herbergja sem p�ntu� eru fyrir 
	 * �kve�in dag fyrir t�mabili� sem spurt er um. Nafni� � fallinu er �vi villandi.
	 * 
	 * MinRoomsAvailable notar svo datevinnsla og addDays sem hj�lparf�ll.
	 * 
	 * Athugi�, a� til �ess a� f� ver�i� � b�kuninni, �� er Hotel me� get Skipun
	 * sem n�r � ver�i� mi�a� vi� p�ntunina. �.e. fj�lda k�nna og fj�lda herbergja panta�.
	 * Einnig hva�a t�pu af herbergjum, 1 2 e�a 3. 
	 * 
	 *  �� �arf a� gera:
	 * 
	 * int i = hotelid.getOrderRoomPrice1(fjoldik�nna, fj�ldiherbergja);
	 * �etta gildir l�ka fyrir t�pu 2 og 3 af herbergjum. �.e. getOrderRoomprice2 og 3.
	 * 
	 * 
	 * @param tmp skilyr�i fr� notanda - Leitar strengur
	 * @param datein skilyr�i fr� notanda - Dagsetning innritunar
	 * @param dateout  skilyr�i fr� notanda - Dagsetning �tritunar
	 * @return H�tel sem uppfylla skilyr�i notanda
	 */
	/*
	 * �etta eru einu klasarnir sem vi� notum fr� flugh�pnum.
	 * 
	 * 1.Ykkar leitarfall m�tti bara leita eftir �llu �essvegna en ef vi� viljum
	 * ekki leita eftir einhverjum �kve�num parameter �� setjum vi� bara t�ma
	 * strenginn og -1 hj� int parameturunum e�a �a� sem er best fyrir ykkur.
	 * 
	 * 2. h�r eru parametrarnir � getResults hj� daytours h�pnum sem vi� k�llum
	 * � �egar notandi leitar � vi�m�tinu okkar. Vi� setjum bara t�ma strenginn
	 * � �ann parameter sem vi� viljum ekki leita � public ArrayList<Tours>
	 * 
	 * getResults(String dur, String type, String diff, String area , String
	 * 
	 * lang, boolean pUp, boolean hCap, int date, int numberOfTickets) best v�ri
	 * a� hafa int breyturnar (wifi,freewifi...) 3.�annig a� ef ��r eru 0 fra
	 * okkur v�ri best a� f� allar ni�urst��ur hvort sem er wifi e�a ekki, en ef
	 * 1 �� bara ni�urst��ur sem eru me� wifi ekki -1 eins og �g sag�i ��an
	 */

	
	
	// A method that searches for a hotel with the information in the textarea
	// in the Front class.
	public static ArrayList<Hotel> LeitaHotel(String tmp, String datein,
			String dateout) {
		ArrayList<Hotel> theList = new ArrayList<Hotel>();
		
		
		
		try {
			//Tenging vi� Hotel.db 
			//c = sqliteConnection.dbConnector();
			//B�um til sql skipun.
			String qry = "Select * from Hotel,hotelfacilities, room_price where Hotel.id=Hotelfacilities.hotelid AND Hotel.id = Room_price.Hotelid AND ( Hotel.name LIKE'%"
					+ tmp
					+ "%' OR Hotel.city LIKE'"
					+ tmp
					+ "%' OR Hotel.postcode LIKE'"
					+ tmp
					+ "%' OR Hotel.address LIKE'" + tmp + "%');";

			//F�um g�gnin fr� gagnagrunninum.
			PreparedStatement statement = Front.connection.prepareStatement(qry);
			statement.setQueryTimeout(30);
			ResultSet HotelResultSet = statement.executeQuery();

			//Loopum � gegnum ResultSet-i� og b�um til Hotel hluti sem vi�
			//setjum svo inn � ArrayLista
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
				
				//H�r athugum vi� hversu m�rg herbergi eru laus fyrir hvert
				//h�tel og stillum fj�lda lausra herbergja samkv�mt �vi.
				//Stillum einnig ver�in � herbergjunum. � databasenum
				// eru �rj�r tegundir af herbergjum. 1-3, �.s. 1 er d�rast
				// og 3 �d�rast. 1 hefur f�st herbergi og 3 flest.
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

	/* �etta fall loopar � gegnum allar dagsetningar fr� dateres til dateout.
	 * �a� finnur dagsetninguna me� flestum b�kunum og skilar fj�lda b�kanna �ann
	 * dag. S� tala er svo notu� til a� reikna �t hversu m�rg herbergi eru laus
	 * fyrir �kve�i� h�tel � �kve�nu t�mabili.
	 * 
	 * ATH: �i� �urfi� ekkert a� p�la � �essum f�llum sem eru h�r. �etta 
	 * eru einungis hj�lparf�ll fyrir LeitaHotel.
	 * 
	 */
	public static int MinRoomsAvailable(int hotelid, String dateres,
			String dateout, int roomtype) {
		String name = "0";

		ArrayList<String> myDays = datevinnsla(dateres, dateout);
		for (int i = 0; i < myDays.size(); i++) {
			try {
				
				String NoOfRoomsTaken = "select *, count(hotelid) as pi from roomreserved where hotelid='"
						+ hotelid
						+ "' and datereserved = '"
						+ myDays.get(i)
						+ "' and roomtype = '"
						+ roomtype
						+ "'  group by datereserved;";
				PreparedStatement statement = Front.connection
						.prepareStatement(NoOfRoomsTaken);
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
				System.err.println( e2.getClass().getName() + ": " + e2.getMessage() );
	            System.exit(0);
			}
			
		}
		return Integer.parseInt(name);
	}

	/*
	 	Fall sem b�r til ArrayLista me� strengjum fyrir hverja dagsetningu
	 	fr� in og out.(innritun og �tritun).
	 */
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
		
		return cal.getTime();
	}
	
	public static int editDistance(String s1, String s2) {
	    s1 = s1.toLowerCase();
	    s2 = s2.toLowerCase();

	    int[] costs = new int[s2.length() + 1];
	    for (int i = 0; i <= s1.length(); i++) {
	      int lastValue = i;
	      for (int j = 0; j <= s2.length(); j++) {
	        if (i == 0)
	          costs[j] = j;
	        else {
	          if (j > 0) {
	            int newValue = costs[j - 1];
	            if (s1.charAt(i - 1) != s2.charAt(j - 1))
	              newValue = Math.min(Math.min(newValue, lastValue),
	                  costs[j]) + 1;
	            costs[j - 1] = lastValue;
	            lastValue = newValue;
	          }
	        }
	      }
	      if (i > 0)
	        costs[s2.length()] = lastValue;
	    }
	    return costs[s2.length()];
	  }
	
	 public static double similarity(String s1, String s2) {
		    String longer = s1, shorter = s2;
		    if (s1.length() < s2.length()) { // longer should always have greater length
		      longer = s2; shorter = s1;
		    }
		    int longerLength = longer.length();
		    if (longerLength == 0) { return 1.0; /* both strings are zero length */ }
		    /* // If you have StringUtils, you can use it to calculate the edit distance:
		    return (longerLength - StringUtils.getLevenshteinDistance(longer, shorter)) /
		                               (double) longerLength; */
		    return (longerLength - editDistance(longer, shorter)) / (double) longerLength;

		  }


}
