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
import javax.swing.ImageIcon;

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
		setBounds(100, 100, 675, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBuscarCasa = new JLabel("Buscar Casa");
		lblBuscarCasa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBuscarCasa.setBounds(134, 13, 188, 39);
		contentPane.add(lblBuscarCasa);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnVolver.setBounds(22, 335, 97, 25);
		contentPane.add(btnVolver);
		
		JLabel lblElijeMedianteQue = new JLabel("Elije mediante que parametro deseas realizar la busqueda:");
		lblElijeMedianteQue.setBounds(22, 54, 377, 16);
		contentPane.add(lblElijeMedianteQue);
		
		JButton btnCiudad = new JButton("Ciudad");
		btnCiudad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame ir = new TextoCiudad();
				ir.setVisible(true);
			}
		});
		btnCiudad.setBounds(121, 95, 168, 25);
		contentPane.add(btnCiudad);
		
		JButton btnNhabitaciones = new JButton("N\u00BAHabitaciones");
		btnNhabitaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame ir = new TextoHabitaciones();
				ir.setVisible(true);
			}
		});
		btnNhabitaciones.setBounds(121, 171, 168, 25);
		contentPane.add(btnNhabitaciones);
		
		JButton btnDireccin = new JButton("Direcci\u00F3n");
		btnDireccin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame ir = new TextoDireccion();
				ir.setVisible(true);
			}
		});
		btnDireccin.setBounds(121, 133, 168, 25);
		contentPane.add(btnDireccin);
		
		JButton btnMetrosCuadradosm = new JButton("Metros Cuadrados (m2)");
		btnMetrosCuadradosm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame ir = new TextoMetros();
				ir.setVisible(true);
			}
		});
		btnMetrosCuadradosm.setBounds(121, 209, 168, 25);
		contentPane.add(btnMetrosCuadradosm);
		
		JButton btnDescripcin = new JButton("Descripci\u00F3n");
		btnDescripcin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame ir = new TextoDescripcion();
				ir.setVisible(true);
			}
		});
		btnDescripcin.setBounds(121, 247, 168, 25);
		contentPane.add(btnDescripcin);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("src/Imagenes/Busqueda.png"));
		label.setBounds(333, 26, 469, 334);
		contentPane.add(label);
		
		JButton buttonPuntuacion = new JButton("Puntuaci\u00F3n");
		buttonPuntuacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame ir = new TextoPuntuacion();
				ir.setVisible(true);
			}
		});
		buttonPuntuacion.setBounds(121, 285, 168, 25);
		contentPane.add(buttonPuntuacion);
	}
}
