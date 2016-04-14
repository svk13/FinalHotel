package Hotel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JDateChooser;

import java.beans.PropertyChangeListener;

public class ClientsBookingUI extends JPanel {
	static String labelTextaddress;
	static String labelTextname;
	static String labelTextcity;
	static String url;
	static String price;
	BookingConfirmation BookingConfPanel;
	static int labelTextid;
	static int labelTextpostcode;
	static int wifiteljari;
	static int freewifiteljari;
	static int numberOfLabels1;
	static BookingInfo bookinginfo;
	static JPanel panel_1;
	static JPanel panel;
	Date datein;
	Date dateout;
	String dateOutString;
	String dateInString;
	static ArrayList<HotelResult> resultList = new ArrayList<HotelResult>();
	final JDateChooser DateOutChooser;
	int numberOfLabels = Front.resultHotel.size();
	int count = 0;
	static ArrayList<Hotel> newList;
	ArrayList<Hotel> hotelList;

	/**
	 * Create the panel.
	 */
	public ClientsBookingUI(final Hotel hotel, final BookingInfo bookinginfo1) {
		numberOfLabels1 = Front.resultHotel.size();
		bookinginfo = bookinginfo1;
		System.out.println("I'm here ");

		final int[] details = { 0, 0, 0, 0, 0, 0 };
		this.datein = bookinginfo.getDateIn();
		this.dateout = bookinginfo.getDateout();
		dateInString = bookinginfo.getDateInString();
		dateOutString = bookinginfo.getDateOutString();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 150, 1500, 0 };
		gridBagLayout.rowHeights = new int[] { 547, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		final JPanel OptionPanel = new JPanel();
		OptionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		OptionPanel.setMaximumSize(new Dimension(150, 32767));
		OptionPanel.setBackground(Color.ORANGE);
		GridBagConstraints gbc_OptionPanel = new GridBagConstraints();
		gbc_OptionPanel.gridwidth = 1;
		gbc_OptionPanel.insets = new Insets(0, 0, 0, 5);
		gbc_OptionPanel.anchor = GridBagConstraints.WEST;
		gbc_OptionPanel.fill = GridBagConstraints.VERTICAL;
		gbc_OptionPanel.gridx = 0;
		gbc_OptionPanel.gridy = 0;
		add(OptionPanel, gbc_OptionPanel);
		GridBagLayout gbl_OptionPanel = new GridBagLayout();
		gbl_OptionPanel.columnWidths = new int[] { 150, 0 };
		gbl_OptionPanel.rowHeights = new int[] { 15, 15, 15, 15, 15, 15, 20, 20,
				20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 0 };
		gbl_OptionPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_OptionPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0,
				1.0, 1.0, 1.0 };
		OptionPanel.setLayout(gbl_OptionPanel);

		JLabel lblCheckOutDate = new JLabel("Check out date");
		lblCheckOutDate.setForeground(Color.GREEN);
		GridBagConstraints gbc_lblCheckOutDate = new GridBagConstraints();
		gbc_lblCheckOutDate.fill = GridBagConstraints.VERTICAL;
		gbc_lblCheckOutDate.insets = new Insets(0, 0, 5, 0);
		gbc_lblCheckOutDate.gridx = 0;
		gbc_lblCheckOutDate.gridy = 9;
		OptionPanel.add(lblCheckOutDate, gbc_lblCheckOutDate);

		JLabel lblCheckInDate = new JLabel("Check in date");
		lblCheckInDate.setForeground(Color.GREEN);
		GridBagConstraints gbc_lblCheckInDate = new GridBagConstraints();
		gbc_lblCheckInDate.fill = GridBagConstraints.VERTICAL;
		gbc_lblCheckInDate.insets = new Insets(0, 0, 5, 0);
		gbc_lblCheckInDate.gridx = 0;
		gbc_lblCheckInDate.gridy = 7;
		OptionPanel.add(lblCheckInDate, gbc_lblCheckInDate);
		
		BookingConfPanel = new BookingConfirmation("Do you have any requests?",
				bookinginfo1, hotel);
		GridBagConstraints gbc_BookingConfPanel = new GridBagConstraints();
		gbc_BookingConfPanel.gridwidth = 2;
		gbc_BookingConfPanel.insets = new Insets(0, 0, 0, 5);
		gbc_BookingConfPanel.fill = GridBagConstraints.BOTH;
		gbc_BookingConfPanel.gridx = 1;
		gbc_BookingConfPanel.gridy = 0;
		add(BookingConfPanel, gbc_BookingConfPanel);

		final JDateChooser DateInChooser = new JDateChooser();
		DateInChooser.setDate(datein);
		DateInChooser.getJCalendar().setMinSelectableDate(datein);
		DateInChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				datein = DateInChooser.getDate();
				dateInString = Front.convertStringToDate(datein);
				bookinginfo.setDateIn(datein);
				bookinginfo.setDateInS(dateInString);
				Date dateplus1 = Methods.addDays(datein, 1);
				Front.howManyDays = Methods.howManyDays(dateInString,
						dateOutString);
				DateOutChooser.getJCalendar().setMinSelectableDate(dateplus1);

