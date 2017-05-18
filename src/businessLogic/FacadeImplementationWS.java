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
import javax.xml.crypto.Data;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.*;
//import domain.Booking;

import exceptions.*;
import gui.MainGUI;

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
	//-----------------------crear valoracion --------------------------------------
		public Valoracion crearValoracion(RuralHouse ruralHouse, String comentario, String puntuacion, String nombre)throws RemoteException, Exception {
			DataAccess dbManager = new DataAccess();
			Valoracion v =dbManager.crearValoracion(ruralHouse, comentario, puntuacion, nombre);
			dbManager.close();

			float puntos = Float.parseFloat(puntuacion);
			System.out.println("puntos"+puntos);
			System.out.println("puntos total"+ruralHouse.getPuntuaciontotal());
			float puntuaciontotal = ruralHouse.getPuntuaciontotal()+puntos;
		
			System.out.println("puntos total tras suma"+puntuaciontotal);
			int cont =ruralHouse.getCont() ;
			System.out.println("contador"+ cont);
			float medianueva = (puntuaciontotal)/cont;
			System.out.println("la media es="+medianueva);
			cont++;
			ApplicationFacadeInterfaceWS facade = MainGUI.getBusinessLogic();
			
			facade.actualizarMedia(ruralHouse, medianueva, puntuaciontotal, cont);
			return v;
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
	//------------actualizar media-------------
	public void actualizarMedia(RuralHouse rh,float media, float puntuaciontotal,int cont){
		System.out.println("actualizar media facade");
		DataAccess dbManager = new DataAccess();
		dbManager.actualizarMedia(rh, media, puntuaciontotal, cont);
	}
	
	//-----------------Actualizar usuario----------------
	public boolean actualizarUsuario (Users u, String nombre, String usuario, String pass, String cuenta)throws RemoteException, Exception {
		
		DataAccess dbManager = new DataAccess();
		Boolean b= dbManager.actualizarUsuario(u, nombre, usuario, pass, cuenta);
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
		System.out.println("<< FacadeImplementationWS:: getRuralHousesByciudad");

		return ruralHouses;
	}
	//-------------------------------
		public Vector<RuralHouse> getRuralHouseByDescripcion(String descripcion){
			System.out.println(">> FacadeImplementationWS: getRuralHousesByDescripcion");

			DataAccess dbManager = new DataAccess();

			Vector<RuralHouse> ruralHouses = dbManager.getRuralHouseByDescripcion(descripcion);
			dbManager.close();
			System.out.println("<< FacadeImplementationWS:: getRuralHousesByDescription");

			return ruralHouses;
		}

	// -------------------------------
		public Vector<RuralHouse> getRuralHouseByDireccion(String direccion) {
		System.out.println(">> FacadeImplementationWS: getRuralHousesByDireccion");

		DataAccess dbManager = new DataAccess();

		Vector<RuralHouse> ruralHouses = dbManager.getRuralHouseByDireccion(direccion);
		dbManager.close();
		System.out.println("<< FacadeImplementationWS:: getRuralHousesByDireccion");

		return ruralHouses;
	}
		// -------------------------------
		public Vector<RuralHouse> getRuralHouseByHabitaciones(String habitaciones) {
		System.out.println(">> FacadeImplementationWS: getRuralHousesByHabitaciones");

		DataAccess dbManager = new DataAccess();

		Vector<RuralHouse> ruralHouses = dbManager.getRuralHouseByHabitaciones(habitaciones);
		dbManager.close();
		System.out.println("<< FacadeImplementationWS:: getRuralHousesByHabitaciones");

		return ruralHouses;
	}
		// -------------------------------
		public Vector<RuralHouse> getRuralHouseByMetros(String metros) {
		System.out.println(">> FacadeImplementationWS: getRuralHousesByMetros");

		DataAccess dbManager = new DataAccess();

		Vector<RuralHouse> ruralHouses = dbManager.getRuralHouseByMetros(metros);
		dbManager.close();
		System.out.println("<< FacadeImplementationWS:: getRuralHousesByMetros");

		return ruralHouses;
	}
		//----------------------------------
		
		public Vector<RuralHouse> getRuralHouseByPuntuacion(String puntuacion){
			System.out.println(">> FacadeImplementationWS: getRuralHousesBypuntuacion");

			DataAccess dbManager = new DataAccess();

			Vector<RuralHouse> rh = dbManager.getRuralHouseByPuntuacion(puntuacion);
			dbManager.close();
			System.out.println("<< FacadeImplementationWS:: getRuralHousesBypuntuacion");

			return rh;
		}
		

	// --------------------------reservar casa----------------------
		public Reserva crearReserva(RuralHouse ruralHouse,Offer oferta,String telefono, String precioTotal,String numNoches, Users client) throws RemoteException, Exception {
			DataAccess dbManager= new DataAccess();
			Reserva reservas= dbManager.crearReserva(ruralHouse, oferta, telefono, precioTotal, numNoches, client);
			dbManager.close();
			return reservas;
	}
	//---------------------------eliminar casa-----------------------
	public void BorrarCasa(RuralHouse rh) throws RemoteException, Exception {

		DataAccess dbManager = new DataAccess();
		 dbManager.BorrarCasa(rh);
			
	
	}
	

	//------Borrar Offerta---
	public void BorrarOfferta (Offer of) throws RemoteException, Exception{
		DataAccess dbManager = new DataAccess();
		dbManager.BorrarOfferta(of);
	}

	//--------ofertas mediante casa---
	public Vector<Offer> getOffersbyHouse(RuralHouse rh){

		DataAccess dbManager = new DataAccess();
		
		Vector<Offer> of = dbManager.getOffersbyHouse(rh);
		dbManager.close();
		return of;
	
	}
	//--obtener comentarios mediante casa---------------
	public Vector<Valoracion> getComentariosByHouse(RuralHouse rh){
		DataAccess dbManager= new DataAccess();
		Vector<Valoracion> valoraciones = dbManager.getComentariosByHouse(rh);
		dbManager.close();
		return valoraciones;
	}
	//-----obtener reservas mediante usuario----
	public Vector<Reserva> getReservasByClient(){
		DataAccess dbManager= new DataAccess();
		Vector<Reserva> reservas = dbManager.getReservasByClient();
		dbManager.close();
		return reservas;
	}
	
	
	// -----------------------------------------------
	public Vector<RuralHouse> ordenarVector(Vector<RuralHouse> a){
		Vector<RuralHouse> aux=null;
		return aux;
	}
	
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
