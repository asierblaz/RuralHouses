package businessLogic;

import java.io.File;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.swing.JOptionPane;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.*;
//import domain.Booking;

import exceptions.*;

//Service Implementation
@WebService(endpointInterface = "businessLogic.ApplicationFacadeInterfaceWS")
public class FacadeImplementationWS implements ApplicationFacadeInterfaceWS {

	/**
	 * 
	 */
	Vector<Owner> owners;
	Vector<RuralHouse> ruralHouses;
	Owner owner;
	
	public FacadeImplementationWS() {
		ConfigXML c = ConfigXML.getInstance();
		if (c.getDataBaseOpenMode().equals("initialize")) {
			DataAccess dbManager = new DataAccess();
			dbManager.initializeDB();
			dbManager.close();
		}

	}

	/**
	 * This method creates an offer with a house number, first day, last day and
	 * price
	 * 
	 * @param House
	 *            number, start day, last day and price
	 * @return the created offer, or null, or an exception
	 */
	public Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay, float price)
			throws OverlappingOfferExists, BadDates {
		System.out.println(">> FacadeImplementationWS: createOffer=> ruralHouse= " + ruralHouse + " firstDay= "
				+ firstDay + " lastDay=" + lastDay + " price=" + price);

		DataAccess dbManager = new DataAccess();
		Offer o = null;
		if (firstDay.compareTo(lastDay) >= 0) {
			dbManager.close();
			throw new BadDates();
		}

		boolean b = dbManager.existsOverlappingOffer(ruralHouse, firstDay, lastDay);
		if (!b)
			o = dbManager.createOffer(ruralHouse, firstDay, lastDay, price);

		dbManager.close();
		System.out.println("<< FacadeImplementationWS: createOffer=> O= " + o);
		return o;
	}

	// -----------------------------crear
	// cliente----------------------------------------
	public Client crearCliente(String nombre, String usuario, String pass, String cuenta)
			throws RemoteException, Exception {
		System.out.println(">> FacadeImplementationWS: crearCliente=> Nombre= " + nombre + " Usuario= " + usuario
				+ " Contraseña=" + pass + " Cuenta Bancaria=" + cuenta);
		DataAccess dbManager = new DataAccess();
		Client c = null;

		boolean b = dbManager.existsOvelappingUsers(usuario); // user
		System.out.println(b);
		if (!b)
			c = dbManager.crearCliente(nombre, usuario, pass, cuenta);
		else {
			String message = "El nombre de  Usuario: " + usuario + " esta ocupado!";
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
		}

		dbManager.close();
		System.out.println("<< FacadeImplementationWS: crearCliente=> c= " + c);
		return c;

	}

	// ----------------------------crear
	// owner-----------------------------------------------
	public Owner crearOwner(String nombre, String usuario, String pass, String cuenta)
			throws RemoteException, Exception {
		System.out.println(">> FacadeImplementationWS: crearOwner=> Nombre= " + nombre + " Usuario= " + usuario
				+ " Contraseña=" + pass + " Cuenta Bancaria=" + cuenta);
		DataAccess dbManager = new DataAccess();
		Owner o = null;

		boolean b = dbManager.existsOvelappingUsers(usuario); // user
		System.out.println(b);
		if (!b)
			o = dbManager.crearOwner(nombre, usuario, pass, cuenta);
		else {
			String message = "El nombre de  Usuario: " + usuario + " esta ocupado!";
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);

		}
		dbManager.close();
		System.out.println("<< FacadeImplementationWS: crearOwner=> o= " + o);
		return o;

	}

	// ---------------------comprobar
	// usuario-----------------------------------------------
	@Override
	public Users comprobarUsuario(String usuario, String pass) throws RemoteException, Exception {
		DataAccess dbManager = new DataAccess();
		return dbManager.comprobarUsuario(usuario, pass);
	}

	// -----------------------------crear casa
	// rural-----------------------------------------
	public RuralHouse crearRuralHouse(String description, String city,String direccion, String m2,String numHabitaciones, Owner owner) throws RemoteException, Exception {
		System.out.println(">> FacadeImplementationWS: crearRuralHouse=> Ciudad= " + city + " Descripción="
				+ description + "Owner= " + owner);

		DataAccess dbManager = new DataAccess();
		RuralHouse rh = dbManager.crearRuralHouse( description, city, direccion, numHabitaciones, m2, owner);
		owner.anadirCasaRural(rh.getDescription(), rh.getCity(), rh.getDireccion(), rh.getM2(), rh.getNumHabitaciones(),rh.getOwner());
		dbManager.close();
		System.out.println("<< FacadeImplementationWS: crearRuralHouse=> rh= " + rh);

		return rh;
	}
	

	// ---------------------------------Actualizar Casa Rural------------------
	public boolean actualizarRuralHouse(RuralHouse rh, String description, String city,String direccion,String numHabitaciones,String m2)
			throws RemoteException, Exception {
		System.out.println(">> FacadeImplementationWS: crearRuralHouse=> Ciudad= " + city + " Descripción="
				+ description + "rural House" + rh);

		DataAccess dbManager = new DataAccess();
		Boolean b = dbManager.actualizarRuralHouse(rh, description, city, direccion, numHabitaciones, m2);
		System.out.println("Actualizar rh= " + rh + "descripcion" + description);
		dbManager.close();

		return b;

	}

	// -----------------------------------------------
	public Vector<RuralHouse> getAllRuralHouses() {
		System.out.println(">> FacadeImplementationWS: getAllRuralHouses");

		DataAccess dbManager = new DataAccess();

		Vector<RuralHouse> ruralHouses = dbManager.getAllRuralHouses();
		dbManager.close();
		System.out.println("<< FacadeImplementationWS:: getAllRuralHouses");

		return ruralHouses;

	}
