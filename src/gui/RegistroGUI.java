package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.ApplicationFacadeInterfaceWS;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JPasswordField;
import domain.*;
import exceptions.*;

public class RegistroGUI extends JFrame {
	
	
	private String nombre;
	private String usuario;
	private String pass;
	private String cuenta;
	private boolean tipoOwner;
	private final ButtonGroup TipoUsuario = new ButtonGroup();
	private JRadioButton rdbtnCliente;
	private JRadioButton rdbtnOwner;

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textUsuario;
	private JTextField textCuenta;
	
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 388, 436);
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
		textUsuario.setBounds(179, 155, 156, 22);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		JLabel lblRegistro = new JLabel("Registro");
		lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblRegistro.setBounds(213, 13, 147, 25);
		contentPane.add(lblRegistro);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario:");
		lblNombreDeUsuario.setBounds(54, 155, 131, 22);
		contentPane.add(lblNombreDeUsuario);
		
		JLabel lblCuentaBancaria = new JLabel("Cuenta Bancaria:");
		lblCuentaBancaria.setBounds(54, 203, 113, 16);
		contentPane.add(lblCuentaBancaria);
		
		textCuenta = new JTextField();
		textCuenta.setBounds(179, 200, 156, 22);
		contentPane.add(textCuenta);
		textCuenta.setColumns(10);
		
		JButton btnNewButton = new JButton("Registrarse");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
					ApplicationFacadeInterfaceWS facade = MainGUI.getBusinessLogic();
					tipoOwner= rdbtnOwner.isSelected();
					nombre= textNombre.getText();
					usuario= textUsuario.getText();
					cuenta= textCuenta.getText();
					if(!checkEmptyFields())
				
				
				
				
			}
		});
		btnNewButton.setBounds(205, 284, 107, 25);
		contentPane.add(btnNewButton);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame vuelta= new MainGUI();
				vuelta.setVisible(true);
				setVisible(false);
			}
		});
		btnVolver.setBounds(36, 336, 97, 25);
		contentPane.add(btnVolver);
		
		JButton btnEntrarEnEl = new JButton("Entrar en el Sistema");
		btnEntrarEnEl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame login= new LoginGUI();
				login.setVisible(true);
				setVisible(false);
				
			}
		});
		btnEntrarEnEl.setBounds(179, 336, 156, 25);
		contentPane.add(btnEntrarEnEl);
		
		rdbtnCliente = new JRadioButton("Cliente");
		TipoUsuario.add(rdbtnCliente);
		rdbtnCliente.setBounds(166, 245, 67, 25);
		contentPane.add(rdbtnCliente);
		
		rdbtnOwner = new JRadioButton("Owner");
		TipoUsuario.add(rdbtnOwner);
		rdbtnOwner.setBounds(268, 245, 67, 25);
		contentPane.add(rdbtnOwner);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(54, 112, 76, 16);
		contentPane.add(lblContrasea);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(179, 109, 156, 22);
		contentPane.add(passwordField);
	}
}
