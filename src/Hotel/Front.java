package Hotel;

//change2
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JDateChooser;

import javax.swing.JMenuBar;
import javax.swing.JCheckBoxMenuItem;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;


/*
 * �g var a� reyna a� skipuleggja �etta � eftirfarandi h�tt:
 *		
 *		//nafn
 *
 *		//-------------------------------------------------------------------------------------------------
 * 
 * svona^^ g�ja, �a� sem er � milli er likt og nafni� segir
 * 
 * Eitthva� var sem �g veit ekki hva� gerir, merkt me� "dno" endilega 
 * setja �a� inn � sama h�tt og �g hef gert svo virkni s� auglj�s
 * 
 */

public class Front extends JFrame {

	
	private static final long serialVersionUID = 1L;
	public static Connection connection = null;
	public static ArrayList<Hotel> resultHotel = new ArrayList<Hotel>();
	private JTextField SearchTextArea;
	private String dateInString;
	private String dateOutString;
	private int nrRooms = 1;
	private int nrGuests = 1;
	private Date dateout;
	private Date datein;
	
	static Dimension screenSize;
	static JScrollPane scrollPane;
	static ResultPanel resultpanel;
	static Front frame;
	static JPanel contentPane;
	static CardLayout cardLayout = new CardLayout();	
	static JComboBox<Object> SearchSuggestion;
	static String word = "";
	static int height;
	static int width;
	static boolean somethingWritten = false;
	
	Date innritundags = null;
	static JButton forwardTakki;
	
	final JDateChooser DateChooserIn;
	final JDateChooser DateChooserOut;
	static int whatpage = 1;
	static JButton backTakki;



	/**
	 * Create the frame.
	 */
	public Front() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 891, 530);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		
		
//Back Takkinn
		
		backTakki = new JButton("Back");
		backTakki.setEnabled(false);
		backTakki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				whatpage -= 1;
				forwardTakki.setEnabled(true);
				System.out.println(whatpage);					
				if (whatpage == 1)
					backTakki.setEnabled(false);					//H�gt a� setja �etta � fall?
				if(whatpage!=3){
					forwardTakki.setEnabled(true);
				}
				String s = Integer.toString(whatpage);
				cardLayout.show(contentPane, s);
			}
		});
		menuBar.add(backTakki);
//-------------------------------------------------------------------------------------------------
		
		
		
		
		//dno
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(cardLayout);
		Icon warnIcon = new ImageIcon("Myndir/backward.png");

		
		
		
//Forward takkinn
		
		forwardTakki = new JButton("Forward");
		forwardTakki.setEnabled(false);
		forwardTakki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				whatpage += 1;
				System.out.println(whatpage);
				if (whatpage != 1)
					backTakki.setEnabled(true);							//H�gt a� setja �etta � fall?
				if(whatpage==3){
					forwardTakki.setEnabled(false);
				}
				String s = Integer.toString(whatpage);
				cardLayout.show(contentPane, s);
			}
		});
		menuBar.add(forwardTakki);
//-------------------------------------------------------------------------------------------------
		
		
		
		//dno
		JPanel MainPanel = new JPanel();
		MainPanel.setBackground(Color.ORANGE);
		contentPane.add(MainPanel, "1");
		GridBagLayout gbl_MainPanel = new GridBagLayout();
		gbl_MainPanel.columnWidths = new int[]{195, 72, 103, 227, 46, 147, 46, 0};
		gbl_MainPanel.rowHeights = new int[]{14, 151, 47, 23, 20, 14, 20, 0};
		gbl_MainPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_MainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		MainPanel.setLayout(gbl_MainPanel);
				
		
		
		
// Log-in hnappur
		
		JLabel logIn_Label = new JLabel("Log In");
		logIn_Label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				LogIn log = new LogIn();
				log.setVisible(true);
			}
		});
	
		logIn_Label.setForeground(SystemColor.textHighlight);
		GridBagConstraints gbc_logIn_Label = new GridBagConstraints();
		gbc_logIn_Label.anchor = GridBagConstraints.EAST;
		gbc_logIn_Label.fill = GridBagConstraints.VERTICAL;
		gbc_logIn_Label.insets = new Insets(0, 0, 5, 0);
		gbc_logIn_Label.gridx = 6;
		gbc_logIn_Label.gridy = 0;
		MainPanel.add(logIn_Label, gbc_logIn_Label);
