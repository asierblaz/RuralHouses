package dataAccess;

import java.io.ObjectStreamException;
import java.lang.management.ThreadMXBean;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Vector;

import javax.jdo.annotations.Queries;
import javax.jws.WebMethod;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityResult;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import com.objectdb.o.BDP;
import com.objectdb.o.VAL;

import businessLogic.ApplicationFacadeInterfaceWS;
import configuration.ConfigXML;
import domain.*;
import exceptions.CasaNoReservada;
import exceptions.OverlappingOfferExists;
import exceptions.OverlappingUsersExists;
import gui.EliminarCasaGUI;
import gui.MainGUI;


public class DataAccess {

	public static String fileName;
	protected static EntityManagerFactory emf;
	protected static EntityManager db;
	
	ConfigXML c;

	public DataAccess() {
		c = ConfigXML.getInstance();

		System.out.println("Creating objectdb instance => isDatabaseLocal: " + c.isDatabaseLocal()
				+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());

		String filename = c.getDbFilename();

		if (c.isDatabaseLocal()) {

			emf = Persistence.createEntityManagerFactory(c.getDbFilename());
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());
			emf = Persistence.createEntityManagerFactory(
					"objectdb://" + c.getDatabaseNode() + ":" + c.getDatabasePort() + "/" + c.getDbFilename(),
					properties);
			db = emf.createEntityManager();
		}
	}

	public void initializeDB() {

		db.getTransaction().begin();
		try {

			TypedQuery<RuralHouse> query = db.createQuery("SELECT c FROM RuralHouse c", RuralHouse.class);
			List<RuralHouse> results = query.getResultList();

			Iterator<RuralHouse> itr = results.iterator();

			while (itr.hasNext()) {
				 RuralHouse rh = itr.next();
				db.remove(rh);
			}

			
		//	 RuralHouse rh1 = new RuralHouse("Ezkioko etxea", "Ezkio");
		//	 RuralHouse rh2 = new RuralHouse("Etxetxikia", "Iruna");
		//	 RuralHouse rh3 = new RuralHouse("Udaletxea", "Bilbo"); RuralHouse
			// rh4 = new RuralHouse("Gaztetxea", "Renteria");
			 
		//	 db.persist(rh1); db.persist(rh2); db.persist(rh3);
		//	 db.persist(rh4);

	

			db.getTransaction().commit();
			System.out.println("Db initialized");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


	
	//---------------------reservar Casa---------------------
	public Reserva crearReserva(RuralHouse ruralHouse, Offer oferta,String telefono, String precioTotal,String numNoches, Users client)throws RemoteException, Exception {
		System.out.println("DataAccess>> crearReserva");
		Offer of= db.find(Offer.class, oferta.getOfferNumber());
		db.getTransaction().begin();
		Reserva r= of.crearReserva(ruralHouse, oferta, telefono, precioTotal, numNoches, client);
		System.out.println(r);
		
		client.anadirReserva(r);
		//db.persist(client);
		System.out.println(client.getReservas());
		db.persist(r);
		db.getTransaction().commit();		
		return r;
	}
	//-------------------------------------------------------

	public Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay, float price) {
		System.out.println(">> DataAccess: createOffer=> ruralHouse= " + ruralHouse + " firstDay= " + firstDay
				+ " lastDay=" + lastDay + " price=" + price);

		try {
			
			
			RuralHouse rh = db.find(RuralHouse.class, ruralHouse.getHouseNumber());
			
			db.getTransaction().begin();
			Offer o = rh.createOffer(firstDay, lastDay, price);
			db.persist(o);
			// System.out.println(rh.offers);
			db.getTransaction().commit();
			return o;

		} catch (Exception e) {
			System.out.println("Offer not created: " + e.toString());
			return null;
		}
	}

//---------------------------------crear valoracion-------------------------
	public Valoracion crearValoracion(RuralHouse ruralHouse, String comentario, String puntuacion, String nombre)
			throws RemoteException, Exception {
		try {
			RuralHouse rh = db.find(RuralHouse.class, ruralHouse.getHouseNumber());
			System.out.println(rh);
			db.getTransaction().begin();
			System.out.println(ruralHouse);
			System.out.println(comentario);
			System.out.println(nombre);
			System.out.println(puntuacion);
			//Valoracion v= new Valoracion(ruralHouse,comentario, puntuacion, nombre);
			Valoracion v = rh.crearValoracion(comentario,  puntuacion,  nombre);
			//rh.anadirval(v);
			System.out.println(rh.getValoraciones().elementAt(0));
			
			db.persist(v);
			//db.persist(rh);
			db.getTransaction().commit();
			return v;
		} catch (Exception e) {
		System.out.println("comentario not created: " + e.toString());
		String d = "La valoración no ha sido enviada";
		JOptionPane.showMessageDialog(null, d, "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	// ---------------------crear cliente------------------------------------------
	public Client crearCliente(String nombre, String usuario, String pass, String cuenta) {
		System.out.println(">> FacadeImplementationWS: crearCliente=> Nombre= " + nombre + " Usuario= " + usuario
				+ " Contraseña=" + pass + " Cuenta Bancaria=" + cuenta);

		try {
			db.getTransaction().begin();
			Client c = new Client(nombre, usuario, pass, cuenta);
			db.persist(c);
			db.getTransaction().commit(); // estupendo!!
			return c;

		} catch (Exception e) {
			System.out.println("El cliente no ha sido guardado en la base de datos ");
			return null;
		}

	}
	// -------------------------Crear owner--------------------------------

	public Owner crearOwner(String nombre, String usuario, String pass, String cuenta) {
		System.out.println(">> FacadeImplementationWS: crearCliente=> Nombre= " + nombre + " Usuario= " + usuario
				+ " Contraseña=" + pass + " Cuenta Bancaria=" + cuenta);

		try {
			db.getTransaction().begin();
			Owner o = new Owner(nombre, usuario, pass, cuenta);
			db.persist(o); // hay que darle persistencia al objeto creado
			db.getTransaction().commit();
			return o;

		} catch (Exception e) {
			System.out.println("El Owner no ha sido guardado en la base de datos ");
			return null;
		}

	}
	//-------------------Actualizar Usuario--------------
	public boolean actualizarUsuario (Users u, String nombre, String usuario, String pass, String cuenta){
		
		try{
		u= db.find(Users.class, usuario);
		db.getTransaction().begin();

		u.setNombre(nombre);
		u.setUsuario(usuario);
		u.setPass(pass);
		u.setCuenta(cuenta);
		db.persist(u);
		db.getTransaction().commit();
		} catch (Exception e1) {
			System.out.println(e1);
			System.out.println("casa no actualizada");
			return false;
		}
		
		
		return true;
		
	}
	

	// -----------------------------------crear casa rural----------------
	public RuralHouse crearRuralHouse(String description, String city,String direccion,String numHabitaciones,String m2, Owner owner) throws RemoteException, Exception {
		System.out.println(
	">> FacadeImplementationWS: crearRuralHouse=> Ciudad= " + city + " Descripción=" + description);

		
		
 	try {
		db.getTransaction().begin();
			RuralHouse rh = new RuralHouse(description, city, direccion, numHabitaciones, m2, owner);
		//	owner.anadirCasaRural(rh.getDescription(), rh.getCity(), rh.getDireccion(), rh.getM2(), rh.getNumHabitaciones(), owner);
		//	db.persist(owner);
			db.persist(rh);
			db.getTransaction().commit();
			return rh;
		} catch (Exception e) {
			System.out.println("La casa no se ha guardado en la base de datos ");
			return null;
		}
	}
	//---------------------------------actualizar rural house--------------------------------
	public boolean actualizarRuralHouse (RuralHouse rh,String description, String city,String direccion,String numHabitaciones,String m2) throws RemoteException, Exception{
		//System.out.println(">> FacadeImplementationWS: crearRuralHouse=> Ciudad= " + city + " Descripción=" + description + "RuralHouse= " + rh );
			try{
				rh=db.find(RuralHouse.class, rh.getHouseNumber());

				db.getTransaction().begin();
				rh.setCity(city);
				rh.setNumHabitaciones(numHabitaciones);
				rh.setDescription(description);	
				rh.setDireccion(direccion);				
				rh.setM2(m2);
				db.persist(rh);
				
				db.getTransaction().commit();
				System.out.println("La casa "+ rh.toString()+ "ha sido actualizada");
							
		return true;
	} catch (Exception e1) {
		System.out.println(e1);
		System.out.println("casa no actualizada");
		return false;
	}
			}

	public void actualizarMedia(RuralHouse rh,float media,float puntuaciontotal,int cont){
		rh=db.find(RuralHouse.class, rh.getHouseNumber());
		db.getTransaction().begin();
		rh.setMedia(media);
		rh.setCont(cont);
		rh.setMediastring(String.valueOf((int) media));
		rh.setPuntuaciontotal(puntuaciontotal);
		System.out.println(media);
		System.out.println(cont);
		db.persist(rh);
		
		db.getTransaction().commit();
		
	}
	
	// ------------------------------------------------------------

	public Vector<RuralHouse> getAllRuralHouses() {
		System.out.println(">> DataAccess: getAllRuralHouses");
		Vector<RuralHouse> res = new Vector<>();
	

		TypedQuery<RuralHouse> query = db.createQuery("SELECT c FROM RuralHouse c", RuralHouse.class);
		List<RuralHouse> results = query.getResultList();
		Iterator<RuralHouse> itr = results.iterator();
		while (itr.hasNext()) {
			res.add(itr.next());
		}

		return res;}
	//--------------------obetener casa mediante owner---
	public Vector<RuralHouse> getRuralHouseByOwner(){
		System.out.println(">> DataAccess: geRuralHouseByOwner");
		Vector<RuralHouse> res = new Vector<>();
		
		
		
		Owner owner = (Owner) MainGUI.getUsuario();
		String OwnerConectado=owner.getUsuario();
		TypedQuery<RuralHouse> query = db.createQuery("SELECT c FROM RuralHouse c WHERE c.owner.getUsuario()='"+OwnerConectado+"'",RuralHouse.class);
		//"SELECT p FROM Pilot p WHERE p.nationality='"+naz+"'",Pilot.class
		List<RuralHouse> results = query.getResultList();

		Iterator<RuralHouse> itr = results.iterator();

		while (itr.hasNext()) {
			res.add(itr.next());
		}
		
		return res;
		
	}
	//----------------obtener casa mediante ciudad-----------
	public Vector<RuralHouse> getRuralHouseByCiudad(String ciudad){
		Vector<RuralHouse> res = new Vector<>();
		
		
		TypedQuery<RuralHouse> query = db.createQuery("SELECT c FROM RuralHouse c WHERE c.city='"+ciudad+"'",RuralHouse.class);
		//"SELECT p FROM Pilot p WHERE p.nationality='"+naz+"'",Pilot.class
		List<RuralHouse> results = query.getResultList();

		Iterator<RuralHouse> itr = results.iterator();

		while (itr.hasNext()) {
			res.add(itr.next());
		}
		
		return res;
		
	}
	//----------------obtener casa mediante direccion-----------
	public Vector<RuralHouse> getRuralHouseByDireccion(String direccion){
		Vector<RuralHouse> res = new Vector<>();
		
		
		TypedQuery<RuralHouse> query = db.createQuery("SELECT c FROM RuralHouse c WHERE c.direccion='"+direccion+"'",RuralHouse.class);
		//"SELECT p FROM Pilot p WHERE p.nationality='"+naz+"'",Pilot.class
		List<RuralHouse> results = query.getResultList();

		Iterator<RuralHouse> itr = results.iterator();

		while (itr.hasNext()) {
			res.add(itr.next());
		}
		
		return res;
		
	}
	//----------------obtener casa mediante descripcion-----------
	public Vector<RuralHouse> getRuralHouseByDescripcion(String descripcion){
		Vector<RuralHouse> res = new Vector<>();
		
		
		TypedQuery<RuralHouse> query = db.createQuery("SELECT c FROM RuralHouse c WHERE c.description='"+descripcion+"'",RuralHouse.class);
		//"SELECT p FROM Pilot p WHERE p.nationality='"+naz+"'",Pilot.class
		List<RuralHouse> results = query.getResultList();

		Iterator<RuralHouse> itr = results.iterator();

		while (itr.hasNext()) {
			res.add(itr.next());
		}
		
		return res;
		
	}
	//----------------obtener casa mediante habitacion-----------
	public Vector<RuralHouse> getRuralHouseByHabitaciones(String habitaciones){
		System.out.println(">> DataAccess: geRuralHouseByOwner");
		Vector<RuralHouse> res = new Vector<>();
		
		
		TypedQuery<RuralHouse> query = db.createQuery("SELECT c FROM RuralHouse c WHERE c.numHabitaciones='"+habitaciones+"'",RuralHouse.class);
		//"SELECT p FROM Pilot p WHERE p.nationality='"+naz+"'",Pilot.class
		List<RuralHouse> results = query.getResultList();

		Iterator<RuralHouse> itr = results.iterator();

		while (itr.hasNext()) {
			res.add(itr.next());
		}
		
		return res;
		
	}
	//----------------obtener casa mediante metros-----------
	public Vector<RuralHouse> getRuralHouseByMetros(String metros){
		System.out.println(">> DataAccess: geRuralHouseByOwner");
		Vector<RuralHouse> res = new Vector<>();
		
		TypedQuery<RuralHouse> query = db.createQuery("SELECT c FROM RuralHouse c WHERE c.m2='"+metros+"'",RuralHouse.class);
		//"SELECT p FROM Pilot p WHERE p.nationality='"+naz+"'",Pilot.class
		List<RuralHouse> results = query.getResultList();
		Iterator<RuralHouse> itr = results.iterator();
		while (itr.hasNext()) {
			res.add(itr.next());
		}
		
		return res;
		
		
	}
	
	//Obtener casa mediante puntuacion valoración
	public Vector<RuralHouse> getRuralHouseByPuntuacion(String puntuacion){

		System.out.println(">> DataAccess: getRuralHouseByPuntuacion");
		Vector<RuralHouse> res = new Vector<>();
		
		
		
		TypedQuery<RuralHouse> query = db.createQuery("SELECT c FROM RuralHouse c WHERE c.mediastring='"+puntuacion+"'",RuralHouse.class);

		// TypedQuery<Valoracion> query = db.createQuery("SELECT v FROM Valoracion v WHERE v.puntuacion='"+puntuacion+"'",Valoracion.class);
		System.out.println("b");
		List<RuralHouse> results = query.getResultList();
		Iterator<RuralHouse> itr = results.iterator();
		while (itr.hasNext()) {
			res.add(itr.next());
		}
		
		return res;
		
	}

	//------------obtener reservas mediante usuario----
	public Vector<Reserva> getReservasByClient(){
		Vector<Reserva> res = new Vector<>();
		
		Client client = (Client) MainGUI.getUsuario();
		String clienteconectado=client.getUsuario();
		TypedQuery<Reserva> query = db.createQuery("SELECT r FROM Reserva r WHERE r.client.getUsuario()='"+clienteconectado+"'",Reserva.class);
		List<Reserva> results = query.getResultList();
		Iterator<Reserva> itr = results.iterator();
		while (itr.hasNext()) {
			res.add(itr.next());
		}
		
		return res;
	}
/*Owner owner = (Owner) MainGUI.getUsuario();
		String OwnerConectado=owner.getUsuario();
		TypedQuery<RuralHouse> query = db.createQuery("SELECT c FROM RuralHouse c WHERE c.owner.getUsuario()='"+OwnerConectado+"'",RuralHouse.class);
		//"SELECT p FROM Pilot p WHERE p.nationality='"+naz+"'",Pilot.class
		List<RuralHouse> results = query.getResultList();

		Iterator<RuralHouse> itr = results.iterator();

		while (itr.hasNext()) {
			res.add(itr.next());*/
	
	//-----obtener comentarios mediante casa---------
	public Vector<Valoracion> getComentariosByHouse(RuralHouse rh){
		/*System.out.println(">> DataAccess: getComentariosByHouse");
		Vector<Valoracion> res = new Vector<>();
		System.out.println("1");
		
		TypedQuery<Valoracion> query = db.createQuery("SELECT v FROM Valoracion v WHERE ruralHouse.toString() ='"+rh+"'",Valoracion.class);
		System.out.println("2");

		List<Valoracion> results = query.getResultList();
		System.out.println("3");


		Iterator<Valoracion> itr = results.iterator();
		System.out.println("4");


		while (itr.hasNext()) {
			res.add(itr.next());
		}
		
		return res;*/
		rh = db.find(RuralHouse.class, rh.getHouseNumber());

		System.out.println("Valoraciones");
		Vector<Valoracion> val = new Vector<>();
		System.out.println(rh.valoraciones);
		val = rh.valoraciones;
		
		return val;
		
	}
	
	
	
	//---------------------Obtener offertas mediante casa----
	public Vector<Offer> getOffersbyHouse(RuralHouse rh){
		rh = db.find(RuralHouse.class, rh.getHouseNumber());

		System.out.println("offertas");
		Vector<Offer> of = new Vector<>();
		System.out.println(rh.offers);
		of = rh.offers;
		
		return of;
		
	}
	//---------------------------------------------------------
		public Vector<RuralHouse>  getRhDescription(RuralHouse rh){
			
		RuralHouse	des=  db.find(RuralHouse.class, rh.getDescription());
			
			return null;
	}

	public Vector<Offer> getOffers(RuralHouse rh, Date firstDay, Date lastDay) {
		System.out.println(">> DataAccess: getOffers");
		Vector<Offer> res = new Vector<>();
		RuralHouse rhn = db.find(RuralHouse.class, rh.getHouseNumber());
		res = rhn.getOffers(firstDay, lastDay);
		return res;
	}
	// -------------------------------comprobar si ya existe la offer--------------------------

	public boolean existsOverlappingOffer(RuralHouse rh, Date firstDay, Date lastDay) throws OverlappingOfferExists {
		try {
			RuralHouse rhn = db.find(RuralHouse.class, rh.getHouseNumber());
			if (rhn.overlapsWith(firstDay, lastDay) != null)
				return true;
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
			return true;
		}
		return false;
	}
	// ------------------------------comprobar si existe el usuario-------------------

	public boolean existsOvelappingUsers(String usuario) throws RemoteException, OverlappingUsersExists {

		try {

			Users u = db.find(Users.class, usuario);
			if (u != null) // preguntar (excemption para que no registre usuarios
							// iguales.
				return true;
		} catch (Exception e) {
			System.out.println("usuario" + usuario);
			System.out.println("Error: " + e.toString());
			return true;
		}

		return false;
	}

	// -----------------------------comprobar usuario para logearse----------------------------------------------------

	public Users comprobarUsuario(String usuario, String pass) throws RemoteException {

		try {
			Users u = db.find(Users.class, usuario);
			System.out.println(u);
			if (u == null)
				return null;
			else
				u = db.find(Users.class, pass);
				return u;
		} finally {

		}
	}
	
//-----Borrar Oferta seleccionada----
	public void BorrarOfferta (Offer of) throws RemoteException, Exception{
		of = db.find(Offer.class,of.getOfferNumber());
		db.getTransaction().begin();
		db.remove(of);
		
		db.getTransaction().commit();
		String message = "La offerta" + of.getOfferNumber() + "ha sido borrada con exito!";
		JOptionPane.showMessageDialog(null, message, "Correcto", JOptionPane.INFORMATION_MESSAGE);

	}
	
//--------------------------eliminar una casa------------------
	public void BorrarCasa(RuralHouse rh) throws RemoteException, Exception {
		try {

			rh = db.find(RuralHouse.class, rh.getHouseNumber());
			System.out.println(rh.offers);
			if (rh.offers.isEmpty() ||	 rh.valoraciones.isEmpty()) {
				db.getTransaction().begin();
				db.remove(rh);
				db.getTransaction().commit();
				String message = "La casa  " + rh.getCity() + " ha sido borrada con exito!";
				JOptionPane.showMessageDialog(null, message, "Correcto", JOptionPane.INFORMATION_MESSAGE);
			} else {
				String message = "La casa en " + rh.getCity() + " no ha sido borrada porque tiene ofertas y/o valoraciones";
				JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
				if (ConfirmarDatos()) {
					rh.offers.removeAllElements();
					rh.valoraciones.removeAllElements();
					String elim = "Todas las ofertas y/o valoraciones de la casa " + rh.getCity() + "han sido eliminadas con exito!";
					JOptionPane.showMessageDialog(null, elim, "Correcto", JOptionPane.INFORMATION_MESSAGE);
					BorrarCasa(rh);

				} else {
					String d = "La casa en " + rh.getCity() + " no ha sido borrada";
					JOptionPane.showMessageDialog(null, d, "Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}

		} catch (Exception e1) {
			System.out.println(e1);
			String message = "La casa en " + rh.getCity() + " no ha sido borrada";
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
			
	
		
		}
	}
	
	private boolean ConfirmarDatos() {

		String nl = System.getProperty("line.separator");

		String message = "La casa que estas intentando borrar tiene ofertas, ¿deseas eliminar todas las ofertas de la casa?";

		int selection = JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);

		return selection == 0;
	}
	//----------------------------
	

	public void close() {
		db.close();
		System.out.println("DataBase closed");
	}

}
