package Hotel;

import javax.mail.MessagingException;
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
	private JTextField credit;
	private JTextField textField_5;
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
		setLayout(null);
		
		this.bookinginfo=bookinginfo;
		this.hotel=hotel;
		DateInS = bookinginfo.getDateInString();
		DateOutS = bookinginfo.getDateOutString();
		numberofrooms = bookinginfo.getNumberOfRooms();
		fname = new JTextField();
		fname.setBounds(112, 86, 86, 20);
		add(fname);
		fname.setColumns(10);
		
		lname = new JTextField();
		lname.setBounds(112, 117, 86, 20);
		add(lname);
		lname.setColumns(10);
		
		emailtxt = new JTextField();
		emailtxt.setBounds(112, 148, 86, 20);
		add(emailtxt);
		emailtxt.setColumns(10);
		
		special = new JTextField();
		special.setBounds(112, 179, 251, 20);
		add(special);
		special.setColumns(10);
		special.setText(a);
		
		credit = new JTextField();
		credit.setBounds(112, 210, 121, 20);
		add(credit);
		credit.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(277, 210, 86, 20);
		add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("First name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(37, 89, 65, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Last name");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(37, 120, 65, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(56, 151, 46, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Special requests");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(24, 182, 78, 14);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Credit card info");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(10, 213, 92, 14);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("CVC");
		lblNewLabel_5.setBounds(243, 213, 46, 14);
		add(lblNewLabel_5);
		
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
				
				datevinnsla(DateInS, DateOutS);
				
				Bookings book = new Bookings(hotel.getID(), resid, DateInS,DateOutS, bookinginfo.getNumberOfRooms(),ClientID,clientpassword);
				String message = "Thank you " + fname.getText() + " " + lname.getText() + ", \nfor booking your hotel using our services. \nYour username is: "+ ClientID + " and password: "+ clientpassword +"\n \np.s. your hotel has been informed of your special request that is: " + special.getText();
				try {
					GoogleMail.send(username, password, recipientEmail, title, message);
					JOptionPane.showMessageDialog(null, "Bókun staðfest");
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Front.frame.setCursor(defaultCursor);
				Front.cardLayout.show(Front.contentPane, "1");
			}
		});
		
		btnNewButton.setBounds(277, 241, 86, 23);
		add(btnNewButton);

		JLabel lblNewLabel_6 = new JLabel(DateInS);
		lblNewLabel_6.setBorder(new TitledBorder(null, "Date In", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 255, 0)));
		lblNewLabel_6.setBounds(208, 89, 155, 37);
		add(lblNewLabel_6);
		
		JLabel label = new JLabel(DateOutS);
		label.setBorder(new TitledBorder(null, "Date Out", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 255, 0)));
		label.setBounds(208, 140, 155, 37);
		add(label);
		int r = bookinginfo.getNumberOfRooms();
		int[] p=hotel.getPrice();
		int totalprice = r*p[2];
		String bill = "Number of guests: \nNumber of Rooms: \nTotal price:\t";
		
		// 
		JTextArea textArea = new JTextArea(bill);
		textArea.setBackground(SystemColor.controlHighlight);
		textArea.setBounds(37, 240, 155, 62);
		add(textArea);
		
		// prices shows the price of the room for a specific Hotel
		String prices=  bookinginfo.getNumberOfGuests()+"\n"+ bookinginfo.getNumberOfRooms()+"\n"+totalprice;
		JTextArea textArea_1 = new JTextArea(prices);
		textArea_1.setBackground(SystemColor.controlHighlight);
		textArea_1.setBounds(191, 241, 59, 61);
		add(textArea_1);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon("src/Myndir/mynd.jpg"));
		lblNewLabel_7.setBounds(0, 0, Front.width, Front.height);
		add(lblNewLabel_7);
		

	}
	
	/* Usage: datevinnsla(in,out);
	 * Pre: in is a specific date in String form and so is out.
	 * Post: The method has added the dates of the reservation
	 * 		 that the client has chosen in his booking.  
	 */ 
	public void datevinnsla(String in, String out ){
	    
		String tmpin = in.substring(0, 2);
		String tmpout = out.substring(0, 2);
		int itmp = Integer.parseInt(tmpin);
		int itmpout = Integer.parseInt(tmpout);
		sqlWorkBench.reservedroom(hotel.getID(),resid , in, 3);
				
		while(itmp!=itmpout){
		
			SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy"); 
			Date myDate;
			try {
				myDate = format.parse(in);
				myDate = addDays(myDate, 1);
				String newdate = format.format(myDate);
				tmpin = newdate.substring(0, 2);
				itmp = Integer.parseInt(tmpin);
				
				for(int i = 0; i<numberofrooms;i++){
				sqlWorkBench.reservedroom(hotel.getID(),resid , newdate, 3);
				System.out.println(i + " booking into reservedroom");
				}
				
				in = newdate;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			
		
	}
	/* Usage: addDays(date, days);
	 * Pre: date is a Date and days is the number 
	 *  	of days that you want to add to your new date.
	 * Post: New date is returned that is 'days' many days later
	 *  	 than the original date. 
	 */ 
    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        //System.out.println("Nýjasta nýtt " + cal.getTime());
        return cal.getTime();
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
