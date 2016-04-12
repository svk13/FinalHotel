package Hotel;

// Hlynur kl. 13.04 31.3

import javax.mail.MessagingException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.SystemColor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class BookingConfirmation extends JPanel {
	/** BookingConfirmation is a Jpanel that lets the user
	 *  confirm his bookings. He adds into the textfields
	 *  his information. hotel is a Hotel that the client
	 *  has chosen. bookinginfo contains the information, days,
	 *  number of room etc. that the booking needs. 
	 *  resid is the reservation id, numberofrooms is the number
	 *  of rooms.
	 */
	private static final long serialVersionUID = 1L;
	private JTextField fname;
	private JTextField lname;
	private JTextField emailtxt;
	private JTextField special;
    private String DateInS;
    private String DateOutS;
    private Hotel hotel;
    private BookingInfo bookinginfo;
 
    private int resid;
    private int numberofrooms;
    private Cursor waitCursor = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
	/**
	 * Create the panel.
	 */
	public BookingConfirmation(String a, final BookingInfo bookinginfo, final Hotel hotel ){
		setBackground(Color.ORANGE);
		setSize(1000,1000);
		setLayout(null);
		
		HotelResult myHotel = new HotelResult(hotel,bookinginfo);
		//my.setBounds(24, 306, 339, 20);
		myHotel.setBounds(15, 74, 800, 250);
		myHotel.setBackground(Color.ORANGE);
		myHotel.lblNewLabel_5.setBackground(Color.ORANGE);
		myHotel.button.setVisible(false);
		myHotel.setVisible(true);
		add(myHotel);
		
		this.bookinginfo=bookinginfo;
		this.hotel=hotel;
		DateInS = bookinginfo.getDateInString();
		DateOutS = bookinginfo.getDateOutString();
		numberofrooms = bookinginfo.getNumberOfRooms();
		fname = new JTextField();
		fname.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "First name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		fname.setBounds(42, 361, 182, 47);
		add(fname);
		fname.setColumns(10);
		
		lname = new JTextField();
		lname.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Last name", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		lname.setBounds(42, 424, 182, 47);
		add(lname);
		lname.setColumns(10);
		
		emailtxt = new JTextField();
		emailtxt.setBorder(new TitledBorder(null, "Email", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		emailtxt.setBounds(42, 487, 182, 47);
		add(emailtxt);
		emailtxt.setColumns(10);
		
		special = new JTextField();
		special.setBorder(new TitledBorder(null, "Special request", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		special.setBounds(42, 540, 277, 47);
		add(special);
		special.setColumns(10);
		special.setText(a);
		
		JLabel lblBookingConfirmation = new JLabel("Booking Confirmation");
		lblBookingConfirmation.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblBookingConfirmation.setBounds(112, 11, 237, 47);
		add(lblBookingConfirmation);
		
		/* Usage: actionperformed button
		 * Pre: 
		 * Post: This button confirms the booking for the 
		 * 		 client. 
		 */ 
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Front.frame.setCursor(waitCursor);
				
				String username = "hotelprojecticeland";
				String password = "sigosigo";
				String recipientEmail = emailtxt.getText();
				String title = "Your booking confirmation via HotelProjectIceland";
				String clientpassword = randomString();
				int nrclients = sqlWorkBench.NrOfClients();
				String ClientID = fname.getText()+nrclients;
				resid = (int) (Math.random()*100000);
				
				//datevinnsla(DateInS, DateOutS);
				
				//Bookings book = new Bookings(hotel.getID(), resid, DateInS,DateOutS, bookinginfo.getNumberOfRooms(),ClientID,clientpassword);
				//sqlWorkBench.makebooking(hotel.getID(), resid, DateInS, DateOutS, bookinginfo.getNumberOfRooms(),ClientID,clientpassword);
				FinaliseBooking.updateAllTheDataBase(hotel.getID(), resid, DateInS, DateOutS, bookinginfo.getNumberOfRooms(),ClientID,clientpassword, 3);
				String message = "Thank you " + fname.getText() + " " + lname.getText() + ", \nfor booking your hotel using our services. \nYour username is: "+ ClientID + " and password: "+ clientpassword +"\n \np.s. your hotel has been informed of your special request that is: " + special.getText();
				/*try {
					GoogleMail.send(username, password, recipientEmail, title, message);
					JOptionPane.showMessageDialog(null, "Bókun staðfest");
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
				Front.frame.setCursor(defaultCursor);
				Front.cardLayout.show(Front.contentPane, "1");
			}
		});
		
		btnNewButton.setBounds(282, 623, 86, 23);
		add(btnNewButton);

		JLabel lblNewLabel_6 = new JLabel(DateInS);
		lblNewLabel_6.setBorder(new TitledBorder(null, "Date In", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 255, 0)));
		lblNewLabel_6.setBounds(256, 365, 155, 37);
		add(lblNewLabel_6);
		
		JLabel label = new JLabel(DateOutS);
		label.setBorder(new TitledBorder(null, "Date Out", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 255, 0)));
		label.setBounds(256, 415, 155, 37);
		add(label);
		int r = bookinginfo.getNumberOfRooms();
		
		int totalprice = r*hotel.getPriceOfRoomType3();
		String bill = "Number of guests: \nNumber of Rooms: \nTotal price:\t";
		
		// 
		JTextArea textArea = new JTextArea(bill);
		textArea.setEditable(false);
		textArea.setBackground(Color.ORANGE);
		textArea.setBounds(42, 622, 155, 62);
		add(textArea);
		
		int nrGuests = bookinginfo.getNumberOfGuests();
		int nrRooms = bookinginfo.getNumberOfRooms();
		// prices shows the price of the room for a specific Hotel
		String prices=  nrGuests+"\n"+ nrRooms+"\n"+hotel.getOrderPriceRoomType3(nrGuests, nrRooms)*bookinginfo.howManyDays();
		JTextArea textArea_1 = new JTextArea(prices);
		textArea_1.setEditable(false);
		textArea_1.setBackground(Color.ORANGE);
		textArea_1.setBounds(196, 623, 59, 61);
		add(textArea_1);
		
		
		
		Front.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	
	/* Usage: randomString();
	 * Pre: 
	 * Post: The method returns a random String that 
	 * 		 is used to create passwords.
	 */ 
	public String randomString(){
		Random r = new Random(); // Intialize a Random Number Generator with SysTime as the seed
		int wordLength = r.nextInt(8)+3;
	    StringBuilder sb = new StringBuilder(wordLength);
	    for(int i = 0; i < wordLength; i++) { // For each letter in the word
	        char tmp = (char) ('a' + r.nextInt('z' - 'a')); // Generate a letter between a and z
	        sb.append(tmp); // Add it to the String
	    }
	    return sb.toString();
	}
}
