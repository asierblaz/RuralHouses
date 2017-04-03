package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.ApplicationFacadeInterfaceWS;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

import domain.*;
import gui.*;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;

public class crearCasaGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textCiudad;
	private JLabel lblNewLabel;
	private JLabel lblAadirDescripcinDe;
	private JButton btnAadirCasa;
	private String city;
	private String description;
	private RuralHouse rh;
	private JTextField tDireccion;
	private JTextField tNumHabitaciones;
	private JTextField tm2;
	private JTextPane textDescripcion;
	private String direccion;
	private String numHabitaciones;
	private String m2;

	/**
	

	/**
	 * Create the frame.
	 */
	public crearCasaGUI(boolean modificarCasa, RuralHouse casa) {
		setBounds(100, 100, 492, 553);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAadirCasa = new JLabel("Crear Casa");
		lblAadirCasa.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblAadirCasa.setBounds(184, 13, 122, 29);
		contentPane.add(lblAadirCasa);

		textCiudad = new JTextField();
		textCiudad.setBounds(119, 57, 279, 29);
		contentPane.add(textCiudad);
		textCiudad.setColumns(10);

		lblNewLabel = new JLabel("Ciudad: ");
		lblNewLabel.setBounds(26, 61, 56, 16);
		contentPane.add(lblNewLabel);

		lblAadirDescripcinDe = new JLabel("A\u00F1adir Descripci\u00F3n de la casa: ");
		lblAadirDescripcinDe.setBounds(26, 276, 207, 16);
		contentPane.add(lblAadirDescripcinDe);
		contentPane.add(getCrearCasa(modificarCasa));
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setBounds(26, 105, 81, 16);
		contentPane.add(lblDireccin);
		
		tDireccion = new JTextField();
		tDireccion.setBounds(119, 99, 279, 29);
		contentPane.add(tDireccion);
		tDireccion.setColumns(10);
		
		JLabel lable = new JLabel("N\u00FAmero de Habitaciones:");
		lable.setBounds(26, 144, 169, 16);
		contentPane.add(lable);
		
		tNumHabitaciones = new JTextField();
		tNumHabitaciones.setText("0");
		tNumHabitaciones.setBounds(184, 141, 36, 22);
		contentPane.add(tNumHabitaciones);
		tNumHabitaciones.setColumns(10);
		
		tm2 = new JTextField();
		tm2.setText("80");
		tm2.setBounds(184, 191, 36, 22);
		contentPane.add(tm2);
		tm2.setColumns(10);
		
		JLabel lblMetrosCuadradosm = new JLabel("Metros Cuadrados (m\u00B2):");
		lblMetrosCuadradosm.setBounds(26, 194, 146, 16);
		contentPane.add(lblMetrosCuadradosm);
		
		textDescripcion = new JTextPane();
		textDescripcion.setBounds(26, 305, 420, 114);
		contentPane.add(textDescripcion);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("src/Imagenes/crearCasa.png"));
		lblNewLabel_1.setBounds(90, 226, 410, 364);
		contentPane.add(lblNewLabel_1);

		if (modificarCasa) {
			rh = casa;
			textCiudad.setText(casa.getCity());
			textDescripcion.setText(casa.getDescription());
			tDireccion.setText(casa.getDireccion());
			tm2.setText(casa.getM2());
			tNumHabitaciones.setText(casa.getNumHabitaciones());

		}

	}

	// -----------------añadir casa--------------
	private JButton getCrearCasa(boolean modificarCasa) {
		btnAadirCasa = new JButton("A\u00F1adir Casa");
		btnAadirCasa.setBounds(154, 450, 152, 25);
		// añadimos un if para saber si añadimos o modificamos casa

		if (!modificarCasa) {

			btnAadirCasa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

						
					ApplicationFacadeInterfaceWS facade = MainGUI.getBusinessLogic();
					try {
						
						city = textCiudad.getText();
						description = textDescripcion.getText();
						direccion= tDireccion.getText();
						m2= tm2.getText();
						numHabitaciones = tNumHabitaciones.getText();

						if (!ComprobarCamposVacios())
							if (ConfirmarDatos()) {
							rh= facade.crearRuralHouse(description, city, direccion, m2, numHabitaciones, (Owner) MainGUI.getUsuario());
								System.out.println(rh.toString() + "Casa añadida correctamente");
								JOptionPane.showMessageDialog(null, "Casa añadida correctamente");
								
							if (establecerDisponibilidad()) {
									Vector<RuralHouse> rhs = facade.getRuralHousesByOwner(); //Se_guardan_en_el_vector_de_casas
									JFrame a = new SetAvailabilityGUI(rhs);
									a.setVisible(true);
									setVisible(false);

								} else
									dispose();

							}
					} catch (Exception ex) {
						ex.printStackTrace();
					}

				}
			});
		} else {
			btnAadirCasa.setText("Actualizar Casa");
			btnAadirCasa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ApplicationFacadeInterfaceWS facade = MainGUI.getBusinessLogic();
					try {
						city = textCiudad.getText();
						description = textDescripcion.getText();
						direccion= tDireccion.getText();
						m2= tm2.getText();
						numHabitaciones = tNumHabitaciones.getText();

						if (!ComprobarCamposVacios())
							if (ConfirmarDatos()) {
								if(facade.actualizarRuralHouse(rh, description, city, direccion, numHabitaciones, m2)){
									System.out.println(rh.toString() + "Casa actualizada correctamente");
									JOptionPane.showMessageDialog(null, "Casa actualizada correctamente");
									dispose();
								}
								
							}else {
								System.out.println(rh.toString() + "La casa no se ha añadido correctamente");
								JOptionPane.showMessageDialog(null, "La casa no se ha añadido correctamente");
							}
					} catch (Exception ex) {
						ex.printStackTrace();
					}

				}
			});

		}

		return btnAadirCasa;
	}

	// ----------------------------------------
	private boolean ComprobarCamposVacios() {
		String message = "Porfavor rellene todos los campos";

		if (city.trim().equals("") || description.trim().equals("")|| numHabitaciones.trim().equals("")|| direccion.trim().equals("")|| m2.trim().equals("")) {
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
			return true;
		} else {
			return false;
		}

	}

	private boolean ConfirmarDatos() {

		String nl = System.getProperty("line.separator");

		String message = "Porfavor compruebe que los siguientes datos son correctos:" + nl + "Ciudad: " + city + nl
				+ "Descripción: " + description + nl+"Dirección: "+direccion +nl +"Número de habitaciones: "+ numHabitaciones + nl+"Metros Cuadrados: "+ m2;

		int selection = JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);

		return selection == 0;
	}

	private boolean establecerDisponibilidad() {

		//String nl = System.getProperty("line.separator");

		String message = "¿Desea establecer dsiponibilidad para la casa: " + city + "?";

		int selection = JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);

		return selection == 0;
	}
}
