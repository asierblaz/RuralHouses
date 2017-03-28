package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import gui.*;

import domain.RuralHouse;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModificarCasaGUI extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	private JButton btnModificar;
	/**
	 * Launch the application.
	 */
	/**jsfvbkjvkazdjbljkbxjkb
	 * Create the frame.
	 */
	public ModificarCasaGUI(Vector<RuralHouse> VectorRuralHouses) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 566, 221);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblModificarCasaGUI = new JLabel("Modificar Casa");
		lblModificarCasaGUI.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblModificarCasaGUI.setBounds(143, 13, 164, 34);
		contentPane.add(lblModificarCasaGUI);
		
		JLabel lblSeleccioneLaCasa = new JLabel("Seleccione la casa que desea modificar: ");
		lblSeleccioneLaCasa.setBounds(24, 59, 283, 16);
		contentPane.add(lblSeleccioneLaCasa);
		
		comboBox = new JComboBox(VectorRuralHouses);
		comboBox.setBounds(284, 54, 206, 27);
		contentPane.add(comboBox);
		contentPane.add(getModificar(),null);
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(40, 124, 97, 25);
		contentPane.add(btnVolver);
	}
	 private JButton getModificar(){
		 btnModificar = new JButton("Modificar");
		 btnModificar.setBounds(393, 124, 97, 25);
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					RuralHouse rh = (RuralHouse) comboBox.getSelectedItem();
					JFrame modificar = new crearCasaGUI(true, rh) ;
					modificar.setVisible(true);
					dispose();
				}
			});
			
			return btnModificar;
	 }
}
