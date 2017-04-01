package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Owner;
import domain.RuralHouse;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.UIManager;

public class VerDatosHouse extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCiudad;
	private JTextField textFieldNombre;
	private JTextField textFieldhabita;
	private JTextField textFieldm2;
	private JTextField textFielddireccion;

	/**
	 * 
	 * 
	 * /** Create the frame.
	 */
	public VerDatosHouse(RuralHouse casa) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 468, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEstosSonLos = new JLabel("Estos son los datos de la casa seleccionada:");
		lblEstosSonLos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEstosSonLos.setBounds(31, 13, 363, 16);
		contentPane.add(lblEstosSonLos);

		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(41, 56, 56, 16);
		contentPane.add(lblCiudad);

		textFieldCiudad = new JTextField();
		textFieldCiudad.setBackground(Color.WHITE);
		textFieldCiudad.setEditable(false);
		textFieldCiudad.setBounds(140, 56, 143, 22);
		contentPane.add(textFieldCiudad);
		textFieldCiudad.setColumns(10);

		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		textFieldNombre.setBounds(130, 319, 116, 22);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(141, 143, 198, 69);
		contentPane.add(editorPane);

		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setBounds(41, 143, 97, 21);
		contentPane.add(lblDescripcin);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

		btnVolver.setBounds(341, 340, 97, 25);
		contentPane.add(btnVolver);
		
		JLabel lblDueo = new JLabel("Due\u00F1o:");
		lblDueo.setBounds(31, 322, 56, 16);
		contentPane.add(lblDueo);
		
		textFieldhabita = new JTextField();
		textFieldhabita.setEditable(false);
		textFieldhabita.setBounds(198, 225, 32, 25);
		contentPane.add(textFieldhabita);
		textFieldhabita.setColumns(10);
		
		textFieldm2 = new JTextField();
		textFieldm2.setEditable(false);
		textFieldm2.setColumns(10);
		textFieldm2.setBounds(197, 273, 32, 25);
		contentPane.add(textFieldm2);
		
		JLabel lblNmeroDeHabitaciones = new JLabel("N\u00FAmero de Habitaciones:");
		lblNmeroDeHabitaciones.setBounds(31, 225, 167, 16);
		contentPane.add(lblNmeroDeHabitaciones);
		
		JLabel lblMetrosCuadradosm = new JLabel("Metros cuadrados (m\u00B2):");
		lblMetrosCuadradosm.setBounds(31, 273, 143, 16);
		contentPane.add(lblMetrosCuadradosm);

		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setBounds(41, 99, 76, 16);
		contentPane.add(lblDireccin);
		//asignaciones
		textFielddireccion = new JTextField();
		textFielddireccion.setText((String) null);
		textFielddireccion.setEditable(false);
		textFielddireccion.setColumns(10);
		textFielddireccion.setBackground(Color.WHITE);
		textFielddireccion.setBounds(140, 96, 143, 22);
		contentPane.add(textFielddireccion);
		
		
		textFieldCiudad.setText(casa.getCity());
		editorPane.setText(casa.getDescription());
		textFieldNombre.setText(casa.getOwner().getUsuario());
		textFieldhabita.setText(casa.getNumHabitaciones());
		textFieldm2.setText(casa.getM2());
		textFielddireccion.setText(casa.getDireccion());
		
		


	}
}
