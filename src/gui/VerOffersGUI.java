package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.ApplicationFacadeInterfaceWS;
import domain.Offer;
import domain.Owner;
import domain.RuralHouse;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JList;
import java.awt.ScrollPane;
import java.awt.Label;

public class VerOffersGUI extends JFrame {

	private JPanel contentPane;
	
	ApplicationFacadeInterfaceWS facade = MainGUI.getBusinessLogic();

	/**
	 * 
	 * 
	 * /** Create the frame.
	 */
	public VerOffersGUI(RuralHouse casa , String noches) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 566, 576);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblbltitulo = new JLabel("Estos son las offertas de la casa seleccionada:");
		lblbltitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblbltitulo.setBounds(31, 13, 312, 16);
		contentPane.add(lblbltitulo);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

		btnVolver.setBounds(238, 491, 97, 25);
		contentPane.add(btnVolver);
		Vector<Offer> vectoroffertas = facade.getOffersbyHouse(casa) ;

		
		JList list = new JList(vectoroffertas);
		list.setBounds(31, 71, 485, 357);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(31, 71, 485, 357);
		scrollPane.add(list);
		contentPane.add(scrollPane);
		
		Label label = new Label("");
		label.setBounds(348, 10, 70, 24);
		contentPane.add(label);
		label.setText(casa.getCity());
		
		JButton btnReservar = new JButton("Reservar");
		btnReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Offer of= (Offer) list.getSelectedValue();
				JFrame reserva = new ReservarCasaGUI(of,noches,casa);
				reserva.setVisible(true);
				dispose();
			}
		});
		btnReservar.setBounds(238, 444, 97, 25);
		contentPane.add(btnReservar);
		
		
		


	
	}


}
