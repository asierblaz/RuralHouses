package businessLogic;

import java.util.Iterator;
import java.util.Vector;
import java.rmi.RemoteException;
import java.util.Date;

import domain.Client;
//import domain.Booking;
import domain.Offer;
import domain.Owner;
import domain.Reserva;
import domain.RuralHouse;
import domain.Users;
import domain.Valoracion;
import exceptions.BadDates;
import exceptions.CasaNoReservada;
import exceptions.OverlappingOfferExists;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ApplicationFacadeInterfaceWS  {
	

	/**
	 * This method creates an offer with a house number, first day, last day and price
	 * 
	 * @param House
	 *            number, start day, last day and price
	 * @return None
	 */


	@WebMethod Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay,
		float price) throws  OverlappingOfferExists, BadDates;
	
	
	@WebMethod public Valoracion crearValoracion(RuralHouse ruralHouse, String comentario, String puntuacion, String nombre)
			throws RemoteException, Exception;

	/**
	 * This method creates a book with a corresponding parameters
	 * 
	 * @param First
	 *            day, last day, house number and telephone
	 * @return a book
	 */
	
	
	
	/**
	 * This method retrieves the existing  rural houses 
	 * 
	 * @return a Set of rural houses
	 */
	@WebMethod public Vector<RuralHouse> getAllRuralHouses();
	@WebMethod public Vector<RuralHouse> getRuralHousesByOwner();
	@WebMethod public Vector<RuralHouse> getRuralHouseByCiudad(String ciudad);
	@WebMethod public Vector<RuralHouse> getRuralHouseByDireccion(String direccion);
	@WebMethod public Vector<RuralHouse> getRuralHouseByHabitaciones(String habitaciones);
	@WebMethod public Vector<RuralHouse> getRuralHouseByMetros(String metros);
	@WebMethod public Vector<RuralHouse> getRuralHouseByDescripcion(String descripcion);
	@WebMethod public Vector<Offer> getOffersbyHouse(RuralHouse rh);
	@WebMethod 	public Vector<Valoracion> getComentariosByHouse(RuralHouse rh);
	@WebMethod	public Vector<RuralHouse> getRuralHouseByPuntuacion(String puntuacion);

	@WebMethod public Vector<Reserva> getReservasByClient();

	public void actualizarMedia(RuralHouse rh,float media, float puntuaciontotal,int cont);

	public Reserva crearReserva(RuralHouse ruralHouse,Offer oferta,String telefono, String precioTotal,String numNoches, Users client) throws RemoteException, Exception ;
	/**
	 * This method obtains the  offers of a ruralHouse in the provided dates 
	 * 
	 * @param ruralHouse, the ruralHouse to inspect 
	 * @param firstDay, first day in a period range 
	 * @param lastDay, last day in a period range
	 * @return the first offer that overlaps with those dates, or null if there is no overlapping offer
	 */
	@WebMethod public void BorrarCasa (RuralHouse rh) throws RemoteException, Exception;
	@WebMethod public void BorrarOfferta (Offer of) throws RemoteException, Exception;

	@WebMethod public Client crearCliente (String nombre,String usuario, String pass, String cuenta) throws RemoteException, Exception;
	
	@WebMethod public Owner crearOwner(String nombre, String usuario, String pass, String cuenta)throws RemoteException, Exception;
	@WebMethod public Users comprobarUsuario(String usuario, String pass) throws RemoteException, Exception;
	@WebMethod public boolean actualizarUsuario (Users u, String nombre, String usuario, String pass, String cuenta)throws RemoteException, Exception;

	
	@WebMethod public RuralHouse crearRuralHouse(String description, String city,String direccion, String m2,String numHabitaciones, Owner owner) throws RemoteException, Exception;
	
	@WebMethod public boolean actualizarRuralHouse(RuralHouse rh, String description, String city,String direccion,String numHabitaciones,String m2)throws RemoteException, Exception;
	
	@WebMethod public Vector<Offer> getOffers( RuralHouse rh, Date firstDay,  Date lastDay) ;
	
	@WebMethod public void initializeBD();

	
}
