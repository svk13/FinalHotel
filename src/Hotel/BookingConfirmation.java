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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class BookingConfirmation extends JPanel {
	/**
	 * BookingConfirmation is a Jpanel that lets the user confirm his bookings.
	 * He adds into the textfields his information. hotel is a Hotel that the
	 * client has chosen. bookinginfo contains the information, days, number of
	 * room etc. that the booking needs. resid is the reservation id,
	 * numberofrooms is the number of rooms.
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
	private boolean recipientEmail = false;
	double totalPrice;
	private int resid;
	private int nrRooms;
	private int nrGuests;
	private Cursor waitCursor = new Cursor(Cursor.WAIT_CURSOR);
	private Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
	private int roomType=3;
	/**
	 * Create the panel.
	 */
	public BookingConfirmation(String a, final BookingInfo bookinginfo,
			final Hotel hotel) {
		setBackground(Color.WHITE);
		setSize(1109, 1000);
		setLayout(null);

		final HotelResult myHotel = new HotelResult(hotel, bookinginfo);
		// my.setBounds(24, 306, 339, 20);
		myHotel.setBounds(10, 69, 1089, 250);
		myHotel.setBackground(Color.WHITE);
		
		myHotel.button.setVisible(false);
		myHotel.setVisible(true);
		add(myHotel);
		
		this.bookinginfo = bookinginfo;
		this.hotel = hotel;
		DateInS = bookinginfo.getDateInString();
		DateOutS = bookinginfo.getDateOutString();
		 
		nrRooms = bookinginfo.getNumberOfRooms();
		fname = new JTextField();
		fname.setBackground(Color.WHITE);
		fname.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "First name",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0,
						255)));
		fname.setBounds(42, 361, 182, 47);
		add(fname);
		fname.setColumns(10);

		lname = new JTextField();
		lname.setBackground(Color.WHITE);
		lname.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Last name",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		lname.setBounds(42, 424, 182, 47);
		add(lname);
		lname.setColumns(10);

		emailtxt = new JTextField();
		emailtxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				recipientEmail=true;
			}
		});
		emailtxt.setBackground(Color.WHITE);
		emailtxt.setBorder(new TitledBorder(null, "Email",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		emailtxt.setBounds(42, 487, 182, 47);
		add(emailtxt);
		emailtxt.setColumns(10);

		special = new JTextField();
		special.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				special.setText("");
			}
		});
		special.setBackground(Color.WHITE);
		special.setBorder(new TitledBorder(null, "Special request",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		special.setBounds(42, 540, 277, 47);
		add(special);
		special.setColumns(10);
		special.setText(a);

		JLabel lblBookingConfirmation = new JLabel("Booking confirmation");
		lblBookingConfirmation.setForeground(Color.BLUE);
		lblBookingConfirmation.setFont(new Font("Times New Roman", Font.BOLD,
				23));
		lblBookingConfirmation.setBounds(408, 11, 237, 47);
		add(lblBookingConfirmation);

		/*
		 * Usage: actionperformed button Pre: Post: This button confirms the
		 * booking for the client.
		 */
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Front.frame.setCursor(waitCursor);

				String username = "hotelprojecticeland";
				String password = "sigosigo";
				String recipientEmails = emailtxt.getText();
				String title = "Your booking confirmation via HotelProjectIceland";
				String clientpassword = randomString();
				int nrclients = sqlWorkBench.NrOfClients();
				String ClientID = fname.getText() + nrclients;
				resid = (int) (Math.random() * 100000);
				System.out.println(hotel.getID()+ resid+
						DateInS+ DateOutS+ bookinginfo.getNumberOfRooms()+
						ClientID
						+clientpassword+"   "+roomType);
				FinaliseBooking.updateAllTheDataBase(hotel.getID(), resid,
						DateInS, DateOutS, bookinginfo.getNumberOfRooms(),
						ClientID, clientpassword, roomType);
				String message = "Thank you "
						+ fname.getText()
						+ " "
						+ lname.getText()
						+ ", \nfor booking your hotel using our services. \nYour username is: "
						+ ClientID
						+ " and password: "
						+ clientpassword
						+ "\n \np.s. your hotel has been informed of your special request that is: "
						+ special.getText();
				System.out.println("Username: " + ClientID + "\nPassword: " + clientpassword);
				if (recipientEmail == true) {
					try {
						GoogleMail.send(username, password, recipientEmails,
								title, message);
						JOptionPane.showMessageDialog(null, "Bókun staðfest");
					} catch (MessagingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				Front.frame.setCursor(defaultCursor);
				Front.cardLayout.show(Front.contentPane, "1");
			}
		});

		btnNewButton.setBounds(939, 552, 107, 37);
		add(btnNewButton);

		JLabel lblNewLabel_6 = new JLabel(DateInS);
		lblNewLabel_6.setBorder(new TitledBorder(null, "Date In",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 255,
						0)));
		lblNewLabel_6.setBounds(256, 365, 155, 37);
		add(lblNewLabel_6);

		JLabel label = new JLabel(DateOutS);
		label.setBorder(new TitledBorder(null, "Date Out",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 255,
						0)));
		label.setBounds(256, 415, 155, 37);
		add(label);
		int r = bookinginfo.getNumberOfRooms();

		int totalprice = r * hotel.getPriceOfRoomType3();
		String bill = "Number of guests: \nNumber of Rooms: \nNumber of days: \n------------------------------\nTotal price:\t";

		//
		JTextArea textArea = new JTextArea(bill);
		textArea.setEditable(false);
		textArea.setBackground(Color.WHITE);
		textArea.setBounds(644, 487, 155, 94);
		add(textArea);

		DecimalFormat twoPlaces = new DecimalFormat("0.00");
		
		nrGuests = bookinginfo.getNumberOfGuests();
		nrRooms = bookinginfo.getNumberOfRooms();
		totalPrice = hotel.getOrderPriceRoomType3(nrGuests, nrRooms)
				* Front.howManyDays;
		String finalPrice  = String.format("%,.2f", totalPrice);
		// prices shows the price of the room for a specific Hotel
		String prices = nrGuests + "\n" + nrRooms + "\n" + Front.howManyDays
				+ "\n----------------------\n"
				+ finalPrice+" ISK";
		final JTextArea textArea_1 = new JTextArea(prices);
		textArea_1.setEditable(false);
		textArea_1.setBackground(Color.WHITE);
		textArea_1.setBounds(799, 487, 125, 93);
		add(textArea_1);
		
		String labels[] = {"Economy suites", "Luxury suites","Presidential suites" };
		final JComboBox comboBox = new JComboBox(labels);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Object item = e.getItem();
				
			    if (item.toString().equals("Luxury suites")) {
			    	
			    	totalPrice = hotel.getOrderPriceRoomType2(nrGuests, nrRooms)
							* Front.howManyDays;
			    	String finalPrice  = String.format("%,.2f", totalPrice);
			    	String prices = nrGuests + "\n" + nrRooms + "\n" + Front.howManyDays
							+ "\n----------------------\n"
							+ finalPrice+" ISK";
			    	textArea_1.setText(prices);
			    	int noRooms = hotel.getRoomType2Count();
			    	if(noRooms<bookinginfo.getNumberOfRooms()){
			    		HotelResult.RoomsLabel.setText("");
			    		HotelResult.lblNumberOfRooms.setText("Sorry, these types of rooms are fully booked");
				    	
						HotelResult.lblPrice.setText("");
			    	}else{
			    	HotelResult.RoomsLabel.setText(noRooms+"");
			    	double totalPrice = hotel.getPriceOfRoomType2();
					String newPrice  = String.format("%,.2f", totalPrice);
					HotelResult.lblPrice.setText(newPrice+" ISK");
					}
			    	roomType = 2;
					repaint();
					updateUI();
			    }
			    else if (item.toString().equals("Presidential suites")) {
			    	
			    	totalPrice = hotel.getOrderPriceRoomType1(nrGuests, nrRooms)
							* Front.howManyDays;
			    	String finalPrice  = String.format("%,.2f", totalPrice);
			    	String prices = nrGuests + "\n" + nrRooms + "\n" + Front.howManyDays
							+ "\n----------------------\n"
							+ finalPrice+" ISK";
			    	textArea_1.setText(prices);
			    	int noRooms = hotel.getRoomType1Count();
			    	if(noRooms<bookinginfo.getNumberOfRooms()){
			    		HotelResult.RoomsLabel.setText("");
			    		HotelResult.lblNumberOfRooms.setText("Sorry, these types of rooms are fully booked");
						HotelResult.lblPrice.setText("");
			    	}else{
			    	HotelResult.RoomsLabel.setText(noRooms+"");
			    	double totalPrice = hotel.getPriceOfRoomType1();
					String newPrice  = String.format("%,.2f", totalPrice);
					HotelResult.lblPrice.setText(newPrice+" ISK");
					}
			    	roomType=1;
					repaint();
					updateUI();
			    }
			    else if (item.toString().equals("Economy suites")) {
			    	
			    	totalPrice = hotel.getOrderPriceRoomType3(nrGuests, nrRooms)
							* Front.howManyDays;
			    	String finalPrice  = String.format("%,.2f", totalPrice);
			    	String prices = nrGuests + "\n" + nrRooms + "\n" + Front.howManyDays
							+ "\n----------------------\n"
							+ finalPrice+" ISK";
			    	textArea_1.setText(prices);
			    	int noRooms = hotel.getRoomType3Count();
			    	if(noRooms<bookinginfo.getNumberOfRooms()){
			    		HotelResult.RoomsLabel.setText("");
			    		HotelResult.lblNumberOfRooms.setText("Sorry, these types of rooms are fully booked");
				    	
						HotelResult.lblPrice.setText("");
			    	}else{
			    	HotelResult.RoomsLabel.setText(noRooms+"");
			    	double totalPrice = hotel.getPriceOfRoomType3();
					String newPrice  = String.format("%,.2f", totalPrice);
					HotelResult.lblPrice.setText(newPrice+ " ISK");
					}
			    	roomType=3;
					repaint();
					updateUI();
			    }
			}
		});
		comboBox.setBounds(644, 428, 155, 26);
		add(comboBox);
		
		JLabel lblTypesOfRooms = new JLabel("Types of rooms");
		lblTypesOfRooms.setForeground(Color.BLUE);
		lblTypesOfRooms.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblTypesOfRooms.setBounds(644, 403, 125, 14);
		add(lblTypesOfRooms);
		int tmp = 1;
		Front.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
	}

	/*
	 * Usage: randomString(); Pre: Post: The method returns a random String that
	 * is used to create passwords.
	 */
	public String randomString() {
		Random r = new Random(); // Intialize a Random Number Generator with
									// SysTime as the seed
		int wordLength = r.nextInt(8) + 3;
		StringBuilder sb = new StringBuilder(wordLength);
		for (int i = 0; i < wordLength; i++) { // For each letter in the word
			char tmp = (char) ('a' + r.nextInt('z' - 'a')); // Generate a letter
															// between a and z
			sb.append(tmp); // Add it to the String
		}
		return sb.toString();
	}
}
