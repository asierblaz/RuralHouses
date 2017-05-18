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
import domain.Valoracion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.UIManager;
import javax.swing.ImageIcon;

public class ReservarCasaGUI extends JFrame {

	private JPanel contentPane;
	private JTextField numoffer;
	private JTextField textFieldNombre;
	private JTextField precio;
	private JTextField ciudad;
	private JTextField primerdia;
	private JTextField ultimodia;
	private JTextField textTelefono;
	private String telefono;
	private String numNoches;
	private Offer oferta;
	private String precioTotal;
	private Users client;
	private JTextField textFieldNoches;

	/**
	 * 
	 * 
	 * /** Create the frame.
	 */
	public ReservarCasaGUI(Offer of, String noches, RuralHouse ruralHouse) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 530, 659);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEstosSonLos = new JLabel("Estos son los datos de la oferta seleccionada:");
		lblEstosSonLos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEstosSonLos.setBounds(31, 13, 363, 16);
		contentPane.add(lblEstosSonLos);

		JLabel num = new JLabel("Numero de Oferta:");
		num.setBounds(41, 56, 133, 16);
		contentPane.add(num);

		numoffer = new JTextField();
		numoffer.setBackground(Color.WHITE);
		numoffer.setEditable(false);
		numoffer.setBounds(158, 53, 56, 22);
		contentPane.add(numoffer);
		numoffer.setColumns(10);

		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		textFieldNombre.setBounds(147, 272, 116, 22);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

		btnVolver.setBounds(380, 533, 97, 25);
		contentPane.add(btnVolver);

		JLabel lblDueo = new JLabel("Due\u00F1o:");
		lblDueo.setBounds(31, 275, 56, 16);
		contentPane.add(lblDueo);

		precio = new JTextField();
		precio.setBackground(Color.WHITE);
		precio.setEditable(false);
		precio.setBounds(158, 185, 32, 25);
		contentPane.add(precio);
		precio.setColumns(10);

		ciudad = new JTextField();
		ciudad.setBackground(Color.WHITE);
		ciudad.setEditable(false);
		ciudad.setColumns(10);
		ciudad.setBounds(168, 230, 115, 25);
		contentPane.add(ciudad);

		JLabel lblprecio = new JLabel("Precio por noche:");
		lblprecio.setBounds(31, 189, 167, 16);
		contentPane.add(lblprecio);

		JLabel lblMetrosCuadradosm = new JLabel("Ciudad de casa rural");
		lblMetrosCuadradosm.setBounds(31, 234, 143, 16);
		contentPane.add(lblMetrosCuadradosm);

		JLabel primerdialbl = new JLabel("Primer D\u00EDa:");
		primerdialbl.setBounds(41, 99, 76, 16);
		contentPane.add(primerdialbl);
		// asignaciones
		primerdia = new JTextField();
		primerdia.setText((String) null);
		primerdia.setEditable(false);
		primerdia.setColumns(10);
		primerdia.setBackground(Color.WHITE);
		primerdia.setBounds(140, 96, 298, 22);
		contentPane.add(primerdia);

		JLabel lblltimoDa = new JLabel("\u00DAltimo D\u00EDa:");
		lblltimoDa.setBounds(41, 144, 76, 16);
		contentPane.add(lblltimoDa);

		ultimodia = new JTextField();
		ultimodia.setText((String) null);
		ultimodia.setEditable(false);
		ultimodia.setColumns(10);
		ultimodia.setBackground(Color.WHITE);
		ultimodia.setBounds(140, 141, 298, 22);
		contentPane.add(ultimodia);

		numoffer.setText(String.valueOf(of.getOfferNumber()));
		primerdia.setText(String.valueOf(of.getFirstDay()));
		ultimodia.setText(String.valueOf(of.getLastDay()));
		precio.setText(String.valueOf(of.getPrice()));
		ciudad.setText(of.getRuralHouse().getCity());
		textFieldNombre.setText(of.getRuralHouse().getOwner().getNombre());

		String a = String.valueOf(of.getOfferNumber());

		numoffer.setText(a);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("src/Imagenes/o.png"));
		label.setBounds(245, 43, 344, 322);
		contentPane.add(label);

		JLabel lblNecesitamosUnosDatos = new JLabel("Necesitamos tu numero de t\u00E9lefono para terminar");
		lblNecesitamosUnosDatos.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNecesitamosUnosDatos.setBounds(31, 357, 407, 22);
		contentPane.add(lblNecesitamosUnosDatos);

		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(31, 407, 56, 16);
		contentPane.add(lblTelefono);

		JLabel lblNewLabel = new JLabel("El precio total ser\u00E1 de: ");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(31, 459, 183, 16);
		contentPane.add(lblNewLabel);

		textTelefono = new JTextField();
		textTelefono.setBounds(158, 404, 116, 22);
		contentPane.add(textTelefono);
		textTelefono.setColumns(10);
		//------------------------------------
		JLabel labelPrecio = new JLabel("");
		labelPrecio.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelPrecio.setBounds(207, 460, 89, 16);
		contentPane.add(labelPrecio);
		float pre = Float.parseFloat(precio.getText());
		float noche = Float.parseFloat(noches);
		float mul = pre * noche;
		String price = Float.toString(mul);
		labelPrecio.setText(price + "€");
		JButton btnReservar = new JButton("Reservar");
		btnReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ApplicationFacadeInterfaceWS facade = MainGUI.getBusinessLogic();
				
				client= MainGUI.getUsuario();
				oferta=of;
				numNoches=noches;
				precioTotal= price;
				telefono=textTelefono.getText();
				try {
					if (!ComprobarCamposVacios()) {
						if (ConfirmarDatos()) {
							
						Reserva res= facade.crearReserva(ruralHouse,oferta, telefono, precioTotal, numNoches, client);
						System.out.println(res.toString() + "Reserva añadida correctamente");
							JOptionPane.showMessageDialog(null, "Reserva creada correctamente");
							dispose();
						
						}

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnReservar.setBounds(168, 515, 97, 25);
		contentPane.add(btnReservar);

	

		JLabel lblNmeroDe = new JLabel("N\u00FAmero de noches:");
		lblNmeroDe.setBounds(31, 320, 125, 16);
		contentPane.add(lblNmeroDe);

		textFieldNoches = new JTextField();
		textFieldNoches.setEditable(false);
		textFieldNoches.setBounds(167, 317, 116, 22);
		contentPane.add(textFieldNoches);
		textFieldNoches.setColumns(10);
		textFieldNoches.setText(noches);
		// labelPrecio.setText(String.valueOf(precio.getText()));

		/*
		 * textFieldCiudad.setText(casa.getCity());
		 * editorPane.setText(casa.getDescription());
		 * textFieldNombre.setText(casa.getOwner().getUsuario());
		 * textFieldhabita.setText(casa.getNumHabitaciones());
		 * textFieldm2.setText(casa.getM2());
		 * textFielddireccion.setText(casa.getDireccion());
		 */

	}
	private boolean ComprobarCamposVacios() {
		String message = "Porfavor rellene el número de télefono";

		if (telefono.trim().equals("") ) {
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
			return true;
		} else {
			return false;
		}

	}

	private boolean ConfirmarDatos() {

		String nl = System.getProperty("line.separator");

		String message = "Porfavor compruebe que los siguientes datos son correctos:" + nl + "Usuario: " + MainGUI.getUsuario()+nl+"Telefono: "+telefono + nl
				+ "Oferta: " + oferta + nl + "Precio total: " + precioTotal + nl + "Número de Noches: " + numNoches;

		int selection = JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);

		return selection == 0;
	}
}
