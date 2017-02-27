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
import java.awt.event.ActionEvent;

import domain.*;

public class crearCasaGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textCiudad;
	private JLabel lblNewLabel;
	private JTextField textDescripcion;
	private JLabel lblAadirDescripcinDe;
	private JButton btnAadirCasa;
	private String ciudad;
	private String descripcion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					crearCasaGUI frame = new crearCasaGUI();
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
	public crearCasaGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		contentPane.add(getCrearCasa());
		
		
	}
	//-----------------añadir casa--------------
	private JButton getCrearCasa(){
		btnAadirCasa = new JButton("A\u00F1adir Casa");
		btnAadirCasa.setBounds(154, 271, 152, 25);
		btnAadirCasa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ApplicationFacadeInterfaceWS facade= MainGUI.getBusinessLogic();
				ciudad= textCiudad.getText();
				descripcion= textDescripcion.getText();				
				
				
				
				
			}
		});
		
		return btnAadirCasa;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//----------------------------------------
	private boolean ComprobarCamposVacios() {
		String message = "Porfavor rellene todos los campos";

		if (ciudad.trim().equals("") || descripcion.trim().equals("")) {
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
			return true;
		} else {
			return false;
		}

	}
	
}
