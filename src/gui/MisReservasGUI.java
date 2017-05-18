package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.ApplicationFacadeInterfaceWS;
import domain.Offer;
import domain.Owner;
import domain.Reserva;
import domain.RuralHouse;
import domain.Users;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JList;
import java.awt.ScrollPane;
import java.awt.Label;
import javax.swing.ImageIcon;

public class MisReservasGUI extends JFrame {

	private JPanel contentPane;
	
	ApplicationFacadeInterfaceWS facade = MainGUI.getBusinessLogic();
	private JTextField textFieldNumReserva;
	private JTextField textFieldCasa;
	private JTextField textFieldPrimerDia;
	private JTextField textFieldUltimoDia;
	private JTextField textFieldNoches;
	private JTextField textFieldOfertaa;
	private JTextField textFieldPrecio;
	private JTextField textFieldTelefono;
	private JTextField textFieldCliente;

	/**
	 * 
	 * 
	 * /** Create the frame.
	 */
	public MisReservasGUI(Vector<Reserva> r,Users u) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 568, 602);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblbltitulo = new JLabel("Estas son los numeros de reserva del cliente "+u.getUsuario()+":");
		lblbltitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblbltitulo.setBounds(31, 13, 517, 16);
		contentPane.add(lblbltitulo);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

		btnVolver.setBounds(242, 506, 97, 25);
		contentPane.add(btnVolver);
		
		Vector<RuralHouse> rhs = facade.getAllRuralHouses();
		Vector<Reserva> reservas = facade.getReservasByClient();

		JComboBox comboBox = new JComboBox(reservas);
		comboBox.setBounds(195, 59, 211, 25);
		contentPane.add(comboBox);
		
		JLabel lblNumerosDeReserva = new JLabel("Numeros de Reserva:");
		lblNumerosDeReserva.setBounds(12, 63, 171, 16);
		contentPane.add(lblNumerosDeReserva);
		
		JLabel lblDatosDeLa = new JLabel("Datos de la reserva seleccionada:");
		lblDatosDeLa.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDatosDeLa.setBounds(12, 154, 517, 16);
		contentPane.add(lblDatosDeLa);
		

	
		
		textFieldNumReserva = new JTextField();
		textFieldNumReserva.setBackground(Color.WHITE);
		textFieldNumReserva.setEditable(false);
		textFieldNumReserva.setBounds(161, 183, 48, 22);
		contentPane.add(textFieldNumReserva);
		textFieldNumReserva.setColumns(10);
		
		JLabel lblCiudadCasaRural = new JLabel("Ciudad Casa Rural:");
		lblCiudadCasaRural.setBounds(31, 228, 113, 16);
		contentPane.add(lblCiudadCasaRural);
		
		textFieldCasa = new JTextField();
		textFieldCasa.setBackground(Color.WHITE);
		textFieldCasa.setEditable(false);
		textFieldCasa.setBounds(161, 225, 116, 22);
		contentPane.add(textFieldCasa);
		textFieldCasa.setColumns(10);
		
		JLabel lblPrimerDa = new JLabel("Primer D\u00EDa:");
		lblPrimerDa.setBounds(31, 301, 118, 16);
		contentPane.add(lblPrimerDa);
		
		textFieldPrimerDia = new JTextField();
		textFieldPrimerDia.setBackground(Color.WHITE);
		textFieldPrimerDia.setEditable(false);
		textFieldPrimerDia.setBounds(161, 298, 363, 22);
		contentPane.add(textFieldPrimerDia);
		textFieldPrimerDia.setColumns(10);
		
		JLabel lblltimoDa = new JLabel("\u00DAltimo D\u00EDa:");
		lblltimoDa.setBounds(31, 335, 118, 16);
		contentPane.add(lblltimoDa);
		
		textFieldUltimoDia = new JTextField();
		textFieldUltimoDia.setBackground(Color.WHITE);
		textFieldUltimoDia.setEditable(false);
		textFieldUltimoDia.setColumns(10);
		textFieldUltimoDia.setBounds(161, 332, 363, 22);
		contentPane.add(textFieldUltimoDia);
		
		JLabel lblNewLabel = new JLabel("N\u00FAmero de Noches: ");
		lblNewLabel.setBounds(31, 367, 138, 16);
		contentPane.add(lblNewLabel);
		
		textFieldNoches = new JTextField();
		textFieldNoches.setBackground(Color.WHITE);
		textFieldNoches.setEditable(false);
		textFieldNoches.setBounds(161, 364, 37, 22);
		contentPane.add(textFieldNoches);
		textFieldNoches.setColumns(10);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(31, 431, 56, 16);
		contentPane.add(lblCliente);
		
		JLabel lblNumoferta = new JLabel("Num.Oferta:");
		lblNumoferta.setBounds(31, 269, 76, 16);
		contentPane.add(lblNumoferta);
		
		JLabel lblPreciototal = new JLabel("Precio Total:");
		lblPreciototal.setBounds(31, 402, 113, 16);
		contentPane.add(lblPreciototal);
		
		JLabel lblTelefno = new JLabel("Telef\u00F3no:");
		lblTelefno.setBounds(31, 466, 56, 16);
		contentPane.add(lblTelefno);
		
		textFieldOfertaa = new JTextField();
		textFieldOfertaa.setBackground(Color.WHITE);
		textFieldOfertaa.setEditable(false);
		textFieldOfertaa.setBounds(161, 266, 116, 22);
		contentPane.add(textFieldOfertaa);
		textFieldOfertaa.setColumns(10);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setBackground(Color.WHITE);
		textFieldPrecio.setEditable(false);
		textFieldPrecio.setBounds(161, 396, 48, 22);
		contentPane.add(textFieldPrecio);
		textFieldPrecio.setColumns(10);

		
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setBackground(Color.WHITE);
		textFieldTelefono.setEditable(false);
		textFieldTelefono.setBounds(161, 463, 116, 22);
		contentPane.add(textFieldTelefono);
		textFieldTelefono.setColumns(10);
		
		JButton btnVerDatos = new JButton("Ver Datos");
		btnVerDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Reserva res = (Reserva) comboBox.getSelectedItem();
				textFieldNumReserva.setText(String.valueOf(res.getNumReserva()));
				textFieldCasa.setText(res.getRuralHouse().getCity());
				textFieldPrimerDia.setText(String.valueOf(res.getOferta().getFirstDay()));
				textFieldUltimoDia.setText(String.valueOf(res.getOferta().getLastDay()));
				textFieldNoches.setText(res.getNumNoches());
				textFieldCliente.setText(res.getClient().getNombre());
				textFieldPrecio.setText(res.getPrecioTotal());
				textFieldTelefono.setText(res.getTelefono());
				textFieldOfertaa.setText(String.valueOf(res.getOferta().getOfferNumber()));
			}
		});
		btnVerDatos.setBounds(290, 116, 97, 25);
		contentPane.add(btnVerDatos);
		
		textFieldCliente = new JTextField();
		textFieldCliente.setBackground(Color.WHITE);
		textFieldCliente.setEditable(false);
		textFieldCliente.setBounds(161, 428, 116, 22);
		contentPane.add(textFieldCliente);
		textFieldCliente.setColumns(10);
		
		JLabel lblReserva = new JLabel("Reserva:");
		lblReserva.setBounds(31, 186, 56, 16);
		contentPane.add(lblReserva);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("src/Imagenes/o.png"));
		label.setBounds(288, 59, 288, 303);
		contentPane.add(label);
		
		
		


	
	}
}
