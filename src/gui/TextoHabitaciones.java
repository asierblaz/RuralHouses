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

public class TextoHabitaciones extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private static String habita;
	private JButton btnVolver;

	public TextoHabitaciones() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 369, 201);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(241, 42, 38, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		contentPane.add(getBuscar());

		JLabel lblIntroduceLaCiudad = new JLabel("Introduce el numero de habitaciones:");
		lblIntroduceLaCiudad.setBounds(12, 45, 224, 16);
		contentPane.add(lblIntroduceLaCiudad);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnVolver.setBounds(36, 106, 97, 25);
		contentPane.add(btnVolver);

	}

	private JButton getBuscar() {
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				habita = textField.getText();
				if (!ComprobarCamposVacios()) {
					if (ConfirmarDatos()) {
						JFrame buscar = new BuscarHabitacionesGUI();
						buscar.setVisible(true);
					}
				}
			}
		});
		btnBuscar.setBounds(241, 106, 97, 25);
		return btnBuscar;
	}

	/**
	 * @return the habita
	 */
	public static String gethabita() {
		return habita;
	}

	private boolean ComprobarCamposVacios() {
		String message = "Porfavor rellene todos los campos";

		if (habita.trim().equals("")) {
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
			return true;
		} else {
			return false;
		}

	}

	private boolean ConfirmarDatos() {

		String nl = System.getProperty("line.separator");

		String message = "Porfavor compruebe que los siguientes datos son correctos:" + nl + "Numero de Habitaciones: " + habita + nl;

		int selection = JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);

		return selection == 0;
	}

}
