package Hotel;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpinnerNumberModel;
import java.util.*;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
public class ResultPanel extends JPanel {
	 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		static String labelTextaddress;
	    static String labelTextname;
	    static String labelTextcity;
	    static String url;
	    static String price;
	    static JScrollPane scrollpane; 
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
	    final JDateChooser dateChooser_1;
	    int numberOfLabels = Front.resultHotel.size();
	    int count = 0;
	    static ArrayList<Hotel> newList;
	    ArrayList<Hotel> hotelList;
	
	/**
	 * Create the panel.
	 */
	public ResultPanel(final ArrayList<Hotel> hotelList1, final BookingInfo bookinginfo) {
		//setMinimumSize(new Dimension(1000, 50000));
		numberOfLabels1 = Front.resultHotel.size();
		this.bookinginfo = bookinginfo;
		newList = hotelList1;
		hotelList=hotelList1;
		final int[] details = {0,0,0,0,0,0};
		setLayout(new BorderLayout(0, 0));
		this.datein = bookinginfo.getDateIn();
		this.dateout = bookinginfo.getDateout();
		
		
		panel_1 = new JPanel();
		panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_1.setBackground(Color.WHITE);
		panel_1.setPreferredSize(new Dimension(1000,(hotelList1.size()*240)));
		add(panel_1);
		panel_1.setLayout(new GridLayout(numberOfLabels1, 1, 100, 0));
		
		scrollpane = new JScrollPane(panel_1);
		scrollpane.setBounds(0, 0, Front.width, Front.height);
		scrollpane.getVerticalScrollBar().setUnitIncrement(16);
		
		add(scrollpane);
	
		
		final JPanel panel_2 = new JPanel();
		panel_2.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_2.setMaximumSize(new Dimension(150, 32767));
		panel_2.setBackground(Color.ORANGE);
		add(panel_2, BorderLayout.WEST);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{150, 0};
		gbl_panel_2.rowHeights = new int[] {15, 15, 15, 15, 15, 15, 20, 20, 20,20,20,20,20,20,20,20, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0};
		panel_2.setLayout(gbl_panel_2);
		
		
		
		final JCheckBox chckbxNewCheckBox = new JCheckBox("Wifi");
		chckbxNewCheckBox.setBackground(Color.ORANGE);
		chckbxNewCheckBox.setBounds(10, 10, 43, 23);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				
				if(chckbxNewCheckBox.isSelected()){
					
					newList = sqlWorkBench.detailedSearch(newList,0);
					panel_1.removeAll();
					panel_1.setPreferredSize(new Dimension(1000,(newList.size()*240)));
					panel_1.setLayout(new GridLayout(newList.size(),1,100,0));
					details[0]=1;
					updateUIid(newList);
					repaint();
					updateUI();
					
					
				}else{
					panel_1.removeAll();
					newList=hotelList1;
					details[0]=0;
					for(int i=0; i<details.length;i++){
						if(details[i]==1){ 
							newList=sqlWorkBench.detailedSearch(newList, i);
							
						}
					}
					updateUIid(newList);
					panel_1.setPreferredSize(new Dimension(1000,(newList.size()*240)));
					panel_1.setLayout(new GridLayout(newList.size(),1,100,0));
					repaint();
					updateUI();
				}
				
					
			}
		});
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.fill = GridBagConstraints.BOTH;
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxNewCheckBox.gridx = 0;
		gbc_chckbxNewCheckBox.gridy = 0;
		panel_2.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		
		final JCheckBox chckbxFreeWifi = new JCheckBox("Free Wifi");
		chckbxFreeWifi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxFreeWifi.isSelected()){
					
					newList = sqlWorkBench.detailedSearch(newList,1);
					panel_1.removeAll();
					panel_1.setPreferredSize(new Dimension(1000,(newList.size()*240)));
					panel_1.setLayout(new GridLayout(newList.size(),1,100,0));
					details[1]=1;
					updateUIid(newList);
					repaint();
					updateUI();
					
					
				}else{
					panel_1.removeAll();
					newList=hotelList1;
					details[1]=0;
					for(int i=0; i<details.length;i++){
						if(details[i]==1){ 
							newList=sqlWorkBench.detailedSearch(newList, i);
							
						}
					}
					updateUIid(newList);
					panel_1.setPreferredSize(new Dimension(1000,(newList.size()*240)));
					panel_1.setLayout(new GridLayout(newList.size(),1,100,0));
					repaint();
					updateUI();
				}
			}
		});
		chckbxFreeWifi.setBackground(Color.ORANGE);
		GridBagConstraints gbc_chckbxFreeWifi = new GridBagConstraints();
		gbc_chckbxFreeWifi.fill = GridBagConstraints.BOTH;
		gbc_chckbxFreeWifi.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxFreeWifi.gridx = 0;
		gbc_chckbxFreeWifi.gridy = 1;
		panel_2.add(chckbxFreeWifi, gbc_chckbxFreeWifi);
		
		final JCheckBox chckbxSwimmingPool = new JCheckBox("Swimming pool");
		chckbxSwimmingPool.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxSwimmingPool.isSelected()){
					
					newList = sqlWorkBench.detailedSearch(newList,2);
					panel_1.removeAll();
					panel_1.setPreferredSize(new Dimension(1000,(newList.size()*240)));
					panel_1.setLayout(new GridLayout(newList.size(),1,100,0));
					details[2]=1;
					updateUIid(newList);
					repaint();
					updateUI();
					
					
				}else{
					panel_1.removeAll();
					newList=hotelList1;
					details[2]=0;
					for(int i=0; i<details.length;i++){
						if(details[i]==1){ 
							newList=sqlWorkBench.detailedSearch(newList, i);
							
						}
					}
					updateUIid(newList);
					panel_1.setPreferredSize(new Dimension(1000,(newList.size()*240)));
					panel_1.setLayout(new GridLayout(newList.size(),1,100,0));
					repaint();
					updateUI();
				}
			}
		});
		chckbxSwimmingPool.setBackground(Color.ORANGE);
		GridBagConstraints gbc_chckbxSwimmingPool = new GridBagConstraints();
		gbc_chckbxSwimmingPool.fill = GridBagConstraints.BOTH;
		gbc_chckbxSwimmingPool.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxSwimmingPool.gridx = 0;
		gbc_chckbxSwimmingPool.gridy = 2;
		panel_2.add(chckbxSwimmingPool, gbc_chckbxSwimmingPool);
		
		final JCheckBox chckbxGym = new JCheckBox("Gym");
		chckbxGym.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxGym.isSelected()){
					
					newList = sqlWorkBench.detailedSearch(newList,3);
					panel_1.removeAll();
					panel_1.setPreferredSize(new Dimension(1000,(newList.size()*240)));
					panel_1.setLayout(new GridLayout(newList.size(),1,100,0));
					details[3]=1;
					updateUIid(newList);
					repaint();
					updateUI();
					
					
				}else{
					panel_1.removeAll();
					newList=hotelList1;
					details[3]=0;
					for(int i=0; i<details.length;i++){
						if(details[i]==1){ 
							newList=sqlWorkBench.detailedSearch(newList, i);
							
						}
					}
					updateUIid(newList);
					panel_1.setPreferredSize(new Dimension(1000,(newList.size()*240)));
					panel_1.setLayout(new GridLayout(newList.size(),1,100,0));
					repaint();
					updateUI();
				}
			}
		});
		chckbxGym.setBackground(Color.ORANGE);
		GridBagConstraints gbc_chckbxGym = new GridBagConstraints();
		gbc_chckbxGym.fill = GridBagConstraints.BOTH;
		gbc_chckbxGym.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxGym.gridx = 0;
		gbc_chckbxGym.gridy = 3;
		panel_2.add(chckbxGym, gbc_chckbxGym);
		
		final JCheckBox chckbxTv = new JCheckBox("Tv");
		chckbxTv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxTv.isSelected()){
					
					newList = sqlWorkBench.detailedSearch(newList,4);
					panel_1.removeAll();
					panel_1.setPreferredSize(new Dimension(1000,(newList.size()*240)));
					panel_1.setLayout(new GridLayout(newList.size(),1,100,0));
					details[4]=1;
					updateUIid(newList);
					repaint();
					updateUI();
					
					
				}else{
					panel_1.removeAll();
					newList=hotelList1;
					details[4]=0;
					for(int i=0; i<details.length;i++){
						if(details[i]==1){ 
							newList=sqlWorkBench.detailedSearch(newList, i);
							
						}
					}
					updateUIid(newList);
					panel_1.setPreferredSize(new Dimension(1000,(newList.size()*240)));
					panel_1.setLayout(new GridLayout(newList.size(),1,100,0));
					repaint();
					updateUI();
				}
			}
		});
		chckbxTv.setBackground(Color.ORANGE);
		GridBagConstraints gbc_chckbxTv = new GridBagConstraints();
		gbc_chckbxTv.fill = GridBagConstraints.BOTH;
		gbc_chckbxTv.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxTv.gridx = 0;
		gbc_chckbxTv.gridy = 4;
		panel_2.add(chckbxTv, gbc_chckbxTv);
		
		final JCheckBox chckbxSmokingArea = new JCheckBox("Smoking area");
		chckbxSmokingArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxSmokingArea.isSelected()){
					
					newList = sqlWorkBench.detailedSearch(newList,5);
					panel_1.removeAll();
					panel_1.setPreferredSize(new Dimension(1000,(newList.size()*240)));
					panel_1.setLayout(new GridLayout(newList.size(),1,100,0));
					details[5]=1;
					updateUIid(newList);
					repaint();
					updateUI();
					
					
				}else{
					panel_1.removeAll();
					newList=hotelList1;
					details[5]=0;
					for(int i=0; i<details.length;i++){
						if(details[i]==1){ 
							newList=sqlWorkBench.detailedSearch(newList, i);
							
						}
					}
					updateUIid(newList);
					panel_1.setPreferredSize(new Dimension(1000,(newList.size()*240)));
					panel_1.setLayout(new GridLayout(newList.size(),1,100,0));
					repaint();
					updateUI();
				}
			}
		});
		chckbxSmokingArea.setBackground(Color.ORANGE);
		GridBagConstraints gbc_chckbxSmokingArea = new GridBagConstraints();
		gbc_chckbxSmokingArea.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxSmokingArea.fill = GridBagConstraints.BOTH;
		gbc_chckbxSmokingArea.gridx = 0;
		gbc_chckbxSmokingArea.gridy = 5;
		panel_2.add(chckbxSmokingArea, gbc_chckbxSmokingArea);
		
		String labels[] = {"Raða eftir", "A-Z", "Z-A", "Prices from", "Prices to"};
		final JComboBox comboBox = new JComboBox(labels);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				
				Object item = evt.getItem();
				hotelList=newList;
			    if (item.toString().equals("Prices from")) {
			    	panel_1.removeAll();
					Collections.sort(hotelList, new Methods.MyComparator2());
					updateUIid(hotelList);
					repaint();
					updateUI();
			    } else if(item.toString().equals("Prices to")) {
			    	panel_1.removeAll();
					Collections.sort(hotelList, new Methods.MyComparator());
					updateUIid(hotelList);
					repaint();
					updateUI();
			    }
			    else if(item.toString().equals("A-Z")) {
			    	panel_1.removeAll();
					Collections.sort(hotelList, new Methods.MyComparator3());
					updateUIid(hotelList);
					repaint();
					updateUI();
			    }
			    else if(item.toString().equals("Z-A")) {
			    	panel_1.removeAll();
					Collections.sort(hotelList, new Methods.MyComparator4());
					updateUIid(hotelList);
					repaint();
					updateUI();
			    }
			}
		});
		comboBox.setVisible(true);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.anchor = GridBagConstraints.NORTH;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 6;
		panel_2.add(comboBox, gbc_comboBox);
		
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
				dateChooser_1.setDate(dateplus1);
				dateChooser_1.getJCalendar().setMinSelectableDate(dateplus1);
			}
		});
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				dateout = dateChooser_1.getDate();
				dateOutString = Front.convertStringToDate(dateout);
				bookinginfo.setDateOutS(dateOutString);
				bookinginfo.setDateOut(dateout);
			}
		});
		dateChooser_1.setDate(dateout);
		GridBagConstraints gbc_dateChooser_1 = new GridBagConstraints();
		gbc_dateChooser_1.fill = GridBagConstraints.BOTH;
		gbc_dateChooser_1.insets = new Insets(0, 0, 5, 0);
		gbc_dateChooser_1.gridx = 0;
		gbc_dateChooser_1.gridy = 10;
		panel_2.add(dateChooser_1, gbc_dateChooser_1);
		


		
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
		spinner.setValue(bookinginfo.getNumberOfGuests());
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int rooms = (int) spinner_1.getValue();
				int s = (int) spinner.getValue();
				if(s<bookinginfo.getNumberOfGuests()){
					if (s % 3 == 0) {
						--rooms;
						spinner_1.setValue(rooms);
						
						spinner_1.setModel(new SpinnerNumberModel(rooms, rooms,
								30, 1));
					}
					bookinginfo.setNumberOfGuests(s);
					bookinginfo.setNumberOfRooms(rooms);
				}
				else{
				bookinginfo.setNumberOfGuests(s);
				
				if (s % 3 == 1 && s > 3 && (s/3) >= rooms) {
					++rooms;
					spinner_1.setValue(rooms);
					bookinginfo.setNumberOfRooms(rooms);
					spinner_1.setModel(new SpinnerNumberModel(rooms, rooms,
							30, 1));
				}
				}
				panel_1.removeAll();
				updateUIid(newList);
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
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 11;
		panel_2.add(lblNewLabel_1, gbc_lblNewLabel_1);
		

		//int numberOfLabels = Front.resultHotel.size();

		
		updateUIid(hotelList);
		setVisible(true);
		toTop();
	}
	
    private static void toTop(){
    	javax.swing.SwingUtilities.invokeLater(new Runnable() {
			   public void run() { 
			       scrollpane.getVerticalScrollBar().setValue(0);
			   }
			});
    }
	 
	public static void updateUIid(ArrayList<Hotel> hotelListtmp){
		int numberOfLabels = hotelListtmp.size();
		resultList.clear();
		for (int index=0; index<numberOfLabels; index++) {
			Hotel Hotel = hotelListtmp.get(index);
			HotelResult resultpane = new HotelResult(Hotel, bookinginfo);
			resultList.add(resultpane);
			resultpane.setBounds(40, 50+((index)*240), 630, 230);
			panel_1.add(resultpane);
			
		}
		toTop();
	}
}
