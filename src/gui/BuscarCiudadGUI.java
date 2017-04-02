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
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class BuscarCiudadGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JComboBox comboBox;
	private String city;
	private Vector<RuralHouse> rhs= null;
	ApplicationFacadeInterfaceWS facade = MainGUI.getBusinessLogic();


	/**
	 * Create the frame.
	 */
	public BuscarCiudadGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 358, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblBuscarCasaPor = new JLabel("Buscar Casa por Ciudad");
		lblBuscarCasaPor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBuscarCasaPor.setBounds(77, 13, 203, 16);
		contentPane.add(lblBuscarCasaPor);

		JLabel label = new JLabel("Ciudad:");
		label.setBounds(32, 62, 56, 16);
		contentPane.add(label);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(111, 59, 116, 22);
		contentPane.add(textField);

		rhs=facade.getRuralHouseByCiudad(city); //k

		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				city= textField.getText();
				System.out.println(city);
			}
		});
		btnBuscar.setBounds(130, 242, 97, 25);
		contentPane.add(btnBuscar);

		
		//vector
				

				
				
				//combobox
				comboBox = new JComboBox(rhs);
				comboBox.setBounds(53, 122, 247, 30);
				contentPane.add(comboBox);
				
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnVolver.setBounds(12, 290, 97, 25);
		contentPane.add(btnVolver);
		

		
		
		JButton btnVerDatos = new JButton("Ver Datos");
		btnVerDatos.addActionListener(new ActionListener() {
			

			
			public void actionPerformed(ActionEvent arg0) {
				RuralHouse rh = (RuralHouse) comboBox.getSelectedItem();
				JFrame ver = new VerDatosHouse(rh);
				ver.setVisible(true);
			}
		});
		btnVerDatos.setBounds(218, 280, 97, 25);
		contentPane.add(btnVerDatos);

		
	}

}
