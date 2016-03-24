package Hotel;


import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.util.*;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JCheckBox;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.BorderLayout;

import javax.swing.JComboBox;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

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
	    static JScrollPane j; 
		static int labelTextid;
	    static int labelTextpostcode;
	    static int wifiteljari;
	    static int freewifiteljari;
	    static int numberOfLabels1;
	    static BookingInfo bookinginfo;
	    static JPanel panel_1;
	    static JPanel panel;
	    
	    int numberOfLabels = Front.resultHotel.size();
	    int count = 0;
	    static ArrayList<Hotel> newList;
	    ArrayList<Hotel> hotelList;
	
	/**
	 * Create the panel.
	 */
	public ResultPanel(final ArrayList<Hotel> hotelList1, BookingInfo bookinginfo1) {
		//setMinimumSize(new Dimension(1000, 50000));
		numberOfLabels1 = Front.resultHotel.size();
		System.out.println(numberOfLabels1 + " STÆRÐIN MAÐUR");
		//setSize(new Dimension(835, 700));
		bookinginfo = bookinginfo1;
		newList = hotelList1;
		hotelList=hotelList1;
		final int[] details = {0,0,0,0,0,0};
		setLayout(new BorderLayout(0, 0));
		
		
		
		panel_1 = new JPanel();
		panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_1.setBackground(Color.WHITE);
		panel_1.setPreferredSize(new Dimension(1000,(hotelList1.size()*240)));
		add(panel_1);
		panel_1.setLayout(new GridLayout(numberOfLabels1, 1, 100, 0));
		System.out.println("SIBBIIIIIIII" + numberOfLabels1);
		j = new JScrollPane(panel_1);
		j.setBounds(0, 0, Front.width, Front.height);
		j.getVerticalScrollBar().setUnitIncrement(16);
		
		add(j);
	
		
		final JPanel panel_2 = new JPanel();
		panel_2.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_2.setMaximumSize(new Dimension(120, 32767));
		panel_2.setBackground(Color.DARK_GRAY);
		add(panel_2, BorderLayout.WEST);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{115, 0};
		gbl_panel_2.rowHeights = new int[] {15, 15, 15, 15, 15, 15, 50, 50, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		final JCheckBox chckbxNewCheckBox = new JCheckBox("Wifi");
		chckbxNewCheckBox.setBackground(Color.DARK_GRAY);
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
		chckbxFreeWifi.setBackground(Color.DARK_GRAY);
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
		chckbxSwimmingPool.setBackground(Color.DARK_GRAY);
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
		chckbxGym.setBackground(Color.DARK_GRAY);
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
		chckbxTv.setBackground(Color.DARK_GRAY);
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
		chckbxSmokingArea.setBackground(Color.DARK_GRAY);
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
		gbc_comboBox.anchor = GridBagConstraints.NORTH;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 7;
		panel_2.add(comboBox, gbc_comboBox);
		//int numberOfLabels = Front.resultHotel.size();

		updateUIid(hotelList);
		setVisible(true);
		
	}
	

	
	public static void updateUIid(ArrayList<Hotel> hotelListtmp){
		int numberOfLabels = hotelListtmp.size();

		for (int index=0; index<numberOfLabels; index++) {
			Hotel Hotel = hotelListtmp.get(index);
			HotelResult resultpane = new HotelResult(Hotel, bookinginfo);
			resultpane.setBounds(40, 50+((index)*240), 630, 230);
			panel_1.add(resultpane);
			
		}
	}
}
