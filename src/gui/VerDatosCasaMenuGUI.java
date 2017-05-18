package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.ApplicationFacadeInterfaceWS;
import domain.RuralHouse;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerDatosCasaMenuGUI extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public VerDatosCasaMenuGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 454, 206);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblVerDatosDe = new JLabel("Ver datos de Casa");
		lblVerDatosDe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblVerDatosDe.setBounds(119, 13, 210, 16);
		contentPane.add(lblVerDatosDe);
		
		JLabel lblSeleccioneUnaCasa = new JLabel("Seleccione una casa:");
		lblSeleccioneUnaCasa.setBounds(25, 56, 126, 16);
		contentPane.add(lblSeleccioneUnaCasa);
		ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
		
		Vector<RuralHouse> rhs=facade.getAllRuralHouses();
		//Vector<RuralHouse> rhs=facade.getRuralHousesByOwner();
		comboBox = new JComboBox(rhs);
		//comboBox.setModel(new DefaultComboBoxModel(rhs));

		comboBox.setBounds(178, 56, 181, 22);
		contentPane.add(comboBox);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RuralHouse rh = (RuralHouse) comboBox.getSelectedItem();
			JFrame ver = new VerDatosHouse(rh);
				ver.setVisible(true);
				
			}
		});
		btnAceptar.setBounds(327, 121, 97, 25);
		contentPane.add(btnAceptar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnVolver.setBounds(25, 121, 97, 25);
		contentPane.add(btnVolver);
	}
}
