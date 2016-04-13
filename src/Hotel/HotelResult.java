package Hotel;


import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.Desktop;
import java.awt.Font;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;


public class HotelResult extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String DateInS;
    private String DateOutS;
    private BookingInfo bookinginfo1;
    private int numberofrooms;
    private int roomsavailable;
    static JButton button; 
   
    static JLabel RoomsLabel;
    static JLabel lblPrice;
    static JLabel lblNumberOfRooms; 
	/**
	 * Create the panel.
	 */
	public HotelResult(final Hotel hotel,  final BookingInfo bookinginfo) {
		setBackground(Color.WHITE);
		
		setForeground(Color.BLUE);
		
		setSize(new Dimension(601, 230));
		setMinimumSize(new Dimension(630, 230));
		setMaximumSize(new Dimension(630, 230));
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		String name = hotel.getName();
		String city = hotel.getCity();
		numberofrooms=bookinginfo.getNumberOfRooms();
		int wifi = hotel.getWifi();
		int FreeWifi = hotel.getFreeWifi();
		int smoke = hotel.getSmoke();
		int swim = hotel.getPool();
		int gym = hotel.getGym();
		int tv = hotel.getTV();
		int postcode = hotel.getPostcode();
		DateInS = bookinginfo.getDateInString();
		DateOutS = bookinginfo.getDateOutString();
		bookinginfo1 = bookinginfo;
		
		int price = hotel.getPriceOfRoomType3();
		setBounds(0,0,630,230);
		setSize(630,230);
		int tmp = hotel.getID();
		int tmp3 = (tmp%10)+1;
		String tmp2= ""+tmp3;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{259, 79, 46, 137, 124, 0};
		gridBagLayout.rowHeights = new int[]{41, 29, 34, 14, 41, 20, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		final JLabel lblNewLabel = new JLabel("");
		
		lblNewLabel.setIcon(new ImageIcon("src/Myndir/"+tmp2+".jpg"));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridheight = 5;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		final JLabel lblNewLabel_1 = new JLabel(name);
		lblNewLabel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_1.setToolTipText("Click here to order your room");
		
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 22));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridwidth = 4;
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 0;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
	
		JLabel lblNewLabel_2 = new JLabel(city);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 1;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel(postcode +"");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 2;
		gbc_lblNewLabel_4.gridy = 1;
		add(lblNewLabel_4, gbc_lblNewLabel_4);
		String info = Methods.info(wifi, FreeWifi, smoke, swim, gym,tv);
		final JTextArea lblNewLabel_5 = new JTextArea(info);
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setBackground(new Color(240, 248, 255));
				lblNewLabel_5.setBackground(new Color(240, 248, 255));
			}
		});
		
		lblNewLabel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Facilities", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 255, 0)));
		lblNewLabel_5.setBackground(Color.WHITE);
		lblNewLabel_5.setEditable(false);
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridheight = 3;
		gbc_lblNewLabel_5.gridwidth = 2;
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 2;
		add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				//setBorder(new MatteBorder(5, 5, 5, 5, (Color) Color.ORANGE));
				setBackground(new Color(240, 248, 255));
				lblNewLabel_5.setBackground(new Color(240, 248, 255));
				
				repaint();
				updateUI();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				//setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				setBackground(Color.WHITE);
				lblNewLabel_5.setBackground(Color.WHITE);
			}
		});
		
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 23));
				lblNewLabel_1.setForeground(Color.BLUE);
				setBackground(new Color(240, 248, 255));
				lblNewLabel_5.setBackground(new Color(240, 248, 255));
				//setBorder(new MatteBorder(5, 5, 5, 5, (Color) Color.ORANGE));
				repaint();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 22));
				lblNewLabel_1.setForeground(Color.BLACK);
				lblNewLabel_5.setBackground(Color.WHITE);
				setBackground(Color.WHITE);
				repaint();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(bookinginfo.getNumberOfRooms()>roomsavailable){
					//System.out.println("Rooms available are " + roomsavailable + " bookinginfo rooms are" + bookinginfo.getNumberOfRooms());
					JOptionPane.showMessageDialog(null, "Sorry, we don't have enough rooms available for you.\nPlease change your booking information");
				}else{
					BookingConfirmation book = new BookingConfirmation("Do you have any requests?", bookinginfo1, hotel);
					Front.contentPane.add(book, "3");
					Front.cardLayout.show(Front.contentPane, "3");	
					Front.whatpage=3;
					Front.forwardTakki.setEnabled(false);
				}
			}
		});
		
		
		lblNumberOfRooms = new JLabel("Number of rooms available");
		lblNumberOfRooms.setBackground(Color.BLUE);
		lblNumberOfRooms.setForeground(Color.BLUE);
		lblNumberOfRooms.setFont(new Font("Tahoma", Font.BOLD, 10));
		GridBagConstraints gbc_lblNumberOfRooms = new GridBagConstraints();
		gbc_lblNumberOfRooms.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblNumberOfRooms.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfRooms.gridx = 3;
		gbc_lblNumberOfRooms.gridy = 2;
		roomsavailable = hotel.getRoomType3Count();
		add(lblNumberOfRooms, gbc_lblNumberOfRooms);
		RoomsLabel = new JLabel("" +roomsavailable);
		
		
		RoomsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.SOUTHWEST;
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 4;
		gbc_label.gridy = 2;
		add(RoomsLabel, gbc_label);
		
		JLabel lblPricesFrom = new JLabel("Prices from");
		lblPricesFrom.setBackground(Color.BLUE);
		lblPricesFrom.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPricesFrom.setForeground(Color.BLUE);
		GridBagConstraints gbc_lblPricesFrom = new GridBagConstraints();
		gbc_lblPricesFrom.anchor = GridBagConstraints.EAST;
		gbc_lblPricesFrom.fill = GridBagConstraints.VERTICAL;
		gbc_lblPricesFrom.insets = new Insets(0, 0, 5, 5);
		gbc_lblPricesFrom.gridx = 3;
		gbc_lblPricesFrom.gridy = 3;
		add(lblPricesFrom, gbc_lblPricesFrom);
		
		double totalPrice = hotel.getPriceOfRoomType3();
		String finalPrice  = String.format("%,.2f", totalPrice);
		lblPrice = new JLabel(finalPrice+" ISK");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrice.setForeground(Color.BLUE);
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.WEST;
		gbc_lblPrice.fill = GridBagConstraints.VERTICAL;
		gbc_lblPrice.insets = new Insets(0, 0, 5, 0);
		gbc_lblPrice.gridx = 4;
		gbc_lblPrice.gridy = 3;
		add(lblPrice, gbc_lblPrice);
		
		button = new JButton("Choose room");
		button.setToolTipText("Click here to order your room");
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				//setBorder(new MatteBorder(5, 5, 5, 5, (Color) Color.ORANGE));
				lblNewLabel_5.setBackground(new Color(240, 248, 255));
				setBackground(new Color(240, 248, 255));
			}
		});

		button.setForeground(Color.BLUE);
		button.setAlignmentX(Component.RIGHT_ALIGNMENT);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(bookinginfo.getNumberOfRooms()>roomsavailable){
					//System.out.println("Rooms available are " + roomsavailable + " bookinginfo rooms are" + bookinginfo.getNumberOfRooms());
					JOptionPane.showMessageDialog(null, "Sorry, we don't have enough rooms available for you.\nPlease change your booking information");
				}else{
					BookingConfirmation book = new BookingConfirmation("Do you have any requests?", bookinginfo1, hotel);
					Front.contentPane.add(book, "3");
					Front.cardLayout.show(Front.contentPane, "3");	
					Front.whatpage=3;
					Front.forwardTakki.setEnabled(false);
				}

			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.anchor = GridBagConstraints.EAST;
		gbc_button.fill = GridBagConstraints.VERTICAL;
		gbc_button.gridwidth = 1;
		gbc_button.gridx = 3;
		gbc_button.gridy = 4;
		add(button, gbc_button);
		
		if(roomsavailable<15 && roomsavailable>0){ 
			RoomsLabel.setForeground(Color.RED);
			System.out.println(roomsavailable + " roomsavailable");
			lblNumberOfRooms.setForeground(Color.RED);
			setBorder(new TitledBorder(null, "Only a few rooms left", TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));
		} 
		else{
			RoomsLabel.setForeground(Color.BLUE);
			lblNumberOfRooms.setForeground(Color.BLUE);
			
		}

		
		if(roomsavailable<=0){
			button.setEnabled(false);
			lblNumberOfRooms.setText("Sorry, we're fully booked");
			lblNumberOfRooms.setBackground(Color.RED);
			lblNumberOfRooms.setForeground(Color.RED);
			RoomsLabel.setText("");
		}
		else if(roomsavailable<numberofrooms){
			lblNumberOfRooms.setBounds(429,123,300,14);
			lblNumberOfRooms.setText("Sorry, we only have "+ roomsavailable+" rooms available");
			RoomsLabel.setText("");
			button.setEnabled(false);
		} 
	}
}
