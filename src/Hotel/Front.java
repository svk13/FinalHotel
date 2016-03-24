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
	JButton btnNewButton_1;
	
	final JDateChooser DateChooserIn;
	final JDateChooser DateChooserOut;
	int whatpage = 1;
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
				if (whatpage != 1)
					btnNewButton.setEnabled(true);
				String s = Integer.toString(whatpage);
				cardLayout.show(contentPane, s);
			}
		});
		menuBar.add(btnNewButton_1);

		JPanel MainPanel = new JPanel();
		MainPanel.setBackground(Color.BLUE);
		contentPane.add(MainPanel, "1");

		MainPanel.setLayout(null);

		SearchTextArea = new JTextField();
		SearchTextArea.setFont(new Font("Tahoma", Font.ITALIC, 14));
		SearchTextArea.setText("t.d. land, sta\u00F0ur, h\u00F3tel...");
		SearchTextArea.setBounds(195, 176, 419, 47);

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

		MainPanel.add(SearchTextArea);
		SearchTextArea.setColumns(10);
		cardLayout.show(contentPane, "1");

		JLabel lblNewLabel = new JLabel("Check in date");
		lblNewLabel.setBounds(197, 234, 103, 23);
		MainPanel.add(lblNewLabel);

		JLabel lbltritunardagur = new JLabel("Check out date");
		lbltritunardagur.setBounds(394, 234, 103, 23);
		MainPanel.add(lbltritunardagur);

		JLabel lblNewLabel_1 = new JLabel("Guests");
		lblNewLabel_1.setBounds(196, 289, 71, 14);
		MainPanel.add(lblNewLabel_1);

		JLabel lblBrn = new JLabel("Number of rooms");
		lblBrn.setBounds(277, 289, 103, 14);
		MainPanel.add(lblBrn);

		final JSpinner spinner_5 = new JSpinner(new SpinnerNumberModel(1, 1,
				30, 1));
		spinner_5.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int val = (int) spinner_5.getValue();
				nrRooms = val;
			}
		});
		spinner_5.setBounds(277, 304, 71, 20);
		MainPanel.add(spinner_5);

		final JSpinner spinner_4 = new JSpinner(new SpinnerNumberModel(1, 1,
				30, 1));
		spinner_4.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {

				int s = (int) spinner_4.getValue();
				nrGuests = s;
				int rooms = (int) spinner_5.getValue();
				nrRooms = rooms;
				if (s % 3 == 1 && s > 3) {
					++rooms;
					spinner_5.setValue(rooms);
					nrRooms = rooms;
				}
				// System.out.println(s);
			}
		});

		spinner_4.setBounds(196, 304, 71, 20);
		MainPanel.add(spinner_4);

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

		ExecuteSearch.setBounds(616, 176, 46, 47);
		MainPanel.add(ExecuteSearch);

		DateChooserIn = new JDateChooser();
		DateChooserIn.getJCalendar().setMinSelectableDate(new Date());
		DateChooserIn.setDate(new Date());
		DateChooserIn.addPropertyChangeListener(new PropertyChangeListener() {
			private Date datein;

			public void propertyChange(PropertyChangeEvent arg0) {

				datein = DateChooserIn.getDate();
				dateInString = convertStringToDate(datein);
				Date dateplus1 = sqlWorkBench.addDays(datein, 1);
				DateChooserOut.setDate(dateplus1);
				DateChooserOut.getJCalendar().setMinSelectableDate(dateplus1);
			}
		});

		DateChooserIn.setBounds(195, 260, 139, 20);
		MainPanel.add(DateChooserIn);

		DateChooserOut = new JDateChooser();
		DateChooserIn.getJCalendar().setMinSelectableDate(new Date());
		DateChooserOut.addPropertyChangeListener(new PropertyChangeListener() {
			private Date dateout;

			public void propertyChange(PropertyChangeEvent evt) {
				dateout = DateChooserOut.getDate();
				dateOutString = convertStringToDate(dateout);

			}
		});
		DateChooserOut.setBounds(394, 260, 133, 20);
		MainPanel.add(DateChooserOut);

		MainPanel.setFocusable(true);
		MainPanel.requestFocusInWindow();

		SearchTextArea.setFocusable(true);

		JLabel lblNewLabel_2 = new JLabel("Log In");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				LogIn log = new LogIn();
				log.setVisible(true);
			}
		});
		lblNewLabel_2.setForeground(SystemColor.textHighlight);
		lblNewLabel_2.setBounds(809, 11, 46, 14);
		MainPanel.add(lblNewLabel_2);
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
		scrollPane = new JScrollPane();
		BookingInfo bookinginfo = new BookingInfo(datein, dateout,
				dateInString, dateOutString, nrGuests, nrRooms);
		resultpanel = new ResultPanel(resultHotel, bookinginfo);
		frame.setLocationRelativeTo(null);
		scrollPane.setViewportView(resultpanel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		getContentPane().add(scrollPane);
		scrollPane.getVerticalScrollBar().setLocation(0, 0);
		contentPane.add(scrollPane, "2");
		cardLayout.show(contentPane, "2");

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
					frame.setLocationRelativeTo(null);
					Date datein = new Date();
					datein.getTime();
					String dates = convertStringToDate(datein);
					sqlWorkBench.updateRoomBookings(dates);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