//-------------------------------------------------------------------------------------------------
						
		
		
//Leitarsv��i� sj�lft �ar sem notandi sl�r in texta
				SearchTextArea = new JTextField();
				SearchTextArea.setFont(new Font("Tahoma", Font.ITALIC, 14));
				SearchTextArea.setText("t.d. land, sta\u00F0ur, h\u00F3tel...");
				
				SearchTextArea.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent arg0) {
						if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {	// eigum vi� a� hafa �etta � ��rum klasa?
							search();
							whatpage = 2;
						}
					}
				});
				
				SearchTextArea.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent arg0) {
						SearchTextArea.setText("");
						SearchTextArea.setFont(new Font("Tahoma", Font.BOLD, 14));	// og �etta �� � ��rum klasa?
						somethingWritten = true;	
						word = "";
		
					}
				});
						
								
								
										//dno?
										GridBagConstraints gbc_SearchTextArea = new GridBagConstraints();
										gbc_SearchTextArea.fill = GridBagConstraints.BOTH;
										gbc_SearchTextArea.insets = new Insets(0, 0, 5, 5);
										gbc_SearchTextArea.gridwidth = 3;
										gbc_SearchTextArea.gridx = 1;
										gbc_SearchTextArea.gridy = 2;
										MainPanel.add(SearchTextArea, gbc_SearchTextArea);
										SearchTextArea.setColumns(10);
										
												SearchTextArea.setFocusable(true);
												
//-------------------------------------------------------------------------------------------------												
														
														
						
//"LEITA" takki, takkinn sem �tir � og �� leitast
														
		final JButton ExecuteSearch = new JButton("");
		ExecuteSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ExecuteSearch.setBorder(new BevelBorder(BevelBorder.LOWERED,
						null, null, null, null));
				// sqlWorkBench.insertIntoFacilities();
			}
		});
		ExecuteSearch.setBorder(new BevelBorder(BevelBorder.RAISED, null, null,
				null, null));
		ExecuteSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ExecuteSearch.setBorder(new BevelBorder(BevelBorder.RAISED,
						null, null, null, null));
				
				search();
				whatpage = 2;
				setVisible(true);
				
			}
		});
		
				ExecuteSearch.setIcon(new ImageIcon("src/Myndir/Search.png"));
				GridBagConstraints gbc_ExecuteSearch = new GridBagConstraints();
				gbc_ExecuteSearch.fill = GridBagConstraints.BOTH;
				gbc_ExecuteSearch.insets = new Insets(0, 0, 5, 5);
				gbc_ExecuteSearch.gridx = 4;
				gbc_ExecuteSearch.gridy = 2;
				MainPanel.add(ExecuteSearch, gbc_ExecuteSearch);										
//-------------------------------------------------------------------------------------------------
						
										
										
// Static TextaDisplay - "Check in Date"
		JLabel checkInDate = new JLabel("Check in date");
		GridBagConstraints gbc_checkInDate = new GridBagConstraints();
		gbc_checkInDate.anchor = GridBagConstraints.WEST;
		gbc_checkInDate.fill = GridBagConstraints.VERTICAL;
		gbc_checkInDate.insets = new Insets(0, 0, 5, 5);
		gbc_checkInDate.gridwidth = 2;
		gbc_checkInDate.gridx = 1;
		gbc_checkInDate.gridy = 3;
		MainPanel.add(checkInDate, gbc_checkInDate);
//-------------------------------------------------------------------------------------------------
		
		
// Static TextaDisplay - "Check out"	
		JLabel checkOutDate = new JLabel("Check out date");
		GridBagConstraints gbc_checkOutDate = new GridBagConstraints();
		gbc_checkOutDate.anchor = GridBagConstraints.WEST;
		gbc_checkOutDate.fill = GridBagConstraints.VERTICAL;
		gbc_checkOutDate.insets = new Insets(0, 0, 5, 5);
		gbc_checkOutDate.gridx = 3;
		gbc_checkOutDate.gridy = 3;
		MainPanel.add(checkOutDate, gbc_checkOutDate);
