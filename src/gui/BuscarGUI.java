package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class BuscarGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public BuscarGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 409, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBuscarCasa = new JLabel("Buscar Casa");
		lblBuscarCasa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBuscarCasa.setBounds(140, 13, 188, 39);
		contentPane.add(lblBuscarCasa);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(271, 311, 97, 25);
		contentPane.add(btnBuscar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnVolver.setBounds(22, 311, 97, 25);
		contentPane.add(btnVolver);
		
		JLabel lblElijeMedianteQue = new JLabel("Elije mediante que parametro deseas realizar la busqueda:");
		lblElijeMedianteQue.setBounds(22, 54, 377, 16);
		contentPane.add(lblElijeMedianteQue);
		
		JButton btnCiudad = new JButton("Ciudad");
		btnCiudad.setBounds(121, 83, 168, 25);
		contentPane.add(btnCiudad);
		
		JButton btnNhabitaciones = new JButton("N\u00BAHabitaciones");
		btnNhabitaciones.setBounds(121, 159, 168, 25);
		contentPane.add(btnNhabitaciones);
		
		JButton btnDireccin = new JButton("Direcci\u00F3n");
		btnDireccin.setBounds(121, 121, 168, 25);
		contentPane.add(btnDireccin);
		
		JButton btnMetrosCuadradosm = new JButton("Metros Cuadrados (m2)");
		btnMetrosCuadradosm.setBounds(121, 197, 168, 25);
		contentPane.add(btnMetrosCuadradosm);
		
		JButton btnDescripcin = new JButton("Descripci\u00F3n");
		btnDescripcin.setBounds(121, 245, 168, 25);
		contentPane.add(btnDescripcin);
	}
}
