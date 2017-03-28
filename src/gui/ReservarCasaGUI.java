package gui;

import businessLogic.*;
import com.toedter.calendar.*;
import domain.*;
import exceptions.OfferCanNotBeBooked;

import java.beans.*;
import java.util.Calendar;
import java.util.Date;
import java.text.*;
import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class ReservarCasaGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	public static final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;

	private JLabel jLabel1 = new JLabel();
	private JComboBox comboBoxRh;
	Vector<RuralHouse> ruralHouses;
	private JLabel jLabel2 = new JLabel();
	private JLabel jLabel3 = new JLabel();
	private JLabel jLabel4 = new JLabel();
	private JTextField jTextField2 = new JTextField();
	private JTextField jTextFieldNumNoches = new JTextField();
	private JTextField jTextFieldTelefono = new JTextField();
	private JButton jButtonAceptar = new JButton();
	private JButton jButtonVolver = new JButton();

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarMio = null;
	private JLabel jLabel5 = new JLabel();

	public ReservarCasaGUI() {
		setResizable(false);
		try {
			jbInit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ReservarCasaGUI(int houseNumber, Date PrimerDia, Date ultimaNoche) {
		try {
			jbInit();

			for (int i = 0; i < ruralHouses.size(); i++) {
				if (((RuralHouse) ruralHouses.get(i)).getHouseNumber() == houseNumber) {
					comboBoxRh.setSelectedIndex(i);
					break;
				}
			}

			Calendar c = new GregorianCalendar();
			c.setTime(PrimerDia);
			jCalendar1.setCalendar(c);

			long numberOfDays = (long) (ultimaNoche.getTime() - PrimerDia.getTime())
					/ MILLSECS_PER_DAY;
			jTextFieldNumNoches.setText(Long.toString(numberOfDays));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//empieza aqui 
	private void jbInit() throws Exception {
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(410, 413));
		this.setTitle("Reservar Casa Rural");
		jLabel1.setText("Seleccione casa rural:");
		ApplicationFacadeInterfaceWS facade= MainGUI.getBusinessLogic();
		ruralHouses = facade.getAllRuralHouses();

		comboBoxRh = new JComboBox(ruralHouses);

		jLabel1.setBounds(new Rectangle(15, 10, 140, 20));
		comboBoxRh.setBounds(new Rectangle(167, 10, 175, 20));

		jTextFieldNumNoches.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
			}

			public void focusLost(FocusEvent e) {
				jTextField3_focusLost();
			}
		});
		jTextFieldTelefono.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
			}

			public void focusLost(FocusEvent e) {
				jTextField4_focusLost();
			}
		});
		jLabel2.setText("Primer dia:");
		jLabel2.setBounds(new Rectangle(15, 50, 115, 20));
		jLabel3.setText("Numero de noches");
		jLabel3.setBounds(new Rectangle(15, 240, 115, 20));
		jLabel4.setText("Tel\u00E9fono de contacto: ");
		jLabel4.setBounds(new Rectangle(15, 270, 140, 20));
		jTextField2.setBounds(new Rectangle(155, 205, 140, 20));
		jTextField2.setEditable(false);
		jTextFieldNumNoches.setBounds(new Rectangle(155, 240, 140, 20));
		jTextFieldNumNoches.setText("0");
		jTextFieldTelefono.setBounds(new Rectangle(155, 270, 140, 20));
		jTextFieldTelefono.setText("0");
		jButtonAceptar.setText("Aceptar");
		jButtonAceptar.setBounds(new Rectangle(50, 345, 130, 30));
		jButtonAceptar.setSize(new Dimension(130, 30));
		jButtonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// codigo casa
				RuralHouse casaRural = (RuralHouse) comboBoxRh.getSelectedItem();
				// primer Dia
				Date PrimerDia = trim(new Date(jCalendar1.getCalendar()
						.getTime().getTime()));

				System.out.println("PrimerDia=" + PrimerDia);
				
				// Numero de noches en milisegundos
				long noche = 1000 * 60 * 60 * 24
						* Integer.parseInt(jTextFieldNumNoches.getText());
				Date ultimaNoche = new Date((long) (PrimerDia.getTime() + noche));
				// telefono contact
				System.out.println("ultimaNoche=" + ultimaNoche);

				String telefono = jTextFieldTelefono.getText();
				try {

					// Obtain the business logic from a StartWindow class (local
					// or remote)
					ApplicationFacadeInterfaceWS facade = MainGUI.getBusinessLogic();
					Users u = MainGUI.getUsuario();
				
					if (u != null) {
						boolean book = true;// falso
					/*	Booking book = facade.createBooking(casaRural, PrimerDia,
								ultimaNoche, telefono, MainGUI.getUsuario());*/
						System.out.println("funciona");
						if (book /*!= null*/) {
							/*BookRuralHouseConfirmationWindow confirmWindow = new BookRuralHouseConfirmationWindow(
									book);
							confirmWindow.setVisible(true);
							jLabel5.setText("Booking made");*/
							System.out.println("funciona dos");

						} else
							jLabel5.setText("No hay ofertas disponibles para esos dias");
					}else{
						jLabel5.setText("Error: tu tienes que estar registrado");
					}
				} catch (/*OfferCanNotBeBooked*/ Exception  e1) {
					jLabel5.setText("Error: It is not possible to book");
					JFrame a = new QueryAvailabilityGUI();
					a.setVisible(true);

				/*} catch (Exception e1) {

					e1.printStackTrace();*/
				}
			}
		});
		jButtonVolver.setText("Volver");
		jButtonVolver.setBounds(new Rectangle(220, 345, 130, 30));
		jButtonVolver.setSize(new Dimension(130, 30));
		jButtonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton3_actionPerformed(e);
			}
		});
		jLabel5.setBounds(new Rectangle(50, 310, 300, 20));
		jLabel5.setForeground(Color.red);
		jCalendar1.setBounds(new Rectangle(155, 50, 235, 145));
		this.getContentPane().add(jCalendar1, null);
		this.getContentPane().add(jLabel5, null);
		this.getContentPane().add(jButtonVolver, null);
		this.getContentPane().add(jButtonAceptar, null);
		this.getContentPane().add(jTextFieldTelefono, null);
		this.getContentPane().add(jTextFieldNumNoches, null);
		this.getContentPane().add(jTextField2, null);
		this.getContentPane().add(jLabel4, null);
		this.getContentPane().add(jLabel3, null);
		this.getContentPane().add(jLabel2, null);
		this.getContentPane().add(comboBoxRh, null);
		this.getContentPane().add(jLabel1, null);

		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar1.setLocale((Locale) propertychangeevent
							.getNewValue());
					DateFormat dateformat = DateFormat.getDateInstance(1,
							jCalendar1.getLocale());
					jTextField2.setText(dateformat.format(calendarMio.getTime()));

				} else if (propertychangeevent.getPropertyName().equals(
						"calendar")) {
					calendarMio = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1,
							jCalendar1.getLocale());
					jTextField2.setText(dateformat1.format(calendarMio
							.getTime()));
					jCalendar1.setCalendar(calendarMio);
				}
			}
		});
	}

	private Date trim(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		return calendar.getTime();
	}

	private void jButton3_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

	public void setConfirmBooking(boolean b) {
		if (b)
			jLabel5.setText("Booking made");
	}

	private void jTextField3_focusLost() {
		try {
			new Integer(jTextFieldNumNoches.getText());
			jLabel5.setText("");
		} catch (NumberFormatException ex) {
			jLabel5.setText("Error: Introduce a number");
		}
	}

	private void jTextField4_focusLost() {
		try {
			new Integer(jTextFieldTelefono.getText());
			jLabel5.setText("");
		} catch (NumberFormatException ex) {
			jLabel5.setText("Error: Introduce a number");
		}
	}
}