//-------------------------------------------------------------------------------------------------
				
	
			
		
//Dagsetningar sem m�tir � h�teli�, skr�ir �ig inn.
		DateChooserIn = new JDateChooser();
		DateChooserIn.getJCalendar().setMinSelectableDate(new Date());
		DateChooserIn.setDate(new Date());
		DateChooserIn.addPropertyChangeListener(new PropertyChangeListener() {
			//private Date datein;

			public void propertyChange(PropertyChangeEvent arg0) {

				datein = DateChooserIn.getDate();
				dateInString = convertStringToDate(datein);
				Date dateplus1 = Methods.addDays(datein, 1);								//Setja �etta � fall � another klasi?
				DateChooserOut.setDate(dateplus1);
				DateChooserOut.getJCalendar().setMinSelectableDate(dateplus1);
			}
		});
								
								
								
				GridBagConstraints gbc_DateChooserIn = new GridBagConstraints();
				gbc_DateChooserIn.fill = GridBagConstraints.BOTH;
				gbc_DateChooserIn.insets = new Insets(0, 0, 5, 5);
				gbc_DateChooserIn.gridwidth = 2;
				gbc_DateChooserIn.gridx = 1;
				gbc_DateChooserIn.gridy = 4;
				MainPanel.add(DateChooserIn, gbc_DateChooserIn);
				DateChooserIn.getJCalendar().setMinSelectableDate(new Date());
//-------------------------------------------------------------------------------------------------	
				
				
				
				
				
		
//Dagsetningar sem fer� af h�telinu, skr�ir �ig �t.
		DateChooserOut = new JDateChooser();
		DateChooserOut.addPropertyChangeListener(new PropertyChangeListener() {
			//private Date dateout;
		
			public void propertyChange(PropertyChangeEvent evt) {
				dateout = DateChooserOut.getDate();
				dateOutString = convertStringToDate(dateout);
		
			}
		});
		
		GridBagConstraints gbc_DateChooserOut = new GridBagConstraints();
		gbc_DateChooserOut.fill = GridBagConstraints.BOTH;
		gbc_DateChooserOut.insets = new Insets(0, 0, 5, 5);
		gbc_DateChooserOut.gridx = 3;
		gbc_DateChooserOut.gridy = 4;
		MainPanel.add(DateChooserOut, gbc_DateChooserOut);
//-------------------------------------------------------------------------------------------------	
		
		
		
		
	
						
// Static TextaDisplay - "Guests"
		JLabel guest_Label = new JLabel("Guests");
		GridBagConstraints gbc_guest_Label = new GridBagConstraints();
			gbc_guest_Label.fill = GridBagConstraints.BOTH;
			gbc_guest_Label.insets = new Insets(0, 0, 5, 5);
			gbc_guest_Label.gridx = 1;
			gbc_guest_Label.gridy = 5;
		MainPanel.add(guest_Label, gbc_guest_Label);
//-------------------------------------------------------------------------------------------------
		
		
		
// Static TextaDisplay - "Number of rooms"
		JLabel lblBrn = new JLabel("Number of rooms");
		GridBagConstraints gbc_lblBrn = new GridBagConstraints();
			gbc_lblBrn.fill = GridBagConstraints.BOTH;
			gbc_lblBrn.insets = new Insets(0, 0, 5, 5);
			gbc_lblBrn.gridx = 2;
			gbc_lblBrn.gridy = 5;
		MainPanel.add(lblBrn, gbc_lblBrn);
//-------------------------------------------------------------------------------------------------		
				
				
		
		
		
		
				
//"Teljarinn/Spinnerinn" �ar sem notandi velur fj�da herbergja	
		cardLayout.show(contentPane, "1");
		final JSpinner howManyRoomsSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 30, 1));
		
		howManyRoomsSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int val = (int) howManyRoomsSpinner.getValue();
				nrRooms = val;
			}
		});
		GridBagConstraints gbc_howManyRoomsSpinner = new GridBagConstraints();
		gbc_howManyRoomsSpinner.anchor = GridBagConstraints.WEST;
		gbc_howManyRoomsSpinner.fill = GridBagConstraints.VERTICAL;
		gbc_howManyRoomsSpinner.insets = new Insets(0, 0, 0, 5);
		gbc_howManyRoomsSpinner.gridx = 2;
		gbc_howManyRoomsSpinner.gridy = 6;
		MainPanel.add(howManyRoomsSpinner, gbc_howManyRoomsSpinner);