				remove(BookingConfPanel);
				BookingConfPanel = new BookingConfirmation("Do you have any requests?",
						bookinginfo, hotel);
				GridBagConstraints gbc_panel_3 = new GridBagConstraints();
				gbc_panel_3.gridwidth = 2;
				gbc_panel_3.insets = new Insets(0, 0, 0, 5);
				gbc_panel_3.fill = GridBagConstraints.BOTH;
				gbc_panel_3.gridx = 1;
				gbc_panel_3.gridy = 0;
				add(BookingConfPanel, gbc_panel_3);
				updateUI();
				repaint();
			}
		});

		DateOutChooser = new JDateChooser();
		DateOutChooser.setDate(dateout);
		DateOutChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				dateout = DateOutChooser.getDate();
				dateOutString = Front.convertStringToDate(dateout);
				bookinginfo.setDateOutS(dateOutString);
				bookinginfo.setDateOut(dateout);
				Front.howManyDays = Methods.howManyDays(dateInString,
						dateOutString);

				remove(BookingConfPanel);
				BookingConfPanel = new BookingConfirmation("Do you have any requests?",
						bookinginfo, hotel);
				GridBagConstraints gbc_panel_3 = new GridBagConstraints();
				gbc_panel_3.gridwidth = 2;
				gbc_panel_3.insets = new Insets(0, 0, 0, 5);
				gbc_panel_3.fill = GridBagConstraints.BOTH;
				gbc_panel_3.gridx = 1;
				gbc_panel_3.gridy = 0;
				add(BookingConfPanel, gbc_panel_3);
				updateUI();
				repaint();
			}
		});

		GridBagConstraints gbc_DateOutChooser = new GridBagConstraints();
		gbc_DateOutChooser.fill = GridBagConstraints.BOTH;
		gbc_DateOutChooser.insets = new Insets(0, 0, 5, 0);
		gbc_DateOutChooser.gridx = 0;
		gbc_DateOutChooser.gridy = 10;
		OptionPanel.add(DateOutChooser, gbc_DateOutChooser);

		GridBagConstraints gbc_DateInChooser = new GridBagConstraints();
		gbc_DateInChooser.fill = GridBagConstraints.BOTH;
		gbc_DateInChooser.insets = new Insets(0, 0, 5, 0);
		gbc_DateInChooser.gridx = 0;
		gbc_DateInChooser.gridy = 8;
		OptionPanel.add(DateInChooser, gbc_DateInChooser);

		final JSpinner NumberOfRooms = new JSpinner(new SpinnerNumberModel(1, 1,
				30, 1));
		NumberOfRooms.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int val = (int) NumberOfRooms.getValue();
				bookinginfo.setNumberOfRooms(val);
				
				
				
				
				remove(BookingConfPanel);
				BookingConfPanel = new BookingConfirmation("Do you have any requests?",
						bookinginfo, hotel);
				GridBagConstraints gbc_panel_3 = new GridBagConstraints();
				gbc_panel_3.gridwidth = 2;
				gbc_panel_3.insets = new Insets(0, 0, 0, 5);
				gbc_panel_3.fill = GridBagConstraints.BOTH;
				gbc_panel_3.gridx = 1;
				gbc_panel_3.gridy = 0;
				add(BookingConfPanel, gbc_panel_3);
				updateUI();
				repaint();

			}
		});
		NumberOfRooms.setValue(bookinginfo.getNumberOfRooms());
		GridBagConstraints gbc_NumberOfRooms = new GridBagConstraints();
		gbc_NumberOfRooms.fill = GridBagConstraints.VERTICAL;
		gbc_NumberOfRooms.insets = new Insets(0, 0, 5, 0);
		gbc_NumberOfRooms.gridx = 0;
		gbc_NumberOfRooms.gridy = 14;
		OptionPanel.add(NumberOfRooms, gbc_NumberOfRooms);

		final JSpinner NumberOfGuests = new JSpinner(new SpinnerNumberModel(1, 1, 30,
				1));
		NumberOfGuests.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				datein = DateInChooser.getDate();
				dateInString = Front.convertStringToDate(datein);
				bookinginfo.setDateIn(datein);
				bookinginfo.setDateInS(dateInString);
				Date dateplus1 = Methods.addDays(datein, 1);
				Front.howManyDays = Methods.howManyDays(dateInString,
						dateOutString);
				DateOutChooser.getJCalendar().setMinSelectableDate(dateplus1);

			}
		});
		NumberOfGuests.setValue(bookinginfo.getNumberOfGuests());
		NumberOfGuests.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int rooms = (int) NumberOfRooms.getValue();
				int s = (int) NumberOfGuests.getValue();
				
				
				
				bookinginfo.setNumberOfGuests(s);
				remove(BookingConfPanel);
				BookingConfPanel = new BookingConfirmation("Do you have any requests?",
						bookinginfo, hotel);
				GridBagConstraints gbc_panel_3 = new GridBagConstraints();
				gbc_panel_3.gridwidth = 2;
				gbc_panel_3.insets = new Insets(0, 0, 0, 5);
				gbc_panel_3.fill = GridBagConstraints.BOTH;
				gbc_panel_3.gridx = 1;
				gbc_panel_3.gridy = 0;
				add(BookingConfPanel, gbc_panel_3);
				updateUI();
				repaint();
			}
		});
		GridBagConstraints gbc_NumberOfGuests = new GridBagConstraints();
		gbc_NumberOfGuests.fill = GridBagConstraints.VERTICAL;
		gbc_NumberOfGuests.insets = new Insets(0, 0, 5, 0);
		gbc_NumberOfGuests.gridx = 0;
		gbc_NumberOfGuests.gridy = 12;
		OptionPanel.add(NumberOfGuests, gbc_NumberOfGuests);

		JLabel lblNewLabel = new JLabel("Number of rooms");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 13;
		OptionPanel.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Number of guests");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 11;
		OptionPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		
		Front.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		/*
		 * if(bookinginfo.getNumberOfRooms()>roomsavailable){
		 * //System.out.println("Rooms available are " + roomsavailable +
		 * " bookinginfo rooms are" + bookinginfo.getNumberOfRooms());
		 * JOptionPane.showMessageDialog(null,
		 * "Sorry, we don't have enough rooms available for you.\nPlease change your booking information"
		 * ); }else{
		 * 
		 * 
		 * }
		 */

	}
}
