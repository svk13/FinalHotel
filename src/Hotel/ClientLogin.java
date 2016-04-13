package Hotel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ClientLogin dialog = new ClientLogin(id, reservationID, datein, dateout, client);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ClientLogin(String id1, String reservationID1,String datein1,String dateout1, String client) {
		setTitle("Cancel reservation");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/MYndir/red-capital-letter-h-key-icon-12214.png"));
		setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		id = id1;
		reservationID = reservationID1;
		datein=datein1;
		dateout=dateout1;
		
		setBounds(100, 100, 632, 403);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		final JLabel lblCancelReservation = new JLabel("Cancel reservation");
		lblCancelReservation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				sqlWorkBench.clientDelete(reservationID);
				textArea.setText("You have no more reservations");
				textArea.updateUI();
				
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
		lblCancelReservation.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCancelReservation.setBounds(345, 75, 143, 31);
		contentPanel.add(lblCancelReservation);
		
		textArea = new JTextArea("You hava a reservation at " + id +"\nFrom: "+ datein+"\nTo: " +dateout);
		textArea.setFont(new Font("Times New Roman", Font.BOLD, 14));
		textArea.setEditable(false);
		textArea.setBackground(Color.WHITE);
		textArea.setBounds(20, 48, 315, 58);
		contentPanel.add(textArea);
		
		JLabel lblHello = new JLabel("Hello "+ client);
		lblHello.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblHello.setBounds(20, 21, 156, 14);
		contentPanel.add(lblHello);
		
		JLabel lblCancelReservation_1 = new JLabel("Change reservation");
		lblCancelReservation_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sqlWorkBench.clientDelete(reservationID);
				
			}
		});
		lblCancelReservation_1.setBounds(345, 50, 97, 14);
		contentPanel.add(lblCancelReservation_1);
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
