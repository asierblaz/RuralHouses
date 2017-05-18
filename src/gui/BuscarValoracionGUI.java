package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.ApplicationFacadeInterfaceWS;
import domain.RuralHouse;
import domain.Valoracion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class BuscarValoracionGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JComboBox comboBox;
	private String puntuacion;
	private Vector<Valoracion> val;
	private Vector<RuralHouse> rhs;
	
	ApplicationFacadeInterfaceWS facade = MainGUI.getBusinessLogic();

	/**
	 * Create the frame.
	 */
	public BuscarValoracionGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 364, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblBuscarCasaPor = new JLabel("Buscar Casa por Puntuaci\u00F3n");
		lblBuscarCasaPor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBuscarCasaPor.setBounds(77, 13, 223, 16);
		contentPane.add(lblBuscarCasaPor);

		JLabel lblPuntuacin = new JLabel("Puntuaci\u00F3n: ");
		lblPuntuacin.setBounds(32, 62, 84, 16);
		contentPane.add(lblPuntuacin);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(128, 59, 116, 22);
		contentPane.add(textField);

		// vector

		puntuacion = TextoPuntuacion.getPuntuacion();
		System.out.println(puntuacion);
		textField.setText(puntuacion);

		rhs= facade.getRuralHouseByPuntuacion(puntuacion);
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
