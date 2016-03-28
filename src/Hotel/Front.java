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

import javax.swing.AbstractButton;
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

public class Front extends JFrame {

	/**
	 * 
	 */
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
	static JButton btnNewButton_1;
	
	final JDateChooser DateChooserIn;
	final JDateChooser DateChooserOut;
	static int whatpage = 1;
	private AbstractButton btnNewButton;



	/**
	 * Create the frame.
	 */
	public Front() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 891, 530);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		btnNewButton = new JButton("Back");
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				whatpage -= 1;
				btnNewButton_1.setEnabled(true);
				System.out.println(whatpage);
				if (whatpage == 1)
					btnNewButton.setEnabled(false);
				if(whatpage!=3){
					btnNewButton_1.setEnabled(true);
				}
				String s = Integer.toString(whatpage);
				cardLayout.show(contentPane, s);
			}
		});
		menuBar.add(btnNewButton);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(cardLayout);
		Icon warnIcon = new ImageIcon("Myndir/backward.png");

		btnNewButton_1 = new JButton("Forward");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				whatpage += 1;
				System.out.println(whatpage);
				if (whatpage != 1)
					btnNewButton.setEnabled(true);
				if(whatpage==3){
					btnNewButton_1.setEnabled(false);
				}
				String s = Integer.toString(whatpage);
				cardLayout.show(contentPane, s);
			}
		});
		menuBar.add(btnNewButton_1);

		JPanel MainPanel = new JPanel();
		MainPanel.setBackground(Color.ORANGE);
		contentPane.add(MainPanel, "1");
		GridBagLayout gbl_MainPanel = new GridBagLayout();
		gbl_MainPanel.columnWidths = new int[]{195, 72, 103, 227, 46, 147, 46, 0};
		gbl_MainPanel.rowHeights = new int[]{14, 151, 47, 23, 20, 14, 20, 0};
		gbl_MainPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_MainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		MainPanel.setLayout(gbl_MainPanel);
				
						JLabel lblNewLabel_2 = new JLabel("Log In");
						lblNewLabel_2.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent arg0) {
								LogIn log = new LogIn();
								log.setVisible(true);
							}
						});
						lblNewLabel_2.setForeground(SystemColor.textHighlight);
						GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
						gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
						gbc_lblNewLabel_2.fill = GridBagConstraints.VERTICAL;
						gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
						gbc_lblNewLabel_2.gridx = 6;
						gbc_lblNewLabel_2.gridy = 0;
						MainPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
				
						SearchTextArea = new JTextField();
						SearchTextArea.setFont(new Font("Tahoma", Font.ITALIC, 14));
						SearchTextArea.setText("t.d. land, sta\u00F0ur, h\u00F3tel...");
						
								SearchTextArea.addKeyListener(new KeyAdapter() {
									@Override
									public void keyPressed(KeyEvent arg0) {
										if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
											search();
											whatpage = 2;
										}
									}
								});
								
										SearchTextArea.addMouseListener(new MouseAdapter() {
											@Override
											public void mousePressed(MouseEvent arg0) {
												SearchTextArea.setText("");
												SearchTextArea.setFont(new Font("Tahoma", Font.BOLD, 14));
												somethingWritten = true;
												word = "";
								
											}
										});
										
												GridBagConstraints gbc_SearchTextArea = new GridBagConstraints();
												gbc_SearchTextArea.fill = GridBagConstraints.BOTH;
												gbc_SearchTextArea.insets = new Insets(0, 0, 5, 5);
												gbc_SearchTextArea.gridwidth = 3;
												gbc_SearchTextArea.gridx = 1;
												gbc_SearchTextArea.gridy = 2;
												MainPanel.add(SearchTextArea, gbc_SearchTextArea);
												SearchTextArea.setColumns(10);
												
														SearchTextArea.setFocusable(true);
						
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
						
								JLabel lblNewLabel = new JLabel("Check in date");
								GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
								gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
								gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
								gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
								gbc_lblNewLabel.gridwidth = 2;
								gbc_lblNewLabel.gridx = 1;
								gbc_lblNewLabel.gridy = 3;
								MainPanel.add(lblNewLabel, gbc_lblNewLabel);
				
						JLabel lbltritunardagur = new JLabel("Check out date");
						GridBagConstraints gbc_lbltritunardagur = new GridBagConstraints();
						gbc_lbltritunardagur.anchor = GridBagConstraints.WEST;
						gbc_lbltritunardagur.fill = GridBagConstraints.VERTICAL;
						gbc_lbltritunardagur.insets = new Insets(0, 0, 5, 5);
						gbc_lbltritunardagur.gridx = 3;
						gbc_lbltritunardagur.gridy = 3;
						MainPanel.add(lbltritunardagur, gbc_lbltritunardagur);
				
						DateChooserOut = new JDateChooser();
						DateChooserOut.addPropertyChangeListener(new PropertyChangeListener() {
							//private Date dateout;

							public void propertyChange(PropertyChangeEvent evt) {
								dateout = DateChooserOut.getDate();
								dateOutString = convertStringToDate(dateout);

							}
						});
						
								DateChooserIn = new JDateChooser();
								DateChooserIn.getJCalendar().setMinSelectableDate(new Date());
								DateChooserIn.setDate(new Date());
								DateChooserIn.addPropertyChangeListener(new PropertyChangeListener() {
									//private Date datein;

									public void propertyChange(PropertyChangeEvent arg0) {

										datein = DateChooserIn.getDate();
										dateInString = convertStringToDate(datein);
										Date dateplus1 = Methods.addDays(datein, 1);
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
						GridBagConstraints gbc_DateChooserOut = new GridBagConstraints();
						gbc_DateChooserOut.fill = GridBagConstraints.BOTH;
						gbc_DateChooserOut.insets = new Insets(0, 0, 5, 5);
						gbc_DateChooserOut.gridx = 3;
						gbc_DateChooserOut.gridy = 4;
						MainPanel.add(DateChooserOut, gbc_DateChooserOut);
				
						JLabel lblNewLabel_1 = new JLabel("Guests");
						GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
						gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
						gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
						gbc_lblNewLabel_1.gridx = 1;
						gbc_lblNewLabel_1.gridy = 5;
						MainPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
				JLabel lblBrn = new JLabel("Number of rooms");
				GridBagConstraints gbc_lblBrn = new GridBagConstraints();
				gbc_lblBrn.fill = GridBagConstraints.BOTH;
				gbc_lblBrn.insets = new Insets(0, 0, 5, 5);
				gbc_lblBrn.gridx = 2;
				gbc_lblBrn.gridy = 5;
				MainPanel.add(lblBrn, gbc_lblBrn);
		cardLayout.show(contentPane, "1");
		final JSpinner spinner_5 = new JSpinner(new SpinnerNumberModel(1, 1,
				30, 1));
		spinner_5.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int val = (int) spinner_5.getValue();
				nrRooms = val;
			}
		});
		GridBagConstraints gbc_spinner_5 = new GridBagConstraints();
		gbc_spinner_5.anchor = GridBagConstraints.WEST;
		gbc_spinner_5.fill = GridBagConstraints.VERTICAL;
		gbc_spinner_5.insets = new Insets(0, 0, 0, 5);
		gbc_spinner_5.gridx = 2;
		gbc_spinner_5.gridy = 6;
		MainPanel.add(spinner_5, gbc_spinner_5);
