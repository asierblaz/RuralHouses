package gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;

public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Entrar = new JButton("Entrar");
		Entrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		Entrar.setBounds(192, 220, 97, 25);
		contentPane.add(Entrar);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(178, 122, 163, 25);
		contentPane.add(passwordField);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setForeground(new Color(0, 0, 0));
		lblContrasea.setBounds(43, 122, 97, 25);
		contentPane.add(lblContrasea);
		
		JButton volver = new JButton("Volver");
		volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame atras = new MainGUI();
				atras.setVisible(true);
				setVisible(false);
			}
		});
		volver.setBounds(438, 277, 97, 25);
		contentPane.add(volver);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario");
		lblNombreDeUsuario.setBounds(30, 71, 135, 25);
		contentPane.add(lblNombreDeUsuario);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(178, 72, 163, 22);
		contentPane.add(textArea);
		
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
	}
}
