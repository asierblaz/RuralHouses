package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.ApplicationFacadeInterfaceWS;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JPasswordField;
import domain.*;
import exceptions.OverlappingClientExists;
import exceptions.OverlappingUsersExists;

public class RegistroGUI extends JFrame {

	private String nombre;
	private String usuario;
	private String pass;
	private String cuenta;
	private final ButtonGroup TipoUsuario = new ButtonGroup();
	private JRadioButton rdbtnCliente;
	private JRadioButton rdbtnOwner;

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textUsuario;
	private JTextField textCuenta;

	private JButton btnRegistro;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroGUI frame = new RegistroGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegistroGUI() {
		setBounds(100, 100, 396, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textNombre = new JTextField();
		textNombre.setBounds(179, 71, 156, 22);
		contentPane.add(textNombre);
		textNombre.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(54, 74, 56, 16);
		contentPane.add(lblNombre);

		textUsuario = new JTextField();
		textUsuario.setBounds(179, 106, 156, 22);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);

		JLabel lblRegistro = new JLabel("Registro");
		lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblRegistro.setBounds(213, 13, 147, 25);
		contentPane.add(lblRegistro);

		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario:");
		lblNombreDeUsuario.setBounds(54, 106, 131, 22);
		contentPane.add(lblNombreDeUsuario);

		JLabel lblCuentaBancaria = new JLabel("Cuenta Bancaria:");
		lblCuentaBancaria.setBounds(54, 182, 113, 16);
		contentPane.add(lblCuentaBancaria);

		textCuenta = new JTextField();
		textCuenta.setBounds(179, 179, 156, 22);
		contentPane.add(textCuenta);
		textCuenta.setColumns(10);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnVolver.setBounds(36, 305, 97, 25);
		contentPane.add(btnVolver);

		JButton btnEntrarEnEl = new JButton("Entrar en el Sistema");
		btnEntrarEnEl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame login = new LoginGUI();
				login.setVisible(true);
				setVisible(false);

			}
		});
		btnEntrarEnEl.setBounds(179, 305, 156, 25);
		contentPane.add(btnEntrarEnEl);

		rdbtnCliente = new JRadioButton("Cliente");
		rdbtnCliente.setSelected(true);
		TipoUsuario.add(rdbtnCliente);
		rdbtnCliente.setBounds(166, 214, 67, 25);
		contentPane.add(rdbtnCliente);

		rdbtnOwner = new JRadioButton("Owner");
		rdbtnOwner.setSelected(true);
		TipoUsuario.add(rdbtnOwner);
		rdbtnOwner.setBounds(268, 214, 67, 25);
		contentPane.add(rdbtnOwner);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(54, 147, 76, 16);
		contentPane.add(lblContrasea);

		passwordField = new JPasswordField();
		passwordField.setBounds(179, 144, 156, 22);
		contentPane.add(passwordField);

		contentPane.add(getJButton(), null); // añadimos el boton en la interfaz
	}

	// ------------- botón de registro------------------------------------

	private JButton getJButton() {

		btnRegistro = new JButton("Registrarse");
		btnRegistro.setBounds(205, 253, 107, 25);
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					ApplicationFacadeInterfaceWS facade = MainGUI.getBusinessLogic();
					nombre = textNombre.getText();
					usuario = textUsuario.getText();
					cuenta = textCuenta.getText();
					pass = passwordField.getText();
					if (!ComprobarCamposVacios()) {
							if (ConfirmarDatos()) {
								if (rdbtnCliente.isSelected()) {
									Users u = facade.crearCliente(nombre, usuario, pass, cuenta);
									System.out.println(u.toString() + "registrado como Cliente;");
								}
								if (rdbtnOwner.isSelected()) {
									Users us = facade.crearOwner(nombre, usuario, pass, cuenta);
									System.out.println(us.toString() + "registrado como Owner;");

								}
								siRegistro();
								setVisible(false);
							} else {
								noRegistro();
								setVisible(false);

							}
					
					}

				} catch (OverlappingUsersExists e) { // over
					usuarioExiste();
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});

		return btnRegistro;

	}

	// --------------------------------------------------------------------
	// metodos para alertas
	private boolean ComprobarCamposVacios() {
		String message = "Porfavor rellene todos los campos";

		if (cuenta.trim().equals("") || pass.trim().equals("") || usuario.trim().equals("")
				|| nombre.trim().equals("")) {
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
			return true;
		} else {
			return false;
		}

	}

	private boolean ConfirmarDatos() {

		String nl = System.getProperty("line.separator");

		String message = "Porfavor compruebe que los siguientes datos son correctos:" + nl + "Nombre: " + nombre + nl
				+ "Nombre de Usuario: " + usuario + nl + "Contraseña: " + pass + nl + "Cuenta Bancaria: " + cuenta;

		int selection = JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);

		return selection == 0;
	}

	private void siRegistro() {

		String message = "El Usuario " + usuario + " ha sido registrado con exito!";
		JOptionPane.showMessageDialog(null, message, "Registro Completado", JOptionPane.DEFAULT_OPTION);

		textNombre.setText("");
		textUsuario.setText("");
		passwordField.setText("");
		textCuenta.setText("");
	}

	private void noRegistro() {

		String message = "El Usuario " + usuario + " no ha sido registrado!";
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);

		textNombre.setText("");
		textUsuario.setText("");
		passwordField.setText("");
		textCuenta.setText("");
	}

	private void usuarioExiste() {
		String message = "El usuario ya esta registrado";
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
		textNombre.setText("");
		textUsuario.setText("");
		passwordField.setText("");
		textCuenta.setText("");

	}
}