//---------------
	public Vector<RuralHouse> getRuralHousesByOwner() {
		System.out.println(">> FacadeImplementationWS: getRuralHousesByOwner");

		DataAccess dbManager = new DataAccess();

		Vector<RuralHouse> ruralHouses = dbManager.getRuralHouseByOwner();
		dbManager.close();
		System.out.println("<< FacadeImplementationWS:: getRuralHousesByOwner");

		return ruralHouses;
	}
	
	//-------------------------------
	public Vector<RuralHouse> getRuralHouseByCiudad(String ciudad){
		System.out.println(">> FacadeImplementationWS: getRuralHousesByciudad");

		DataAccess dbManager = new DataAccess();

		Vector<RuralHouse> ruralHouses = dbManager.getRuralHouseByCiudad(ciudad);
		dbManager.close();
		System.out.println("<< FacadeImplementationWS:: getRuralHousesByOwner");

		return ruralHouses;
	}
	// --------------------------reservar casa----------------------
	public Reserva reservarCasa(RuralHouse rh, Date primerDia, Date ultimaNoche,
			String telefono, Users u) throws CasaNoReservada{
		
		owners=null;
		ruralHouses= null;
		
		DataAccess dbManager = new DataAccess();
		//return dbManager.actualizarRuralHouse(rh, description, city);

		 
		Reserva r = null;
		return r;
	}
	//---------------------------eliminar casa-----------------------
	public void BorrarCasa (RuralHouse rh) throws RemoteException, Exception{
		
		DataAccess dbManager = new DataAccess();
		boolean b= 	dbManager.BorrarCasa(rh);
		if (b){
			System.out.println("casa borrada correctamente");
			String a= "La casa en "+ rh.getCity()+"  ha sido borrada con exito!";
			JOptionPane.showMessageDialog(null, a, "Bien!", JOptionPane.INFORMATION_MESSAGE);
		}		else {
			String message = "La casa en " + rh.getCity()+ "no ha sido borrada";
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);}
			System.out.println("casa no borrada");
		}

	// -----------------------------------------------
	/**
	 * This method obtains the offers of a ruralHouse in the provided dates
	 * 
	 * @param ruralHouse,
	 *            the ruralHouse to inspect
	 * @param firstDay,
	 *            first day in a period range
	 * @param lastDay,
	 *            last day in a period range
	 * @return the first offer that overlaps with those dates, or null if there
	 *         is no overlapping offer
	 */

	@WebMethod
	public Vector<Offer> getOffers(RuralHouse rh, Date firstDay, Date lastDay) {

		DataAccess dbManager = new DataAccess();
		Vector<Offer> offers = new Vector<Offer>();
		offers = dbManager.getOffers(rh, firstDay, lastDay);
		dbManager.close();

		return offers;
	}

	public void close() {
		DataAccess dbManager = new DataAccess();

		dbManager.close();

	}

	public void initializeBD() {

		DataAccess dbManager = new DataAccess();
		dbManager.initializeDB();
		dbManager.close();

	}

}
