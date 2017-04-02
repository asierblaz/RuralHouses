package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.ApplicationFacadeInterfaceWS;
import domain.RuralHouse;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class BuscarDescripcionGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JComboBox comboBox;
	private String description;
	private Vector<RuralHouse> rhs = null;
	ApplicationFacadeInterfaceWS facade = MainGUI.getBusinessLogic();

	/**
	 * Create the frame.
	 */
	public BuscarDescripcionGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 364, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblBuscarCasaPor = new JLabel("Buscar Casa por Descripcion");
		lblBuscarCasaPor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBuscarCasaPor.setBounds(77, 13, 203, 16);
		contentPane.add(lblBuscarCasaPor);

		JLabel label = new JLabel("Descripción:");
		label.setBounds(32, 62, 56, 16);
		contentPane.add(label);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(111, 59, 116, 22);
		contentPane.add(textField);

		// vector

		description = TextoDescripcion.getdescription();
		System.out.println(description);
		textField.setText(description);

		rhs = facade.getRuralHouseByDescripcion(description);
		if(NoHayDatos()){
			dispose();
		}
		// combobox
		comboBox = new JComboBox(rhs);
		comboBox.setBounds(53, 122, 247, 30);
		contentPane.add(comboBox);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnVolver.setBounds(12, 243, 97, 25);
		contentPane.add(btnVolver);

		JButton btnVerDatos = new JButton("Ver Datos");
		btnVerDatos.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				RuralHouse rh = (RuralHouse) comboBox.getSelectedItem();
				JFrame ver = new VerDatosHouse(rh);
				ver.setVisible(true);
			}
		});
		btnVerDatos.setBounds(237, 243, 97, 25);
		contentPane.add(btnVerDatos);

	}
	private boolean NoHayDatos() {
		String message = "No se han encontrado datos, inentelo con otras palabras.";

		if (rhs.isEmpty()) {
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
			return true;
		} else {
			return false;
		}

	}
	

}
