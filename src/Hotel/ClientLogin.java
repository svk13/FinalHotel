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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ClientLogin dialog = new ClientLogin(id, reservationID, datein, dateout);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ClientLogin(String id1, String reservationID1,String datein1,String dateout1) {
		setResizable(false);
		id = id1;
		reservationID = reservationID1;
		datein=datein1;
		dateout=dateout1;
		
		setBounds(100, 100, 632, 403);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCancelReservation = new JLabel("Cancel reservation");
		lblCancelReservation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				sqlWorkBench.clientDelete(reservationID);
				textArea.setText("You have no more reservations");
				textArea.updateUI();
				
			}
		});
		lblCancelReservation.setForeground(Color.RED);
		lblCancelReservation.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCancelReservation.setBounds(345, 53, 97, 14);
		contentPanel.add(lblCancelReservation);
		
		textArea = new JTextArea("You hava a reservation at " + id +"\nFrom: "+ datein+"\nTo: " +dateout);
		textArea.setEditable(false);
		textArea.setBackground(SystemColor.activeCaptionBorder);
		textArea.setBounds(20, 48, 315, 58);
		contentPanel.add(textArea);
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(ClientLogin.class.getResource("/Myndir/mynd.jpg")));
			lblNewLabel.setBounds(0, 0, 616, 365);
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
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
