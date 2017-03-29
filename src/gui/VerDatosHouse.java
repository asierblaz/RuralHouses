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

	/**
	 * 
	 * 
	 * /** Create the frame.
	 */
	public VerDatosHouse(RuralHouse casa) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 465, 344);
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
		textFieldNombre.setBounds(140, 197, 116, 22);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(141, 104, 198, 69);
		contentPane.add(editorPane);

		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setBounds(41, 104, 97, 21);
		contentPane.add(lblDescripcin);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

		btnVolver.setBounds(338, 259, 97, 25);
		contentPane.add(btnVolver);
		textFieldCiudad.setText(casa.getCity());
		editorPane.setText(casa.getDescription());
		textFieldNombre.setText(casa.getOwner().getUsuario());

		JLabel lblDueo = new JLabel("Due\u00F1o:");
		lblDueo.setBounds(41, 200, 56, 16);
		contentPane.add(lblDueo);

	}
}
