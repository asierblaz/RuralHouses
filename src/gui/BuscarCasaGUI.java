package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuscarCasaGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCiudad;
	private JTextField textFieldDireccion;
	private JTextField textFieldHabitaciones;
	private JTextField textFieldM2;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public BuscarCasaGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBuscarCasa = new JLabel("Buscar Casa");
		lblBuscarCasa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBuscarCasa.setBounds(186, 13, 188, 39);
		contentPane.add(lblBuscarCasa);
		
		textFieldCiudad = new JTextField();
		textFieldCiudad.setBounds(186, 70, 116, 22);
		contentPane.add(textFieldCiudad);
		textFieldCiudad.setColumns(10);
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(34, 73, 56, 16);
		contentPane.add(lblCiudad);
		
		textFieldDireccion = new JTextField();
		textFieldDireccion.setBounds(186, 108, 116, 22);
		contentPane.add(textFieldDireccion);
		textFieldDireccion.setColumns(10);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n: ");
		lblDireccin.setBounds(34, 108, 84, 16);
		contentPane.add(lblDireccin);
		
		textFieldHabitaciones = new JTextField();
		textFieldHabitaciones.setBounds(196, 150, 34, 22);
		contentPane.add(textFieldHabitaciones);
		textFieldHabitaciones.setColumns(10);
		
		JLabel lblNmeroDeHabitaciones = new JLabel("N\u00FAmero de Habitaciones: ");
		lblNmeroDeHabitaciones.setBounds(34, 153, 175, 16);
		contentPane.add(lblNmeroDeHabitaciones);
		
		JLabel lblMetrosCuadradosm = new JLabel("Metros cuadrados (m2):");
		lblMetrosCuadradosm.setBounds(34, 182, 147, 16);
		contentPane.add(lblMetrosCuadradosm);
		
		textFieldM2 = new JTextField();
		textFieldM2.setBounds(196, 179, 34, 22);
		contentPane.add(textFieldM2);
		textFieldM2.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(396, 266, 97, 25);
		contentPane.add(btnBuscar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnVolver.setBounds(34, 266, 97, 25);
		contentPane.add(btnVolver);
	}
}
