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

public class TextoMetros extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private static String m;
	private JButton btnVolver;

	public TextoMetros() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 369, 201);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(166, 42, 129, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		contentPane.add(getBuscar());

		JLabel lblIntroduceLaMetros = new JLabel("Introduce los Metros:");
		lblIntroduceLaMetros.setBounds(36, 45, 140, 16);
		contentPane.add(lblIntroduceLaMetros);

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
				m = textField.getText();
				if (!ComprobarCamposVacios()) {
					if (ConfirmarDatos()) {
						JFrame buscar = new BuscarMetrosGUI();
						buscar.setVisible(true);
					}
				}
			}
		});
		btnBuscar.setBounds(241, 106, 97, 25);
		return btnBuscar;
	}

	/**
	 * @return the m
	 */
	public static String getm() {
		return m;
	}

	private boolean ComprobarCamposVacios() {
		String message = "Porfavor rellene todos los campos";

		if (m.trim().equals("")) {
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
			return true;
		} else {
			return false;
		}

	}

	private boolean ConfirmarDatos() {

		String nl = System.getProperty("line.separator");

		String message = "Porfavor compruebe que los siguientes datos son correctos:" + nl + "Metros: " + m + nl;

		int selection = JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);

		return selection == 0;
	}

}
