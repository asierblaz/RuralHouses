package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerDescripcionGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerDescripcionGUI frame = new VerDescripcionGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VerDescripcionGUI() {
		setBounds(100, 100, 451, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblVerDescripcion = new JLabel("Ver descripci\u00F3n de las casas");
		lblVerDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblVerDescripcion.setBounds(89, 13, 223, 30);
		contentPane.add(lblVerDescripcion);
		
		JTextPane txtpnA = new JTextPane();
		txtpnA.setBounds(33, 163, 280, 151);
		contentPane.add(txtpnA);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(33, 85, 240, 30);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton.setBounds(324, 300, 97, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblElijaLaCasa = new JLabel("Elija la casa de la cual quiere visualizar la descripci\u00F3n");
		lblElijaLaCasa.setBounds(33, 56, 322, 16);
		contentPane.add(lblElijaLaCasa);
	}
}
