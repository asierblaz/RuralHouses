package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.ApplicationFacadeInterfaceWS;
import domain.Owner;
import domain.RuralHouse;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EliminarCasaGUI extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	private String city;
	private String description;
	
	private JButton btnEliminar;
	
	
	public EliminarCasaGUI(Vector<RuralHouse> rhs) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 454, 206);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblVerDatosDe = new JLabel("Eliminar una Casa");
		lblVerDatosDe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblVerDatosDe.setBounds(119, 13, 210, 16);
		contentPane.add(lblVerDatosDe);
		
		JLabel lblSeleccioneUnaCasa = new JLabel("Seleccione una casa:");
		lblSeleccioneUnaCasa.setBounds(25, 56, 126, 16);
		contentPane.add(lblSeleccioneUnaCasa);
		ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
		
		
		 
		comboBox = new JComboBox(rhs);
		//comboBox.setModel(new DefaultComboBoxModel(rhs));

		comboBox.setBounds(178, 56, 181, 22);
		contentPane.add(comboBox);
		
		
		contentPane.add(getEliminarButton());
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnVolver.setBounds(25, 121, 97, 25);
		contentPane.add(btnVolver);
	}
	private JButton getEliminarButton(){
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				ApplicationFacadeInterfaceWS facade = MainGUI.getBusinessLogic();
				RuralHouse rh = (RuralHouse) comboBox.getSelectedItem();
				city= rh.getCity();
				description= rh.getDescription();
				
				if (ConfirmarDatos()){
					facade.BorrarCasa(rh);
					dispose();

				}
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
				e1.printStackTrace();
			}
		}
	}); 

		btnEliminar.setBounds(327, 121, 97, 25);
		return btnEliminar;
		
	}
	
	private boolean ConfirmarDatos() {

		String nl = System.getProperty("line.separator");

		String message = "¿Está seguro de que quiere eliminar la siguiente casa?: " + nl + "Ciudad: " + city + nl
				+ "Descripción: " + description;

		int selection = JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);

		return selection == 0;
	}
	private boolean mostrarMensajeError() {

		String nl = System.getProperty("line.separator");

		String message = "La casa no ha podido ser eliminada" + nl
				+ "por alguno de estos motivos: " + nl + nl
				+ "La casa actualmente tiene ofertas  " + nl
				+ "Hay una excepción";
		int selection = JOptionPane.showConfirmDialog(null, message,
				"Error en el borrado de casa", JOptionPane.WARNING_MESSAGE);

		return selection == 0;
	}

}
