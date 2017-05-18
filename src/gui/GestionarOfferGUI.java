package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.ApplicationFacadeInterfaceWS;
import domain.Offer;
import domain.Owner;
import domain.RuralHouse;

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
import javax.swing.ComboBoxEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.util.Vector;

public class GestionarOfferGUI extends JFrame {

	private JPanel contentPane;
	private JTextField numoffer;
	private JTextField textFieldNombre;
	private JTextField precio;
	private JTextField ciudad;
	private JTextField primerdia;
	private JTextField ultimodia;
	private 	JComboBox comboBox ;
	
	
	ApplicationFacadeInterfaceWS facade = MainGUI.getBusinessLogic();
	/**
	 * 
	 * 
	 * /** Create the frame.
	 */
	public GestionarOfferGUI(RuralHouse rh) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 537, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEstosSonLos = new JLabel("Estos son los datos de la oferta seleccionada:");
		lblEstosSonLos.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblEstosSonLos.setBounds(28, 213, 363, 16);
		contentPane.add(lblEstosSonLos);

		JLabel num = new JLabel("Numero de Oferta:");
		num.setBounds(38, 256, 133, 16);
		contentPane.add(num);

		numoffer = new JTextField();
		numoffer.setBackground(Color.WHITE);
		numoffer.setEditable(false);
		numoffer.setBounds(155, 253, 56, 22);
		contentPane.add(numoffer);
		numoffer.setColumns(10);

		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		textFieldNombre.setBounds(144, 472, 116, 22);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

		btnVolver.setBounds(338, 540, 97, 25);
		contentPane.add(btnVolver);
		
		JLabel lblDueo = new JLabel("Due\u00F1o:");
		lblDueo.setBounds(28, 475, 56, 16);
		contentPane.add(lblDueo);
		
		precio = new JTextField();
		precio.setBackground(Color.WHITE);
		precio.setEditable(false);
		precio.setBounds(155, 385, 32, 25);
		contentPane.add(precio);
		precio.setColumns(10);
		
		ciudad = new JTextField();
		ciudad.setBackground(Color.WHITE);
		ciudad.setEditable(false);
		ciudad.setColumns(10);
		ciudad.setBounds(165, 430, 115, 25);
		contentPane.add(ciudad);
		
		JLabel lblprecio = new JLabel("Precio por noche:");
		lblprecio.setBounds(28, 389, 167, 16);
		contentPane.add(lblprecio);
		
		JLabel lblMetrosCuadradosm = new JLabel("Ciudad de casa rural");
		lblMetrosCuadradosm.setBounds(28, 434, 143, 16);
		contentPane.add(lblMetrosCuadradosm);

		JLabel primerdialbl = new JLabel("Primer D\u00EDa:");
		primerdialbl.setBounds(38, 299, 76, 16);
		contentPane.add(primerdialbl);
		//asignaciones
		
		
		Vector<Offer> vectoroffertas = facade.getOffersbyHouse(rh) ;

		
		primerdia = new JTextField();
		primerdia.setText((String) null);
		primerdia.setEditable(false);
		primerdia.setColumns(10);
		primerdia.setBackground(Color.WHITE);
		primerdia.setBounds(137, 296, 298, 22);
		contentPane.add(primerdia);
		
		JLabel lblltimoDa = new JLabel("\u00DAltimo D\u00EDa:");
		lblltimoDa.setBounds(38, 344, 76, 16);
		contentPane.add(lblltimoDa);
		
		ultimodia = new JTextField();
		ultimodia.setText((String) null);
		ultimodia.setEditable(false);
		ultimodia.setColumns(10);
		ultimodia.setBackground(Color.WHITE);
		ultimodia.setBounds(137, 341, 298, 22);
		contentPane.add(ultimodia);
		

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("src/Imagenes/o.png"));
		label.setBounds(242, 243, 344, 322);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Estos son las offertas de la casa seleccionada:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(28, 27, 363, 16);
		contentPane.add(label_1);
		
		JLabel lblSeleccionaUnaOferta = new JLabel("Selecciona una Oferta: ");
		lblSeleccionaUnaOferta.setBounds(38, 79, 192, 16);
		contentPane.add(lblSeleccionaUnaOferta);
		
		JButton btnBorrarOferta = new JButton("Borrar Oferta");
		btnBorrarOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ApplicationFacadeInterfaceWS facade = MainGUI.getBusinessLogic();
				try{
				Offer of = (Offer) comboBox.getSelectedItem();
				
				if(ConfirmarDatos()){
					facade.BorrarOfferta(of);
					setVisible(false);
					
				}
				else{
					String message = "La casa no ha podido ser borrada";
					JOptionPane.showConfirmDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
					setVisible(false);
				}
				
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
	
		btnBorrarOferta.setBounds(371, 175, 116, 25);
		contentPane.add(btnBorrarOferta);
		
		
		 comboBox = new JComboBox(vectoroffertas);
		comboBox.setBounds(202, 71, 189, 25);
		contentPane.add(comboBox);
		
		
		JButton btnVerDatos = new JButton("Ver Datos");
		btnVerDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Offer of = (Offer) comboBox.getSelectedItem();
				
				numoffer.setText(String.valueOf(of.getOfferNumber()));
				primerdia.setText(String.valueOf(of.getFirstDay()));
				ultimodia.setText(String.valueOf(of.getLastDay()));
				precio.setText(String.valueOf(of.getPrice()));
				ciudad.setText(of.getRuralHouse().getCity());
				textFieldNombre.setText(of.getRuralHouse().getOwner().getNombre());
				
				String a = String.valueOf(of.getOfferNumber());
				
				numoffer.setText(a);
				
			}
		});
		btnVerDatos.setBounds(217, 175, 97, 25);
		contentPane.add(btnVerDatos);
		

		/*
		textFieldCiudad.setText(casa.getCity());
		editorPane.setText(casa.getDescription());
		textFieldNombre.setText(casa.getOwner().getUsuario());
		textFieldhabita.setText(casa.getNumHabitaciones());
		textFieldm2.setText(casa.getM2());
		textFielddireccion.setText(casa.getDireccion());*/
		
		
	
		
		


	}
	
	
	private boolean ConfirmarDatos() {

		String nl = System.getProperty("line.separator");

		String message = "¿Está seguro de que quiere eliminar la  Oferta?";

		int selection = JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);

		return selection == 0;
	}
}
