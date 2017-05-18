package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;

public class TextoPuntuacion extends JFrame {

	private JPanel contentPane;
	private static String puntuacion;

	public static void setPuntuacion(String puntuacion) {
		TextoPuntuacion.puntuacion = puntuacion;
	}
	private JButton btnVolver;
	private JRadioButton radioButton1;
	private JRadioButton radioButton5;
	private JRadioButton radioButton2;
	private JRadioButton radioButton3;
	private JRadioButton radioButton4;
	private JLabel label;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public TextoPuntuacion() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 443, 207);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBuscar());

		JLabel lblIntroducePuntuacion = new JLabel("Introduce Puntuaci\u00F3n:");
		lblIntroducePuntuacion.setBounds(36, 45, 140, 16);
		contentPane.add(lblIntroducePuntuacion);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnVolver.setBounds(24, 114, 97, 25);
		contentPane.add(btnVolver);
		
		radioButton1 = new JRadioButton("");
		buttonGroup.add(radioButton1);
		radioButton1.setBounds(178, 70, 30, 25);
		contentPane.add(radioButton1);
		
		radioButton5 = new JRadioButton("");
		buttonGroup.add(radioButton5);
		radioButton5.setBounds(318, 70, 30, 25);
		contentPane.add(radioButton5);
		
		radioButton2 = new JRadioButton("");
		buttonGroup.add(radioButton2);
		radioButton2.setBounds(216, 70, 30, 25);
		contentPane.add(radioButton2);
		
		radioButton3 = new JRadioButton("");
		buttonGroup.add(radioButton3);
		radioButton3.setBounds(250, 70, 30, 25);
		contentPane.add(radioButton3);
		
		radioButton4 = new JRadioButton("");
		buttonGroup.add(radioButton4);
		radioButton4.setBounds(289, 70, 30, 25);
		contentPane.add(radioButton4);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon("src/Imagenes/puntuacion2.png"));
		label.setBounds(168, 31, 244, 34);
		contentPane.add(label);

	}

	private JButton getBuscar() {
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				puntuacion();
				
				if (!ComprobarCamposVacios()) {
					if (ConfirmarDatos()) {
						JFrame buscar = new BuscarValoracionGUI();
						buscar.setVisible(true);
					}
				}
			}
		});
		btnBuscar.setBounds(274, 114, 97, 25);
		return btnBuscar;
	}


	private boolean ComprobarCamposVacios() {
		String message = "Porfavor rellene todos los campos";

		if (puntuacion.trim().equals("")) {
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
			return true;
		} else {
			return false;
		}

	}

	private boolean ConfirmarDatos() {

		String nl = System.getProperty("line.separator");

		String message = "Porfavor compruebe que los siguientes datos son correctos:" + nl + "Puntuación: " + puntuacion + nl;

		int selection = JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);

		return selection == 0;
	}
	public void puntuacion() {
		if (radioButton1.isSelected())
			puntuacion = "1";
		if (radioButton2.isSelected())
			puntuacion = "2";
		if (radioButton3.isSelected())
			puntuacion = "3";
		if (radioButton4.isSelected())
			puntuacion = "4";
		if (radioButton5.isSelected())
			puntuacion = "5";

	}
	/**
	 * @return the puntuacion
	 */
	public static String getPuntuacion() {
		return puntuacion;
	}

	/**
	 * @param puntuacion the puntuacion to set
	 */

}
