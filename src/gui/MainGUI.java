package gui;

import javax.jdo.JDODetachedFieldAccessException;

/**
 * @author Software Engineering teachers
 */

import javax.swing.*;

import configuration.ConfigXML;
import domain.Owner;
import domain.RuralHouse;

import domain.*;
import businessLogic.ApplicationFacadeInterfaceWS;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private static ActionListener ListenerLogin = null;

	private JPanel jContentPane = null;
	private JButton boton1 = null;
	private static JButton setAvailability = null;
	private static JButton queryAvailability = null;

	private static ApplicationFacadeInterfaceWS appFacadeInterface;

	public static ApplicationFacadeInterfaceWS getBusinessLogic() {
		return appFacadeInterface;
	}

	public static void setBussinessLogic(ApplicationFacadeInterfaceWS afi) {
		appFacadeInterface = afi;
	}

	protected JLabel lblNewLabel;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JMenuBar menuBar;
	private static JMenu mnLogin;
	private static JMenuItem mntmlogin;
	private static JMenuItem mntmRegistrarse;
	private static JMenuItem mntmDesconectar;
	private static Users usuario; // creada.
	private static JButton btnAadir;
	private static JButton btnVerDatos;
	private static JButton ModificarCasa;
	private static JButton btnMisReservas;
	private static JButton btnEliminarCasa;
	private JLabel lblGestinDeCasas;
	private static JButton btnBuscar;
	private static JMenu mnBusqueda;
	private static JMenuItem mntmMiPerfil;
	private static JMenuItem mntmGestionarOfertas;
	private static JButton btnValoracion;
	private static JMenuItem mntmMisReservas;
	

	/**
	 * This is the default constructor
	 */
	public MainGUI() {
		super();
		setResizable(false);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ApplicationFacadeInterfaceWS facade = MainGUI.getBusinessLogic();
				try {
					// if (ConfigXML.getInstance().isBusinessLogicLocal())
					// facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println(
							"Error: " + e1.toString() + " , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(654, 629);
		setJMenuBar(getMenuBar_1());
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getQueryAvailability());
			jContentPane.add(getSetAvailability());
			jContentPane.add(getPanel());
			jContentPane.add(getBtnAadir());
			jContentPane.add(getBtnVerDatos());
			jContentPane.add(getModificarCasa());
			jContentPane.add(getBtnMisReservas());
			jContentPane.add(getBtnEliminarCasa());
			
			JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.lblNewLabel_1.text"));
			lblNewLabel_1.setBounds(0, 332, 902, 305);
			jContentPane.add(lblNewLabel_1);
			lblNewLabel_1.setIcon(new ImageIcon("src/Imagenes/Menu.png"));
			jContentPane.add(getLblGestinDeCasas());
			jContentPane.add(getBtnBuscar());
			
			JButton btnPruebas = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnPruebas.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnPruebas.setVisible(false);
			btnPruebas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ApplicationFacadeInterfaceWS facade= getBusinessLogic();
					JFrame ir= new TextoPuntuacion();
					ir.setVisible(true);
				}
			});
			btnPruebas.setBounds(25, 272, 97, 25);
			jContentPane.add(btnPruebas);
			jContentPane.add(getBtnValoracion());
		}
		return jContentPane;
	}

	// -----------------------
	public static void setModoRegistro() {

		if (getUsuario() instanceof Owner) {
			Owner o = (Owner) getUsuario();
			mntmDesconectar.setEnabled(true);
			mntmlogin.setEnabled(false);
			mntmRegistrarse.setEnabled(false);
			setAvailability.setVisible(true);
			queryAvailability.setVisible(false);
			btnAadir.setVisible(true);
			btnVerDatos.setVisible(true);
			btnMisReservas.setVisible(false);
			ModificarCasa.setVisible(true);
			btnEliminarCasa.setVisible(true);
			btnBuscar.setVisible(false);
			mnBusqueda.setEnabled(false);
			mntmMiPerfil.setEnabled(true);
			mntmGestionarOfertas.setEnabled(true);
			btnValoracion.setVisible(false);
			mntmMisReservas.setEnabled(false);
			

		} else {
			Client c = (Client) getUsuario();
			mntmDesconectar.setEnabled(true);
			mntmlogin.setEnabled(false);
			mntmRegistrarse.setEnabled(false);
			setAvailability.setVisible(false);
			queryAvailability.setVisible(true);
			btnAadir.setVisible(false);
			btnVerDatos.setVisible(true);
			btnMisReservas.setVisible(true);
			ModificarCasa.setVisible(false);
			btnEliminarCasa.setVisible(false);
			btnBuscar.setVisible(true);
			mnBusqueda.setEnabled(true);
			mntmMiPerfil.setEnabled(true);
			mntmGestionarOfertas.setEnabled(false);
			btnValoracion.setVisible(true);
			mntmMisReservas.setEnabled(true);

		}

	}

	// ----------

	public static void aLoBruto() {
// esta funcion sirve para hacer log out.
		
		mntmRegistrarse.setEnabled(true);
		setAvailability.setVisible(false);
		queryAvailability.setVisible(true);
		btnAadir.setVisible(false); 
		btnVerDatos.setVisible(true);
		mntmDesconectar.setEnabled(false);
		mntmlogin.setEnabled(true);
		btnMisReservas.setVisible(false);
		ModificarCasa.setVisible(false);
		btnEliminarCasa.setVisible(false);
		btnBuscar.setVisible(true);
		mnBusqueda.setEnabled(true);
		mntmMiPerfil.setEnabled(false);
		mntmGestionarOfertas.setEnabled(false);
		btnValoracion.setVisible(false);
		mntmMisReservas.setEnabled(false);

		

	}

	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getSetAvailability() {
		if (setAvailability == null) {
			setAvailability = new JButton();
			setAvailability.setBounds(224, 208, 203, 34);
			setAvailability.setText(ResourceBundle.getBundle("Etiquetas").getString("SetAvailability"));
			setAvailability.setVisible(false);
			setAvailability.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ApplicationFacadeInterfaceWS facade = MainGUI.getBusinessLogic();
					Vector<RuralHouse> rhs = facade.getRuralHousesByOwner();
					JFrame a = new SetAvailabilityGUI(rhs);
					a.setVisible(true);
				}
			});
		}
		return setAvailability;
	}

	/**
	 * This method initializes boton3
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getQueryAvailability() {
		if (queryAvailability == null) {
			queryAvailability = new JButton();
			queryAvailability.setBounds(224, 158, 203, 34);
			queryAvailability.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryAvailability"));
			queryAvailability.setVisible(true);
			queryAvailability.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new QueryAvailabilityGUI();

					a.setVisible(true);
				}
			});
		}
		return queryAvailability;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			lblNewLabel.setBounds(0, 73, 634, 38);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel.setForeground(Color.BLACK);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel;
	}

	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("English");
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: " + Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton);
		}
		return rdbtnNewRadioButton;
	}

	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: " + Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton_1);
		}
		return rdbtnNewRadioButton_1;
	}

	private JRadioButton getRdbtnNewRadioButton_2() {
		if (rdbtnNewRadioButton_2 == null) {
			rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
			rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: " + Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton_2);
		}
		return rdbtnNewRadioButton_2;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setVisible(false);
			panel.setBounds(32, 412, 634, 82);
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}

	private void redibujar() {
		lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		queryAvailability.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryAvailability"));
		setAvailability.setText(ResourceBundle.getBundle("Etiquetas").getString("SetAvailability"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnLogin());
			menuBar.add(getMnBusqueda());
			
			JMenu mnOfertas = new JMenu(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.mnOfertas.text")); //$NON-NLS-1$ //$NON-NLS-2$
			menuBar.add(mnOfertas);
			mnOfertas.add(getMntmGestionarOfertas());
			
			JMenu mnReservas = new JMenu(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.mnReservas.text"));
			menuBar.add(mnReservas);
			mnReservas.add(getMntmMisReservas());
		}
		return menuBar;
	}

	private JMenu getMnLogin() {
		if (mnLogin == null) {
			mnLogin = new JMenu(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.mnLogin.text_2")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-2$
			mnLogin.add(getMntmlogin());
			mnLogin.add(getMntmRegistrarse());

			mntmDesconectar = new JMenuItem(
					ResourceBundle.getBundle("Etiquetas").getString("MainGUI.mntmDesconectar.text"));
			mntmDesconectar.setEnabled(false); // por 

			mntmDesconectar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setUsuario(null);

					JOptionPane.showMessageDialog(null, "Sesión cerrada", "", JOptionPane.PLAIN_MESSAGE);

					mntmlogin.removeActionListener(ListenerLogin);

					aLoBruto();
				}
			});
			mnLogin.add(getMntmMiPerfil());
			mnLogin.add(mntmDesconectar);

		}
		return mnLogin;
	}

	private JMenuItem getMntmlogin() {
		if (mntmlogin == null) {
			mntmlogin = new JMenuItem(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.mntmRegistro.text")); //$NON-NLS-1$ //$NON-NLS-2$
			mntmlogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFrame entrar = new LoginGUI();
					entrar.setVisible(true);
				}
			});
		}
		return mntmlogin;
	}

	private JMenuItem getMntmRegistrarse() {
		if (mntmRegistrarse == null) {
			mntmRegistrarse = new JMenuItem(
					ResourceBundle.getBundle("Etiquetas").getString("MainGUI.mntmRegistrarse.text")); //$NON-NLS-1$ //$NON-NLS-2$
			mntmRegistrarse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame registro = new RegistroGUI();
					registro.setVisible(true);
				}
			});
		}
		return mntmRegistrarse;
	}
	// ----------------------------------------------------

	// ----------------metodos de obtencion-----------------------

	public static Users getUsuario() {
		return usuario;
	}
	
	/**
	 * @param usuari
	 *            the usuario to set
	 */
	public static void setUsuario(Users usuario) {
		MainGUI.usuario = usuario;
	}

	// ----------------------------------------

	private JButton getBtnAadir() {
		if (btnAadir == null) {
			btnAadir = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnAadir.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnAadir.setBounds(224, 158, 203, 34);
			btnAadir.setVisible(false);
			btnAadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame crearcasa = new crearCasaGUI(false, null);
					crearcasa.setVisible(true);
				}
			});
		}
		return btnAadir;
	}

	private JButton getBtnVerDatos() {
		if (btnVerDatos == null) {
			btnVerDatos = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnVerDatos.setBounds(224, 111, 203, 34);
			btnVerDatos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame ver = new VerDatosCasaMenuGUI(); 
					ver.setVisible(true);
				}
			});
		}
		return btnVerDatos;
	}
	private JButton getModificarCasa() {
		if (ModificarCasa == null) {
			ModificarCasa = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnNewButton.text_1")); //$NON-NLS-1$ //$NON-NLS-2$
			ModificarCasa.setBounds(224, 302, 203, 34);
			ModificarCasa.setVisible(false);
			ModificarCasa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ApplicationFacadeInterfaceWS facade= getBusinessLogic();
					Vector<RuralHouse> rhlista=null;
					//boolean vacia= true;
					try{
						if(getUsuario() instanceof Owner){
							Owner owner = (Owner) getUsuario();	
							rhlista = facade.getRuralHousesByOwner();
							System.out.println(rhlista);
						}
					}catch (Exception e) {
						e.printStackTrace();
					}
					 
					if(rhlista.isEmpty() !=true ){
						JFrame modificar = new ModificarCasaGUI(rhlista);
						modificar.setVisible(true);					
					} else if (rhlista.isEmpty() == true){
						JOptionPane.showMessageDialog(null,
							"Debes añadir una casa primero", "Error",JOptionPane.PLAIN_MESSAGE);
						System.out.println(rhlista);
					}
				}
			});
		}
		return ModificarCasa;
	}
	private JButton getBtnMisReservas() {
		if (btnMisReservas == null) {
			btnMisReservas = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnNewButton.text_2")); //$NON-NLS-1$ //$NON-NLS-2$
			btnMisReservas.setBounds(224, 302, 203, 34);
			btnMisReservas.setVisible(false);
			btnMisReservas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ApplicationFacadeInterfaceWS facade= getBusinessLogic();
					Vector<Reserva> rsvlista=null;
					//boolean vacia= true;
					try{
						if(getUsuario() instanceof Client){
							Client client = (Client) getUsuario();	
							rsvlista = facade.getReservasByClient();
							System.out.println(rsvlista);
						}
					}catch (Exception e) {
						e.printStackTrace();
					}
					 
					if(rsvlista.isEmpty() !=true ){
						JFrame reservas = new MisReservasGUI(rsvlista,getUsuario());
						reservas.setVisible(true);					
					} else if (rsvlista.isEmpty() == true){
						JOptionPane.showMessageDialog(null,
							"Debes añadir una Reserva primero", "Error",JOptionPane.PLAIN_MESSAGE);
						System.out.println(rsvlista);
					}
				}
			});
		}
		return btnMisReservas;
	}

	private JButton getBtnEliminarCasa() {
		if (btnEliminarCasa == null) {
			btnEliminarCasa = new JButton(
					ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnNewButton.text_3")); //$NON-NLS-1$ //$NON-NLS-2$
			btnEliminarCasa.setBounds(224, 255, 203, 34);
			btnEliminarCasa.setVisible(false);
			btnEliminarCasa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ApplicationFacadeInterfaceWS facade = getBusinessLogic();
					Vector<RuralHouse> rhlista = null;
					try {
						if (getUsuario() instanceof Owner) {
							Owner owner = (Owner) getUsuario();
							rhlista = facade.getRuralHousesByOwner();
							System.out.println(rhlista);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					if (rhlista.isEmpty() != true) {
						JFrame eliminar = new EliminarCasaGUI(rhlista);
						eliminar.setVisible(true);
					} else if (rhlista.isEmpty() == true) {
						JOptionPane.showMessageDialog(null, "Debes añadir una casa primero", "Error",
								JOptionPane.PLAIN_MESSAGE);
						System.out.println(rhlista);
					}
				}
			});
		}
		return btnEliminarCasa;
	}
	private JLabel getLblGestinDeCasas() {
		if (lblGestinDeCasas == null) {
			lblGestinDeCasas = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.lblGestinDeCasas.text")); //$NON-NLS-1$ //$NON-NLS-2$
			lblGestinDeCasas.setFont(new Font("Tahoma", Font.PLAIN, 30));
			lblGestinDeCasas.setBounds(73, 24, 579, 36);
		}
		return lblGestinDeCasas;
	}
	private JButton getBtnBuscar() {
		if (btnBuscar == null) {
			btnBuscar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnBuscar.text_1")); //$NON-NLS-1$ //$NON-NLS-2$
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame bus = new BuscarGUI();
					bus.setVisible(true);
				}
			});
			btnBuscar.setBounds(224, 208, 203, 34);
		}
		return btnBuscar;
	}
	private JMenu getMnBusqueda() {
		if (mnBusqueda == null) {
			mnBusqueda = new JMenu(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.mnBusqueda.text_1")); //$NON-NLS-1$ //$NON-NLS-2$
			
			JMenuItem mntmPorCiudad = new JMenuItem(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.mntmPorCiudad.text")); //$NON-NLS-1$ //$NON-NLS-2$
			mntmPorCiudad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame bus = new TextoCiudad();
					bus.setVisible(true);
					
				}
			});
			mnBusqueda.add(mntmPorCiudad);
			
			JMenuItem mntmDireccin = new JMenuItem(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.mntmDireccin.text")); //$NON-NLS-1$ //$NON-NLS-2$
			mntmDireccin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame bus = new TextoDireccion();
					bus.setVisible(true);
				}
			});
			mnBusqueda.add(mntmDireccin);
			
			JMenuItem mntmNhabitaciones = new JMenuItem(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.mntmNhabitaciones.text")); //$NON-NLS-1$ //$NON-NLS-2$
			mntmNhabitaciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame bus = new TextoHabitaciones();
					bus.setVisible(true);
				}
			});
			mnBusqueda.add(mntmNhabitaciones);
			
			JMenuItem mntmMetrosCuadrados = new JMenuItem(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.mntmMetrosCuadrados.text")); //$NON-NLS-1$ //$NON-NLS-2$
			mntmMetrosCuadrados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame bus = new TextoMetros();
					bus.setVisible(true);
				}
			});
			mnBusqueda.add(mntmMetrosCuadrados);
			
			JMenuItem mntmDescripcin = new JMenuItem(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.mntmDescripcin.text")); //$NON-NLS-1$ //$NON-NLS-2$
			mntmDescripcin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame bus = new TextoDescripcion();
					bus.setVisible(true);
				}
			});
			mnBusqueda.add(mntmDescripcin);
			
			JMenuItem mntmPuntuacin = new JMenuItem(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.mntmPuntuacin.text")); //$NON-NLS-1$ //$NON-NLS-2$
			mntmPuntuacin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFrame puntos = new TextoPuntuacion();
					puntos.setVisible(true);
				}
			});
			mnBusqueda.add(mntmPuntuacin);
		}
		return mnBusqueda;
	}
	private JMenuItem getMntmMiPerfil() {
		if (mntmMiPerfil == null) {
			mntmMiPerfil = new JMenuItem(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.mntmMiPerfil.text")); //$NON-NLS-1$ //$NON-NLS-2$
			mntmMiPerfil.setEnabled(false);
			mntmMiPerfil.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFrame ir = new ModificarUsuarioGUI(getUsuario());
							ir.setVisible(true);
				}
			});
		}
		return mntmMiPerfil;
	}
	private JMenuItem getMntmGestionarOfertas() {
		if (mntmGestionarOfertas == null) {
			mntmGestionarOfertas = new JMenuItem(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.mntmGestionarOfertas.text")); //$NON-NLS-1$ //$NON-NLS-2$
			mntmGestionarOfertas.setEnabled(false);
			mntmGestionarOfertas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFrame borrarof = new GestionarOfferMenu();
					borrarof.setVisible(true);
				}
			});
		}
		return mntmGestionarOfertas;
	}
	private JButton getBtnValoracion() {
		if (btnValoracion == null) {
			btnValoracion = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnNewButton.text_4")); //$NON-NLS-1$ //$NON-NLS-2$		
			btnValoracion.setBounds(224, 255, 203, 34);
			btnValoracion.setVisible(false);
			btnValoracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFrame ver = new ValorarMenuGUI(); 
					ver.setVisible(true);
				}
			});
		}
		
		return btnValoracion;
	}
	private JMenuItem getMntmMisReservas() {
		if (mntmMisReservas == null) {
			mntmMisReservas = new JMenuItem(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.mntmMisReservas.text")); //$NON-NLS-1$ //$NON-NLS-2$
			mntmMisReservas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ApplicationFacadeInterfaceWS facade= getBusinessLogic();
					Vector<Reserva> rsvlista=null;
					//boolean vacia= true;
					try{
						if(getUsuario() instanceof Client){
							Client client = (Client) getUsuario();	
							rsvlista = facade.getReservasByClient();
							System.out.println(rsvlista);
						}
					}catch (Exception e) {
						e.printStackTrace();
					}
					 
					if(rsvlista.isEmpty() !=true ){
						JFrame reservas = new MisReservasGUI(rsvlista,getUsuario());
						reservas.setVisible(true);					
					} else if (rsvlista.isEmpty() == true){
						JOptionPane.showMessageDialog(null,
							"Debes añadir una Reserva primero", "Error",JOptionPane.PLAIN_MESSAGE);
						System.out.println(rsvlista);
					}
				}
			});
			
			mntmMisReservas.setEnabled(false);
		}
		return mntmMisReservas;
	}
} // @jve:decl-index=0:visual-constraint="0,0"
