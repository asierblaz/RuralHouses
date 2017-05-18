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
import exceptions.OverlappingUsersExists;
import javax.swing.ImageIcon;
import java.awt.Color;

public class ModificarUsuarioGUI extends JFrame {

	private String nombre;
	private String usuario;
	private String pass;
	private String cuenta;
	private final ButtonGroup TipoUsuario = new ButtonGroup();

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textUsuario;
	private JTextField textCuenta;

	private JButton btnActualizar;
	private JPasswordField passwordField;




	public ModificarUsuarioGUI(	 Users u ) {
		setBounds(100, 100, 582, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textNombre = new JTextField();
		textNombre.setBackground(Color.WHITE);
		textNombre.setEditable(false);
		textNombre.setBounds(179, 71, 156, 22);
		contentPane.add(textNombre);
		textNombre.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(54, 74, 56, 16);
		contentPane.add(lblNombre);

		textUsuario = new JTextField();
		textUsuario.setBackground(Color.WHITE);
		textUsuario.setEditable(false);
		textUsuario.setBounds(179, 106, 156, 22);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);

		JLabel miPerfil = new JLabel("Mi Perfil");
		miPerfil.setFont(new Font("Tahoma", Font.PLAIN, 19));
		miPerfil.setBounds(188, 13, 147, 25);
		contentPane.add(miPerfil);

		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario:");
		lblNombreDeUsuario.setBounds(54, 106, 131, 22);
		contentPane.add(lblNombreDeUsuario);

		JLabel lblCuentaBancaria = new JLabel("Cuenta Bancaria:");
		lblCuentaBancaria.setBounds(54, 182, 113, 16);
		contentPane.add(lblCuentaBancaria);

		textCuenta = new JTextField();
		textCuenta.setBackground(Color.WHITE);
		textCuenta.setEditable(false);
		textCuenta.setBounds(179, 179, 156, 22);
		contentPane.add(textCuenta);
		textCuenta.setColumns(10);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnVolver.setBounds(50, 243, 97, 25);
		contentPane.add(btnVolver);
  
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(54, 147, 76, 16);
		contentPane.add(lblContrasea);

		passwordField = new JPasswordField();
		passwordField.setBackground(Color.WHITE);
		passwordField.setEditable(false);
		passwordField.setBounds(179, 144, 156, 22);
		contentPane.add(passwordField);

		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("src/Imagenes/modificarUsu.png"));
		label.setBounds(313, 13, 270, 340);
		contentPane.add(label);
		
		JLabel lblEditarMiPerfil = new JLabel("Editar Mi Perfil");
		lblEditarMiPerfil.setVisible(false);
		lblEditarMiPerfil.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblEditarMiPerfil.setBounds(179, 13, 147, 25);
		contentPane.add(lblEditarMiPerfil);
		
		
		
		
			textNombre.setText(u.getNombre());
			textCuenta.setText(u.getCuenta());
			textUsuario.setText(u.getUsuario());
			passwordField.setText(u.getPass());
			
			
			btnActualizar = new JButton("Actualizar");
			btnActualizar.setBounds(179, 243, 147, 25);
			btnActualizar.setVisible(false);
			btnActualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					ApplicationFacadeInterfaceWS facade= MainGUI.getBusinessLogic();
					try{
				
					nombre = textNombre.getText();
					usuario = textUsuario.getText();
					cuenta = textCuenta.getText();
					pass = passwordField.getText();
					
					
					if(!ComprobarCamposVacios()){
					//	if(PassDiferente()){
							if(ConfirmarDatos()){
								if(facade.actualizarUsuario(u, nombre, usuario, pass, cuenta)){
									
									siRegistro();
								
									setVisible(false);
								}
								else{
									noRegistro();
									setVisible(false);
						//		}
							}
						}
					}
					
					} catch (Exception ex) {
						System.out.println(ex);
						ex.printStackTrace();
					}
				}
			});
			contentPane.add(btnActualizar); // añadimos el boton en la interfaz

			
			
			
			JButton btnHabilitarEdicin = new JButton("Editar Perfil");
			btnHabilitarEdicin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (editarPerfil()){
					miPerfil.setVisible(false);
					lblEditarMiPerfil.setVisible(true);
					textCuenta.setEditable(true);
					textNombre.setEditable(true);
					passwordField.setEditable(true);
					btnActualizar.setVisible(true);
					btnHabilitarEdicin.setVisible(false);}
					
				}
			});
			btnHabilitarEdicin.setBounds(179, 241, 147, 28);
			contentPane.add(btnHabilitarEdicin);
			

			
	}


	// ------------- botón de actualizar------------------------------------

	


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

	}/*
public boolean PassDiferente(){
	String message= "Las contraseñas no coinciden";
	if(passwordField == passwordField_1){
		System.out.println("si");
	
		return true;
		} else {
			System.out.println(passwordField.getText());
			System.out.println(passwordField_1.getText());
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}
}*/
	private boolean editarPerfil(){
		String message = "¿Estas seguro de que deseas editar tu perfil?";


		int selection = JOptionPane.showConfirmDialog(null, message, "Confirmar", JOptionPane.YES_NO_OPTION);

		return selection == 0;
		
	}
	
	private boolean ConfirmarDatos() {

		String nl = System.getProperty("line.separator");

		String message = "Porfavor compruebe que los siguientes datos son correctos:" + nl + "Nombre: " + nombre + nl
				+ "Nombre de Usuario: " + usuario + nl + "Contraseña: " + pass + nl + "Cuenta Bancaria: " + cuenta;

		int selection = JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);

		return selection == 0;
	}

	private void siRegistro() {

		String message = "El Usuario " + usuario + " ha sido modificado con exito!";
		JOptionPane.showMessageDialog(null, message, "Registro Completado", JOptionPane.DEFAULT_OPTION);

		
	}

	private void noRegistro() {

		String message = "El Usuario " + usuario + " no ha sido modificado!";
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);

	
	}
}
