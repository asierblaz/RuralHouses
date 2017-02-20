package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Registro extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro frame = new Registro();
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
	public Registro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 448, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(179, 71, 156, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(54, 74, 56, 16);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(54, 112, 56, 16);
		contentPane.add(lblApellido);
		
		textField_1 = new JTextField();
		textField_1.setBounds(179, 109, 156, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(179, 155, 156, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(179, 206, 156, 22);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblRegistro = new JLabel("Registro");
		lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblRegistro.setBounds(213, 13, 147, 25);
		contentPane.add(lblRegistro);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario:");
		lblNombreDeUsuario.setBounds(54, 155, 131, 22);
		contentPane.add(lblNombreDeUsuario);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(54, 208, 113, 19);
		contentPane.add(lblEmail);
		
		JLabel lblCuentaBancaria = new JLabel("Cuenta Bancaria:");
		lblCuentaBancaria.setBounds(54, 258, 113, 16);
		contentPane.add(lblCuentaBancaria);
		
		textField_4 = new JTextField();
		textField_4.setBounds(179, 255, 156, 22);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton = new JButton("Registrarse");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(193, 325, 107, 25);
		contentPane.add(btnNewButton);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame vuelta= new MainGUI();
				vuelta.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(24, 396, 97, 25);
		contentPane.add(btnVolver);
		
		JButton btnEntrarEnEl = new JButton("Entrar en el Sistema");
		btnEntrarEnEl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame login= new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnEntrarEnEl.setBounds(161, 363, 156, 25);
		contentPane.add(btnEntrarEnEl);
	}

}
