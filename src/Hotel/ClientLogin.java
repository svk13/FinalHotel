package Hotel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JTextArea;

import java.awt.SystemColor;

import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Toolkit;

public class ClientLogin extends JDialog {

	/* 
	 * A JDialog for a client login
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static String id;
	private static String reservationID;
	private static String datein;
	private static String dateout;
	private JTextArea textArea;
	private static String client;
	private static String name;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ClientLogin dialog = new ClientLogin(name, reservationID, datein, dateout, client, id);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static Hotel findHotel(ArrayList<Hotel> newList, int realID){
		Hotel theHotel = null;
		for(int i = 0; i<newList.size();i++){
			System.out.println(newList.get(i).getName());
		if(newList.get(i).getID()==realID){
			
			theHotel = newList.get(i);
			System.out.println(theHotel.getName()+ " Hér er hótelið þá");
			return theHotel;
		}
	}
		return theHotel;
	}
	/**
	 * Create the dialog.
	 */
	public ClientLogin(final String id1, String reservationID1,final String datein1,final String dateout1, String client, final String id) {
		setTitle("Cancel reservation");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/MYndir/red-capital-letter-h-key-icon-12214.png"));
		setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		name = id1;
		reservationID = reservationID1;
		datein=datein1;
		dateout=dateout1;
		
		ArrayList<Hotel> newList = SearchControl.LeitaHotel("", datein1, dateout1);
		int realID = Integer.parseInt(id);
		final Hotel theHotel = findHotel(newList,realID);
		
		System.out.println(id1 + "           Nafnið er þetta");
		
		
		final BookingInfo bookinginfo1 = new BookingInfo(Front.datein, Front.dateout, // Henda
				// þessu
				// öllu í
				// fall?
				datein1,dateout1, 1, 1);
		
		setBounds(100, 100, 938, 560);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		final JLabel lblCancelReservation = new JLabel("Cancel reservation");
		lblCancelReservation.addMouseListener(new MouseAdapter() {
			
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel your reservation", "Cancelation", JOptionPane.YES_NO_OPTION);
		        if (reply ==JOptionPane.YES_OPTION) {
		        	sqlWorkBench.clientDelete(reservationID);
					textArea.setText("You have no more reservations");
					textArea.updateUI();
		        }
		     
				
				
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblCancelReservation.setFont(new Font("Tahoma", Font.BOLD, 12));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblCancelReservation.setFont(new Font("Tahoma", Font.PLAIN, 11));
			}
		});
		lblCancelReservation.setForeground(Color.RED);
		lblCancelReservation.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblCancelReservation.setBounds(390, 170, 143, 31);
		contentPanel.add(lblCancelReservation);
		
		textArea = new JTextArea("You hava a reservation at " + id1 +"\nFrom: "+ datein+"\nTo: " +dateout);
		textArea.setFont(new Font("Times New Roman", Font.BOLD, 14));
		textArea.setEditable(false);
		textArea.setBackground(Color.WHITE);
		textArea.setBounds(65, 143, 315, 58);
		contentPanel.add(textArea);
		
		JLabel lblHello = new JLabel("Hello "+ client);
		lblHello.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblHello.setBounds(65, 116, 156, 14);
		contentPanel.add(lblHello);
		
		final JLabel lblCancelReservation_1 = new JLabel("Change reservation");
		lblCancelReservation_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblCancelReservation_1.setForeground(Color.BLUE);
		lblCancelReservation_1.setBackground(Color.BLUE);
		lblCancelReservation_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel your reservation", "Cancelation", JOptionPane.YES_NO_OPTION);
		        if (reply ==JOptionPane.YES_OPTION) {
		        	sqlWorkBench.clientDelete(reservationID);
					
					ClientsBookingUI book = new ClientsBookingUI(theHotel, bookinginfo1);
					Front.contentPane.add(book, "3");
					Front.cardLayout.show(Front.contentPane, "3");	
					Front.whatpage=3;
					Front.forwardTakki.setEnabled(false);
					 System.exit(0);
		        }
				
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblCancelReservation_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblCancelReservation_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			}
		});
		lblCancelReservation_1.setBounds(390, 145, 97, 14);
		contentPanel.add(lblCancelReservation_1);
		
		HotelResult panel = new HotelResult(theHotel, bookinginfo1);
		
		panel.button.setVisible(false);
		panel.setBounds(10, 217, 912, 270);
		contentPanel.add(panel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
						try {
							Front.connection.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
