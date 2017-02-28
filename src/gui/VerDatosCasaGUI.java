package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.ApplicationFacadeInterfaceWS;
import domain.RuralHouse;
import exceptions.OfferCanNotBeBooked;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class VerDatosCasaGUI extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerDatosCasaGUI frame = new VerDatosCasaGUI();
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
	public VerDatosCasaGUI() {
		setBounds(100, 100, 451, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblVerDescripcion = new JLabel("Ver descripci\u00F3n de las casas");
		lblVerDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblVerDescripcion.setBounds(89, 13, 223, 30);
		contentPane.add(lblVerDescripcion);
		
	
		contentPane.add(getHouses());

		
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
		
		JList list = new JList();
		list.setBounds(23, 128, 285, 155);
		contentPane.add(list);
	}
	// metodo para obtener las casas en el combobox;
	
	private JComboBox getHouses(){ 
	ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
		
		Vector<RuralHouse> rhs=facade.getAllRuralHouses();
		
	 comboBox = new JComboBox(rhs);
	 comboBox.addActionListener(new ActionListener() {
	 	public void actionPerformed(ActionEvent e) {
	 		
	 		//aqui tenemos que meter lo que pasa cuando hacemos click
	 		
	 		
	 	}
	 });
		comboBox.setBounds(33, 85, 240, 30);
		contentPane.add(comboBox);
		
		return comboBox;
		
	}
	
	
/*
	private boolean ConfirmarDatos() {

		String nl = System.getProperty("line.separator");

		String message = "Porfavor compruebe que los siguientes datos son correctos:" + nl + "Nombre: " + nombre + nl
				+ "Nombre de Usuario: " + usuario + nl + "Contraseña: " + pass + nl + "Cuenta Bancaria: " + cuenta;

		int selection = JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);

		return selection == 0;
	}*/
	
	
	
}
