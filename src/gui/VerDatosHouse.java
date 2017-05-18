package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.ApplicationFacadeInterfaceWS;
import domain.Client;
import domain.Owner;
import domain.RuralHouse;
import domain.Valoracion;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.UIManager;
import java.awt.ScrollPane;
import javax.swing.JTextPane;
import java.awt.Label;
import java.awt.Button;
import javax.swing.ImageIcon;

public class VerDatosHouse extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCiudad;
	private JTextField textFieldNombre;
	private JTextField textFieldhabita;
	private JTextField textFieldm2;
	private JTextField textFielddireccion;
	private JLabel lblNotaMedia;
	ApplicationFacadeInterfaceWS facade = MainGUI.getBusinessLogic();


	/**
	 * 
	 * 
	 * /** Create the frame.
	 */
	public VerDatosHouse(RuralHouse casa) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 541, 757);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNotaMedia = new JLabel("");
		lblNotaMedia.setForeground(new Color(106, 90, 205));
		lblNotaMedia.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNotaMedia.setBounds(457, 13, 56, 81);
		contentPane.add(lblNotaMedia);
		
		
		float m=casa.getMedia();
		int i = (int) m;
		System.out.println(i);
		DecimalFormat df = new DecimalFormat("0.00"); 
		String sm=df.format(m); 
		lblNotaMedia.setText(sm);
		if (lblNotaMedia.getText()==String.valueOf(0)) lblNotaMedia.setText("");
		
	

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
		textFieldNombre.setBackground(Color.WHITE);
		textFieldNombre.setEditable(false);
		textFieldNombre.setBounds(130, 319, 116, 22);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setBounds(141, 143, 198, 69);
		contentPane.add(editorPane);

		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setBounds(41, 143, 97, 21);
		contentPane.add(lblDescripcin);
		
		Button Opina = new Button("!Opina Aquí!");
		Opina.setForeground(Color.RED);
		Opina.setBackground(new Color(173, 216, 230));
		Opina.setFont(new Font("Dialog", Font.PLAIN, 18));
		Opina.setVisible(false);
		Opina.setEnabled(false);
		Opina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame opinar = new ValorarGUI(casa);
				opinar.setVisible(true);
				dispose();
				
			}
		});
		Opina.setBounds(216, 550, 107, 24);
		contentPane.add(Opina);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

		btnVolver.setBounds(400, 672, 97, 25);
		contentPane.add(btnVolver);
		
		JLabel lblDueo = new JLabel("Due\u00F1o:");
		lblDueo.setBounds(31, 322, 56, 16);
		contentPane.add(lblDueo);
		
		textFieldhabita = new JTextField();
		textFieldhabita.setBackground(Color.WHITE);
		textFieldhabita.setEditable(false);
		textFieldhabita.setBounds(198, 225, 32, 25);
		contentPane.add(textFieldhabita);
		textFieldhabita.setColumns(10);
		
		textFieldm2 = new JTextField();
		textFieldm2.setBackground(Color.WHITE);
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

		
		Vector<Valoracion> valoraciones = facade.getComentariosByHouse(casa);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(10, 395, 488, 240);
		textPane.setBackground(new Color(173, 216, 230));

		
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(25, 400, 488, 240);
		scrollPane.add(textPane);
		contentPane.add(scrollPane);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon("src/Imagenes/puntos.png"));
		label_2.setBounds(433, 13, 90, 81);
		contentPane.add(label_2);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("src/Imagenes/Opinion.png"));
		label_1.setBounds(251, 68, 345, 306);
		contentPane.add(label_1);
			
		
		if(!valoraciones.isEmpty()){
		textPane.setText(valoraciones.toString());
		Label label = new Label("Estas son las " +valoraciones.size()+" opiniones de otros clientes sobre esta casa:");
		label.setBounds(31, 370, 375, 24);
		contentPane.add(label);
		Opina.setEnabled(false);
		Opina.setVisible(false);
		
		}
		else{
			String nl = System.getProperty("line.separator");
			textPane.setForeground(Color.RED);
			textPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
			textPane.setText("!Opina sobre esta casa!"+nl+"¿Cómo ha sido tu estancía en la casa?"+nl+"Qué puntuación le das?");
			Label label = new Label("Todavia nadie ha opinado sobre esta casa");
			label.setBounds(31, 370, 375, 24);
			contentPane.add(label);
			
			if (MainGUI.getUsuario() instanceof Client) {
				Opina.setVisible(true);
				Opina.setEnabled(true);
			}else{
				Opina.setEnabled(false);
				Opina.setVisible(false);
			}

			
		}
		
	

		


	}
}