MainPanel.requestFocusInWindow();
				final JSpinner spinner_4 = new JSpinner(new SpinnerNumberModel(1, 1,
						30, 1));
				spinner_4.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent arg0) {
						int rooms = (int) spinner_5.getValue();
						int s = (int) spinner_4.getValue();
						if(s<nrGuests){
							if (s % 3 == 0) {
								--rooms;
								spinner_5.setValue(rooms);
								nrRooms = rooms;
								spinner_5.setModel(new SpinnerNumberModel(rooms, rooms,
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
							spinner_5.setValue(rooms);
							nrRooms = rooms;
							spinner_5.setModel(new SpinnerNumberModel(rooms, rooms,
									30, 1));
						}
						}
						// System.out.println(s);
					}
				});
				GridBagConstraints gbc_spinner_4 = new GridBagConstraints();
				gbc_spinner_4.anchor = GridBagConstraints.WEST;
				gbc_spinner_4.fill = GridBagConstraints.VERTICAL;
				gbc_spinner_4.insets = new Insets(0, 0, 0, 5);
				gbc_spinner_4.gridx = 1;
				gbc_spinner_4.gridy = 6;
				MainPanel.add(spinner_4, gbc_spinner_4);		

		MainPanel.setFocusable(true);
		
				
	}

	public void search() {
		btnNewButton.setEnabled(true);
		connection = sqliteConnection.dbConnector();
		String s = SearchTextArea.getText();
		sqlWorkBench.LeitaHotel(s);
		if (resultHotel.size() == 0) {
			JOptionPane.showMessageDialog(null,
					"We're sorry, there were no results");
			return;
		}
		//scrollPane = new JScrollPane();
		BookingInfo bookinginfo = new BookingInfo(datein, dateout,
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
		 * like this: DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
		 */
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
