package Hotel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.Desktop;
import java.awt.Font;
import java.net.URL;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.Box;


public class HotelResult extends JPanel {
	private Date DateIn;
    private Date DateOut;
    private String DateInS;
    private String DateOutS;
    private BookingInfo bookinginfo1;
	/**
	 * Create the panel.
	 */
	public HotelResult(final Hotel hotel,  BookingInfo bookinginfo) {
		setSize(new Dimension(601, 230));
		setMinimumSize(new Dimension(630, 230));
		setMaximumSize(new Dimension(630, 230));
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		String name = hotel.getName();
		String city = hotel.getCity();
		String url = hotel.getURL();
		int wifi = hotel.getWifi();
		int FreeWifi = hotel.getFreeWifi();
		int smoke = hotel.getSmoke();
		int swim = hotel.getPool();
		int gym = hotel.getGym();
		int tv = hotel.getTV();
		int postcode = hotel.getPostcode();
		DateIn = bookinginfo.getDateIn();
		DateOut = bookinginfo.getDateout();
		DateInS = bookinginfo.getDateInString();
		DateOutS = bookinginfo.getDateOutString();
		bookinginfo1 = bookinginfo;
		
		int[] price = hotel.getPrice();
		setLayout(null);
		setBounds(0,0,630,230);
		setSize(630,230);
		
		JLabel lblNewLabel = new JLabel("");
		int tmp = hotel.getID();
		int tmp3 = (tmp%10)+1;
		String tmp2= ""+tmp3;
		
		lblNewLabel.setIcon(new ImageIcon("src/Myndir/"+tmp2+".jpg"));
		lblNewLabel.setBounds(10, 11, 259, 174);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(name);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_1.setBounds(279, 11, 470, 41);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(city);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_2.setBounds(279, 63, 79, 29);
		add(lblNewLabel_2);
		
		JButton button = new JButton("Choose room");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BookingConfirmation book = new BookingConfirmation("Do you have any requests?", bookinginfo1, hotel);
				Front.contentPane.add(book, "3");
				Front.cardLayout.show(Front.contentPane, "3");
			}
		});
		button.setBounds(480, 178, 142, 41);
		add(button);
		
		JLabel lblPrice = new JLabel(price[2]+"");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrice.setForeground(new Color(255, 0, 0));
		lblPrice.setBounds(576, 153, 46, 14);
		add(lblPrice);
		
		JLabel lblPricesFrom = new JLabel("Prices from");
		lblPricesFrom.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPricesFrom.setForeground(Color.RED);
		lblPricesFrom.setBounds(461, 153, 105, 14);
		add(lblPricesFrom);
		
		JLabel lblNewLabel_4 = new JLabel(postcode +"");
		lblNewLabel_4.setBounds(368, 63, 46, 29);
		add(lblNewLabel_4);
		
		String info = info(wifi, FreeWifi, smoke, swim, gym,tv);
		//System.out.println(info+ " info");
		JTextArea lblNewLabel_5 = new JTextArea(info);
		lblNewLabel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Facilities", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 255, 0)));
		lblNewLabel_5.setBackground(SystemColor.control);
		lblNewLabel_5.setEditable(false);
		lblNewLabel_5.setBounds(279, 103, 135, 116);
		add(lblNewLabel_5);
		
		/*String[] words = {"Wifi", "FreeWifi","Smoking area","Swimming pool","Gym","TV"};
		for(int i =0; i<price.length;i++){
			if(price[i]==1){
				JLabel lblNewLabel_3 = new JLabel(words[i]);
				lblNewLabel_3.setBounds(279, 103+(24*i), 46, 14);
				add(lblNewLabel_3);
			}
		}*/
		
				
	}
	

	private String info(int a, int b, int c, int d, int e, int f){
		String string="";
		if(a==1) string+="Wifi \n";
		if(a==1) string+="Free Wifi \n";
		if(c==1) string+="Smoking Area \n";
		if(d==1) string+="Swimming pool \n";
		if(e==1) string+="Gym \n";
		if(f==1) string+="TV";
		//System.out.println(string + " strengur");
		return string;
	}
	
	private void internetURL(String urlid) {
		String tmp = urlid.substring(0,urlid.length());
		
		
		try {
		    Desktop.getDesktop().browse(new URL("http://"+tmp).toURI());
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
