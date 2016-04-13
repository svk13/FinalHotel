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
    BookingConfirmation panel_3;
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
    static ArrayList<HotelResult> resultList= new ArrayList<HotelResult>();
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
		
		final int[] details = {0,0,0,0,0,0};
		this.datein = bookinginfo.getDateIn();
		this.dateout = bookinginfo.getDateout();
		dateInString = bookinginfo.getDateInString();
		dateOutString = bookinginfo.getDateOutString();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{150, 1500, 0};
		gridBagLayout.rowHeights = new int[]{547, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
				final JPanel panel_2 = new JPanel();
				panel_2.setAlignmentX(Component.LEFT_ALIGNMENT);
				panel_2.setMaximumSize(new Dimension(150, 32767));
				panel_2.setBackground(Color.ORANGE);
				GridBagConstraints gbc_panel_2 = new GridBagConstraints();
				gbc_panel_2.gridwidth = 1;
				gbc_panel_2.insets = new Insets(0, 0, 0, 5);
				gbc_panel_2.anchor = GridBagConstraints.WEST;
				gbc_panel_2.fill = GridBagConstraints.VERTICAL;
				gbc_panel_2.gridx = 0;
				gbc_panel_2.gridy = 0;
				add(panel_2, gbc_panel_2);
				GridBagLayout gbl_panel_2 = new GridBagLayout();
				gbl_panel_2.columnWidths = new int[]{150, 0};
				gbl_panel_2.rowHeights = new int[] {15, 15, 15, 15, 15, 15, 20, 20, 20,20,20,20,20,20,20,20, 20, 20, 20, 20, 20, 0};
				gbl_panel_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
				gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
				panel_2.setLayout(gbl_panel_2);
				
				
	
	
				
				JLabel lblCheckOutDate = new JLabel("Check out date");
				lblCheckOutDate.setForeground(Color.GREEN);
				GridBagConstraints gbc_lblCheckOutDate = new GridBagConstraints();
				gbc_lblCheckOutDate.fill = GridBagConstraints.VERTICAL;
				gbc_lblCheckOutDate.insets = new Insets(0, 0, 5, 0);
				gbc_lblCheckOutDate.gridx = 0;
				gbc_lblCheckOutDate.gridy = 9;
				panel_2.add(lblCheckOutDate, gbc_lblCheckOutDate);
				
				JLabel lblCheckInDate = new JLabel("Check in date");
				lblCheckInDate.setForeground(Color.GREEN);
				GridBagConstraints gbc_lblCheckInDate = new GridBagConstraints();
				gbc_lblCheckInDate.fill = GridBagConstraints.VERTICAL;
				gbc_lblCheckInDate.insets = new Insets(0, 0, 5, 0);
				gbc_lblCheckInDate.gridx = 0;
				gbc_lblCheckInDate.gridy = 7;
				panel_2.add(lblCheckInDate, gbc_lblCheckInDate);
				
				final JDateChooser dateChooser = new JDateChooser();
				dateChooser.setDate(datein);
				dateChooser.getJCalendar().setMinSelectableDate(datein);
				dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent arg0) {
						datein = dateChooser.getDate();
						dateInString = Front.convertStringToDate(datein);
						bookinginfo.setDateIn(datein);
						bookinginfo.setDateInS(dateInString);
						Date dateplus1 = Methods.addDays(datein, 1);
						Front.howManyDays = Methods.howManyDays(dateInString, dateOutString);
						DateOutChooser.getJCalendar().setMinSelectableDate(dateplus1);
						
						remove(panel_3);
						panel_3 = new BookingConfirmation("Do you have any requests?", bookinginfo, hotel);
						GridBagConstraints gbc_panel_3 = new GridBagConstraints();
						gbc_panel_3.gridwidth = 2;
						gbc_panel_3.insets = new Insets(0, 0, 0, 5);
						gbc_panel_3.fill = GridBagConstraints.BOTH;
						gbc_panel_3.gridx = 1;
						gbc_panel_3.gridy = 0;
						add(panel_3, gbc_panel_3);
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
						Front.howManyDays = Methods.howManyDays(dateInString, dateOutString);
						
						remove(panel_3);
						panel_3 = new BookingConfirmation("Do you have any requests?", bookinginfo, hotel);
						GridBagConstraints gbc_panel_3 = new GridBagConstraints();
						gbc_panel_3.gridwidth = 2;
						gbc_panel_3.insets = new Insets(0, 0, 0, 5);
						gbc_panel_3.fill = GridBagConstraints.BOTH;
						gbc_panel_3.gridx = 1;
						gbc_panel_3.gridy = 0;
						add(panel_3, gbc_panel_3);
						updateUI();
						repaint();
					}
				});
				
				GridBagConstraints gbc_DateOutChooser = new GridBagConstraints();
				gbc_DateOutChooser.fill = GridBagConstraints.BOTH;
				gbc_DateOutChooser.insets = new Insets(0, 0, 5, 0);
				gbc_DateOutChooser.gridx = 0;
				gbc_DateOutChooser.gridy = 10;
				panel_2.add(DateOutChooser, gbc_DateOutChooser);
				


				
				GridBagConstraints gbc_dateChooser = new GridBagConstraints();
				gbc_dateChooser.fill = GridBagConstraints.BOTH;
				gbc_dateChooser.insets = new Insets(0, 0, 5, 0);
				gbc_dateChooser.gridx = 0;
				gbc_dateChooser.gridy = 8;
				panel_2.add(dateChooser, gbc_dateChooser);
				
	
				final JSpinner spinner_1 = new JSpinner(new SpinnerNumberModel(1, 1,
						30, 1));
				spinner_1.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent e) {
						int val = (int) spinner_1.getValue();
						bookinginfo.setNumberOfRooms(val);
						remove(panel_3);
						panel_3 = new BookingConfirmation("Do you have any requests?", bookinginfo, hotel);
						GridBagConstraints gbc_panel_3 = new GridBagConstraints();
						gbc_panel_3.gridwidth = 2;
						gbc_panel_3.insets = new Insets(0, 0, 0, 5);
						gbc_panel_3.fill = GridBagConstraints.BOTH;
						gbc_panel_3.gridx = 1;
						gbc_panel_3.gridy = 0;
						add(panel_3, gbc_panel_3);
						updateUI();
						repaint();
						
					}
				});
				spinner_1.setValue(bookinginfo.getNumberOfRooms());
				GridBagConstraints gbc_spinner_1 = new GridBagConstraints();
				gbc_spinner_1.fill = GridBagConstraints.VERTICAL;
				gbc_spinner_1.insets = new Insets(0, 0, 5, 0);
				gbc_spinner_1.gridx = 0;
				gbc_spinner_1.gridy = 14;
				panel_2.add(spinner_1, gbc_spinner_1);
				
				
				final JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1,
						30, 1));
				spinner.addPropertyChangeListener(new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent arg0) {
						datein = dateChooser.getDate();
						dateInString = Front.convertStringToDate(datein);
						bookinginfo.setDateIn(datein);
						bookinginfo.setDateInS(dateInString);
						Date dateplus1 = Methods.addDays(datein, 1);
						Front.howManyDays = Methods.howManyDays(dateInString, dateOutString);
						DateOutChooser.getJCalendar().setMinSelectableDate(dateplus1);
						
						
					}
				});
				spinner.setValue(bookinginfo.getNumberOfGuests());
				spinner.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent arg0) {
						int rooms = (int) spinner_1.getValue();
						int s = (int) spinner.getValue();
						bookinginfo.setNumberOfGuests(s);
						remove(panel_3);
						panel_3 = new BookingConfirmation("Do you have any requests?", bookinginfo, hotel);
						GridBagConstraints gbc_panel_3 = new GridBagConstraints();
						gbc_panel_3.gridwidth = 2;
						gbc_panel_3.insets = new Insets(0, 0, 0, 5);
						gbc_panel_3.fill = GridBagConstraints.BOTH;
						gbc_panel_3.gridx = 1;
						gbc_panel_3.gridy = 0;
						add(panel_3, gbc_panel_3);
						updateUI();
						repaint();
					}
				});
				GridBagConstraints gbc_spinner = new GridBagConstraints();
				gbc_spinner.fill = GridBagConstraints.VERTICAL;
				gbc_spinner.insets = new Insets(0, 0, 5, 0);
				gbc_spinner.gridx = 0;
				gbc_spinner.gridy = 12;
				panel_2.add(spinner, gbc_spinner);
				
				JLabel lblNewLabel = new JLabel("Number of rooms");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
				gbc_lblNewLabel.gridx = 0;
				gbc_lblNewLabel.gridy = 13;
				panel_2.add(lblNewLabel, gbc_lblNewLabel);
				
				JLabel lblNewLabel_1 = new JLabel("Number of guests");
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
				gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
				gbc_lblNewLabel_1.gridx = 0;
				gbc_lblNewLabel_1.gridy = 11;
				panel_2.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		panel_3 = new BookingConfirmation("Do you have any requests?", bookinginfo1, hotel);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridwidth = 2;
		gbc_panel_3.insets = new Insets(0, 0, 0, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 0;
		add(panel_3, gbc_panel_3);
		Front.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
	/*	if(bookinginfo.getNumberOfRooms()>roomsavailable){
			//System.out.println("Rooms available are " + roomsavailable + " bookinginfo rooms are" + bookinginfo.getNumberOfRooms());
			JOptionPane.showMessageDialog(null, "Sorry, we don't have enough rooms available for you.\nPlease change your booking information");
		}else{
			
			
		}
		*/
		
		

	}
}
