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
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;

public class ValorarGUI extends JFrame {

	private JPanel contentPane;
	private String puntuacion;
	private String comentario;
	private String nombre;
	private JTextPane textcomentario;
	private JTextField textNombre;
	private RuralHouse ruralHouse;
	private JButton btnEnviarOpinin;
	private JRadioButton radioButton1;
	private JRadioButton radioButton2;
	private JRadioButton radioButton3;
	private JRadioButton radioButton4;
	private JRadioButton radioButton5;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel label_1;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * 
	 * 
	 * /** Create the frame.
	 */
	public ValorarGUI(RuralHouse casa) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 510, 655);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		RuralHouse rh1 = casa;
		btnVolver.setBounds(194, 496, 97, 25);
		contentPane.add(btnVolver);

		JLabel lblOpininPersonal = new JLabel("Opini\u00F3n personal:");
		lblOpininPersonal.setBounds(69, 260, 108, 33);
		contentPane.add(lblOpininPersonal);

		textcomentario = new JTextPane();
		textcomentario.setBounds(69, 306, 331, 127);
		contentPane.add(textcomentario);

		JLabel lblPuntuacinDeLa = new JLabel("Puntuaci\u00F3n de la Casa:");
		lblPuntuacinDeLa.setBounds(28, 207, 136, 16);
		contentPane.add(lblPuntuacinDeLa);

		JLabel lblValorarCasa = new JLabel("Valorar Casa");
		lblValorarCasa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblValorarCasa.setBounds(170, 25, 124, 16);
		contentPane.add(lblValorarCasa);

		textNombre = new JTextField();
		textNombre.setEditable(false);
		textNombre.setBounds(170, 162, 80, 22);
		contentPane.add(textNombre);
		textNombre.setColumns(10);

		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario:");
		lblNombreDeUsuario.setBounds(28, 165, 124, 16);
		contentPane.add(lblNombreDeUsuario);
		contentPane.add(getEnviarOpinion());

		radioButton1 = new JRadioButton("");
		buttonGroup.add(radioButton1);
		radioButton1.setBounds(186, 229, 40, 25);
		contentPane.add(radioButton1);

		radioButton2 = new JRadioButton("");
		buttonGroup.add(radioButton2);
		radioButton2.setBounds(223, 229, 25, 25);
		contentPane.add(radioButton2);

		radioButton3 = new JRadioButton("");
		buttonGroup.add(radioButton3);
		radioButton3.setBounds(256, 229, 35, 25);
		contentPane.add(radioButton3);

		radioButton4 = new JRadioButton("");
		buttonGroup.add(radioButton4);
		radioButton4.setBounds(295, 229, 25, 25);
		contentPane.add(radioButton4);

		radioButton5 = new JRadioButton("");
		buttonGroup.add(radioButton5);
		radioButton5.setBounds(324, 229, 35, 25);
		contentPane.add(radioButton5);

		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("src/Imagenes/puntuacion2.png"));
		label_1.setBounds(176, 197, 196, 28);
		contentPane.add(label_1);
		
		JLabel lblNombreDeCasa = new JLabel("Nombre de Casa:");
		lblNombreDeCasa.setBounds(35, 68, 129, 16);
		contentPane.add(lblNombreDeCasa);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(170, 65, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n: ");
		lblDireccin.setBounds(34, 103, 97, 16);
		contentPane.add(lblDireccin);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(170, 100, 116, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField.setText(casa.getCity());
		textField_1.setText(casa.getDireccion());
		
				JLabel label = new JLabel("");
				label.setIcon(new ImageIcon("src/Imagenes/comentario.gif"));
				label.setBounds(79, 55, 478, 496);
				contentPane.add(label);

		// textNombre.setText(u.getNombre());

		if (MainGUI.getUsuario() instanceof Client) {
			Client c = (Client) MainGUI.getUsuario();
			textNombre.setText(c.getNombre());
		}
		ruralHouse = casa;
		
	}

	public JButton getEnviarOpinion() {
		btnEnviarOpinin = new JButton("Enviar Opini\u00F3n");
		btnEnviarOpinin.setBounds(167, 446, 149, 25);
		btnEnviarOpinin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ApplicationFacadeInterfaceWS facade = MainGUI.getBusinessLogic();

				puntuacion();
				// puntuacion = textpuntuacion.getText();
				comentario = textcomentario.getText();
				nombre = textNombre.getText();
				try {
					if (!ComprobarCamposVacios()) {
						if (ConfirmarDatos()) {
							Valoracion val = facade.crearValoracion(ruralHouse, comentario, puntuacion, nombre);
						//	System.out.println(val.toString() + "Valoración añadida correctamente");
							System.out.println("media");
							
							JOptionPane.showMessageDialog(null, "Valoración añadida correctamente");
							dispose();
						}

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		return btnEnviarOpinin;
	}

	private boolean ComprobarCamposVacios() {
		String message = "Porfavor rellene todos los campos";

		if (puntuacion.trim().equals("") || comentario.trim().equals("")) {
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
			return true;
		} else {
			return false;
		}

	}

	private boolean ConfirmarDatos() {

		String nl = System.getProperty("line.separator");

		String message = "Porfavor compruebe que los siguientes datos son correctos:" + nl + "Nombre: " + nombre + nl
				+ "Puntuación: " + puntuacion + nl + "Comentario: " + comentario + nl + "Casa Rural: " + ruralHouse;

		int selection = JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);

		return selection == 0;
	}

	public void puntuacion() {
		if (radioButton1.isSelected())
			puntuacion = "1";
		if (radioButton2.isSelected())
			puntuacion = "2";
		if (radioButton3.isSelected())
			puntuacion = "3";
		if (radioButton4.isSelected())
			puntuacion = "4";
		if (radioButton5.isSelected())
			puntuacion = "5";

	}
}
