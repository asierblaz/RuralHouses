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

public class crearCasaGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textCiudad;
	private JLabel lblNewLabel;
	private JTextField textDescripcion;
	private JLabel lblAadirDescripcinDe;
	private JButton btnAadirCasa;
	private String city;
	private String description;
	private RuralHouse rh;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { crearCasaGUI frame = new
	 * crearCasaGUI(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public crearCasaGUI(boolean modificarCasa, RuralHouse casa) {
		setBounds(100, 100, 489, 361);
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

		textDescripcion = new JTextField();
		textDescripcion.setBounds(119, 144, 267, 114);
		contentPane.add(textDescripcion);
		textDescripcion.setColumns(10);

		lblAadirDescripcinDe = new JLabel("A\u00F1adir Descripci\u00F3n de la casa: ");
		lblAadirDescripcinDe.setBounds(122, 115, 207, 16);
		contentPane.add(lblAadirDescripcinDe);
		contentPane.add(getCrearCasa(modificarCasa));

		if (modificarCasa) {
			rh = casa;
			textCiudad.setText(casa.getCity());
			textDescripcion.setText(casa.getDescription());

		}

	}

	// -----------------añadir casa--------------
	private JButton getCrearCasa(boolean modificarCasa) {
		btnAadirCasa = new JButton("A\u00F1adir Casa");
		btnAadirCasa.setBounds(154, 271, 152, 25);
		// añadimos un if para saber si añadimos o modificamos casa

		if (!modificarCasa) {

			btnAadirCasa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					ApplicationFacadeInterfaceWS facade = MainGUI.getBusinessLogic();
					try {
						city = textCiudad.getText();
						description = textDescripcion.getText();

						if (!ComprobarCamposVacios())
							if (ConfirmarDatos()) {
								rh = facade.crearRuralHouse(description, city, (Owner) MainGUI.getUsuario());
								System.out.println(rh.toString() + "Casa añadida correctamente");
								JOptionPane.showMessageDialog(null, "Casa añadida correctamente");
								if (establecerDisponibilidad()) {
									Vector<RuralHouse> rhs = facade.getAllRuralHouses(); //Se_guardan_en_el_vector_de_casas
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

						if (!ComprobarCamposVacios())
							if (ConfirmarDatos()) {
								if(facade.actualizarRuralHouse(rh, description, city)){
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

		if (city.trim().equals("") || description.trim().equals("")) {
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
			return true;
		} else {
			return false;
		}

	}

	private boolean ConfirmarDatos() {

		String nl = System.getProperty("line.separator");

		String message = "Porfavor compruebe que los siguientes datos son correctos:" + nl + "Ciudad: " + city + nl
				+ "Descripción: " + description;

		int selection = JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);

		return selection == 0;
	}

	private boolean establecerDisponibilidad() {

		String nl = System.getProperty("line.separator");

		String message = "¿Desea establecer dsiponibilidad para la casa: " + city + "?";

		int selection = JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);

		return selection == 0;
	}
}
