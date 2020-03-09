package eLiq;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.EventQueue;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class eLiq{

	private JFrame eLiq;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					eLiq window = new eLiq();
					window.eLiq.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public eLiq() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private void initialize() {
		eLiq = new JFrame();
		eLiq.setTitle("eLiq");
		eLiq.setResizable(false);
		eLiq.setSize(258, 392);;
		eLiq.getContentPane().setLayout(null);
		eLiq.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTextArea txtrPg = new JTextArea();
		txtrPg.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		txtrPg.setBackground(SystemColor.menu);
		txtrPg.setEditable(false);
		txtrPg.setText("PG %");
		txtrPg.setBounds(22, 26, 37, 22);
		eLiq.getContentPane().add(txtrPg);
		
		JTextArea txtrVg = new JTextArea();
		txtrVg.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		txtrVg.setBackground(SystemColor.menu);
		txtrVg.setEditable(false);
		txtrVg.setText("VG %");
		txtrVg.setBounds(163, 26, 37, 22);
		eLiq.getContentPane().add(txtrVg);
		
		JTextArea txtrNicotinemgml = new JTextArea();
		txtrNicotinemgml.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		txtrNicotinemgml.setBackground(SystemColor.menu);
		txtrNicotinemgml.setEditable(false);
		txtrNicotinemgml.setText("Nicotine");
		txtrNicotinemgml.setBounds(22, 122, 69, 22);
		eLiq.getContentPane().add(txtrNicotinemgml);
		
		JTextArea txtrVolume = new JTextArea();
		txtrVolume.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		txtrVolume.setBackground(SystemColor.menu);
		txtrVolume.setEditable(false);
		txtrVolume.setText("Volume");
		txtrVolume.setBounds(163, 122, 53, 22);
		eLiq.getContentPane().add(txtrVolume);
		
		JFormattedTextField VG = new JFormattedTextField();
		VG.setBounds(163, 51, 60, 20);
		eLiq.getContentPane().add(VG);
		
		JFormattedTextField PG = new JFormattedTextField();
		PG.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				pg_update();
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				pg_update();
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				pg_update();
				
			}
			public void pg_update() throws NumberFormatException {
				int pg_value = Integer.parseInt(PG.getText());
				if (pg_value > 100) {
					JOptionPane.showMessageDialog(null,
					          "Error: Please enter number 0 - 100", "Error Message",
					          JOptionPane.ERROR_MESSAGE);
				}
				VG.setText(String.valueOf(100-pg_value));
			}
		});
		PG.setBounds(22, 51, 60, 20);
		eLiq.getContentPane().add(PG);
		
		JFormattedTextField Nicotine = new JFormattedTextField();
		Nicotine.setBounds(22, 155, 60, 20);
		eLiq.getContentPane().add(Nicotine);
		
		JFormattedTextField Volume = new JFormattedTextField();
		Volume.setBounds(163, 155, 60, 20);
		eLiq.getContentPane().add(Volume);
		
		JButton mix_button = new JButton("Mix");
		mix_button.setBackground(Color.LIGHT_GRAY);
		mix_button.setBounds(84, 186, 78, 23);
		eLiq.getContentPane().add(mix_button);
		
		JLabel resultPG = new JLabel("PG:");
		resultPG.setForeground(Color.BLACK);
		resultPG.setBackground(Color.WHITE);
		resultPG.setBounds(37, 241, 163, 22);
		resultPG.setHorizontalAlignment(SwingConstants.CENTER);
		eLiq.getContentPane().add(resultPG);
		
		JLabel resultVG = new JLabel("VG:");
		resultVG.setBounds(37, 264, 163, 22);
		resultVG.setHorizontalAlignment(SwingConstants.CENTER);
		eLiq.getContentPane().add(resultVG);
		
		JLabel resultNic = new JLabel("Nicotine:");
		resultNic.setBounds(37, 286, 163, 22);
		resultNic.setHorizontalAlignment(SwingConstants.CENTER);
		eLiq.getContentPane().add(resultNic);
		
		
        mix_button.addActionListener(new ActionListener() {

            @Override
			public void actionPerformed(ActionEvent arg0) {
            	String PG_text_field = PG.getText();
            	int get_PG = Integer.parseInt(PG_text_field);
            	String VG_text_field = VG.getText();
            	int get_VG = Integer.parseInt(VG_text_field);
            	String nic_text_field = Nicotine.getText();
            	int desired_nic = Integer.parseInt(nic_text_field);
            	String vol_text_field = Volume.getText();
            	int vol = Integer.parseInt(vol_text_field);
        		Calc calc = new Calc(vol);
        		double end_nic = calc.nic(desired_nic);
        		double end_PG = calc.PG(get_PG);
        		double end_VG = calc.VG(get_VG);
        		if (end_PG < 0) {
        			JOptionPane.showMessageDialog(null,
					          "Error: Cannot mix, raise PG percentage");
        		} else if (end_VG < 0) {
        			JOptionPane.showMessageDialog(null,
					          "Error: Cannot mix, raise VG percentage");
        		} else {
        			resultNic.setText("Nicotine: "+end_nic);
        			resultPG.setText("PG: "+end_PG);
        			resultVG.setText("VG: "+end_VG);
        		}
            }
        });
	}
}
