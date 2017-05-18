package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.ApplicationFacadeInterfaceWS;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.ImageIcon;

public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;

	private JButton Entrar;
	private String usuario;
	private String pass;
	private JTextField textUsuario;

	public LoginGUI() {
		setBounds(100, 100, 565, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		passwordField = new JPasswordField();
		passwordField.setBounds(355, 122, 163, 25);
		contentPane.add(passwordField);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setForeground(new Color(0, 0, 0));
		lblContrasea.setBounds(207, 122, 97, 25);
		contentPane.add(lblContrasea);

		JButton volver = new JButton("Volver");
		volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		volver.setBounds(438, 277, 97, 25);
		contentPane.add(volver);

		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario");
		lblNombreDeUsuario.setBounds(207, 71, 135, 25);
		contentPane.add(lblNombreDeUsuario);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblLogin.setBounds(222, 13, 249, 45);
		contentPane.add(lblLogin);

		JButton btnnoTienesCuenta = new JButton("\u00BFNo tienes cuenta?");
		btnnoTienesCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame registro = new RegistroGUI();
				registro.setVisible(true);
				setVisible(false);
			}
		});
		btnnoTienesCuenta.setBounds(43, 277, 177, 25);
		contentPane.add(btnnoTienesCuenta);
		contentPane.add(getEntrar());
		
		textUsuario = new JTextField();
		textUsuario.setBounds(355, 72, 163, 25);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("src/Imagenes/login.png"));
		label.setBounds(-44, 13, 258, 231);
		contentPane.add(label);
	}

	// --implementación del boton entrar---

	private JButton getEntrar() {
		Entrar = new JButton("Entrar");
		Entrar.setBounds(207, 219, 97, 25);

		Entrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			try{
			ApplicationFacadeInterfaceWS facade = MainGUI.getBusinessLogic();
			usuario= textUsuario.getText();
			pass= passwordField.getText();
			
			MainGUI.setUsuario(facade.comprobarUsuario(usuario, pass));
			boolean b= MainGUI.getUsuario() != null;
			System.out.println(b);
			showLoginMessage(b);
			
			textUsuario.setText("");
			passwordField.setText("");
			
			if (b){
			MainGUI.setModoRegistro();
			dispose();}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			
				 
				
			}
		});
		return Entrar;

	}

	// --------------metodos de alertas del sistema---------------------------
	private void showLoginMessage(boolean acceso) {
		String message;
		if (acceso) {
			message = "Bienvenido " + usuario;
			JOptionPane.showMessageDialog(null, message, "Acceso Permitido",
					JOptionPane.PLAIN_MESSAGE);
		} else {
			message = "Error: Datos erroneos, compruebe la contraseña";
			JOptionPane.showMessageDialog(null, message, "Acceso Denegado",
					JOptionPane.PLAIN_MESSAGE);
		}
		

	}
}