//-------------------------------------------------------------------------------------------------				
		
		
		
		
		
		//dno?
		MainPanel.requestFocusInWindow();



//"Teljarinn/Spinnerinn" �ar sem notandi velur fj�lda gesta.
				final JSpinner howManyGuestsSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 30, 1));
				
				howManyGuestsSpinner.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent arg0) {
						int rooms = (int) howManyRoomsSpinner.getValue();
						int s = (int) howManyGuestsSpinner.getValue();
						if(s<nrGuests){
							if (s % 3 == 0) {
								--rooms;
								howManyRoomsSpinner.setValue(rooms);
								nrRooms = rooms;
								howManyRoomsSpinner.setModel(new SpinnerNumberModel(rooms, rooms,		//henda �essu �llu � fall?
										30, 1));
							}
							nrGuests = s;
							nrRooms = rooms;
						}
						else{
						nrGuests = s;
						nrRooms = rooms;
						if (s % 3 == 1 && s > 3 && (s/3) >= rooms) {
							++rooms;
							howManyRoomsSpinner.setValue(rooms);
							nrRooms = rooms;
							howManyRoomsSpinner.setModel(new SpinnerNumberModel(rooms, rooms,
									30, 1));
						}
						}
					}
				});
				GridBagConstraints gbc_howManyGuestsSpinner = new GridBagConstraints();
				gbc_howManyGuestsSpinner.anchor = GridBagConstraints.WEST;
				gbc_howManyGuestsSpinner.fill = GridBagConstraints.VERTICAL;
				gbc_howManyGuestsSpinner.insets = new Insets(0, 0, 0, 5);
				gbc_howManyGuestsSpinner.gridx = 1;
				gbc_howManyGuestsSpinner.gridy = 6;
				MainPanel.add(howManyGuestsSpinner, gbc_howManyGuestsSpinner);		

		MainPanel.setFocusable(true);
	}
	
	

	public void search() {
		backTakki.setEnabled(true);
		connection = sqliteConnection.dbConnector();
		String s = SearchTextArea.getText();
		sqlWorkBench.LeitaHotel(s);
		if (resultHotel.size() == 0) {
			JOptionPane.showMessageDialog(null,
					"We're sorry, there were no results");
			return;
		}
						//scrollPane = new JScrollPane();
		BookingInfo bookinginfo = new BookingInfo(datein, dateout,						//Henda �essu �llu � fall?
				dateInString, dateOutString, nrGuests, nrRooms);
		resultpanel = new ResultPanel(resultHotel, bookinginfo);
		frame.setLocationRelativeTo(null);
						//scrollPane.setViewportView(resultpanel);
						//scrollPane.getVerticalScrollBar().setUnitIncrement(16);
						//getContentPane().add(scrollPane);
						//scrollPane.getVerticalScrollBar().setLocation(0, 0);
						//contentPane.add(scrollPane, "2");
		contentPane.add(resultpanel,"2");
		cardLayout.show(contentPane, "2");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	
	
	

	public static String convertStringToDate(Date indate) {
		String dateString = null;
		SimpleDateFormat sdfr = new SimpleDateFormat("dd MMM yyyy");
			/*
			 * you can also use DateFormat reference instead of SimpleDateFormat
			 * like this: DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");			//�etta �� fall � honum klasanum og kalla � �etta her?
			 */																			// � main fallinu ��?
			try {
			dateString = sdfr.format(indate);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return dateString;
	}
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					frame = new Front();
					frame.setVisible(true);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					height = screenSize.height;
					width = screenSize.width;
					//frame.setLocationRelativeTo(null);
					Date datein = new Date();
					datein.getTime();
					//String dates = convertStringToDate(datein);
					//sqlWorkBench.updateRoomBookings(dates);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
