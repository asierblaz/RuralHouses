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

public class TextoDescripcion extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private static String description;
	private JButton btnVolver;

	public TextoDescripcion() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 460, 239);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(166, 42, 225, 94);
		contentPane.add(textField);
		textField.setColumns(10);
		contentPane.add(getBuscar());

		JLabel lblIntroduceLaDescripcion = new JLabel("Introduce la Descripcion:");
		lblIntroduceLaDescripcion.setBounds(36, 13, 181, 16);
		contentPane.add(lblIntroduceLaDescripcion);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnVolver.setBounds(36, 154, 97, 25);
		contentPane.add(btnVolver);

	}

	private JButton getBuscar() {
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				description = textField.getText();
				if (!ComprobarCamposVacios()) {
					if (ConfirmarDatos()) {
						JFrame buscar = new BuscarDescripcionGUI();
						buscar.setVisible(true);
					}
				}
			}
		});
		btnBuscar.setBounds(333, 154, 97, 25);
		return btnBuscar;
	}

	/**
	 * @return the description
	 */
	public static String getdescription() {
		return description;
	}

	private boolean ComprobarCamposVacios() {
		String message = "Porfavor rellene todos los campos";

		if (description.trim().equals("")) {
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
			return true;
		} else {
			return false;
		}

	}

	private boolean ConfirmarDatos() {

		String nl = System.getProperty("line.separator");

		String message = "Porfavor compruebe que los siguientes datos son correctos:" + nl + "Descripcion: " + description + nl;

		int selection = JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);

		return selection == 0;
	}

}
