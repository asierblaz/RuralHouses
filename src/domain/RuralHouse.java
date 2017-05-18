package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javax.persistence.*;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class RuralHouse implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	@GeneratedValue
	private Integer houseNumber;
	private String description;
	private String city;
	private String direccion;
	private String numHabitaciones;
	private String m2;
	private Owner owner;
	private float media;
	private String mediastring;
	private int cont;
	private float puntuaciontotal;
	@OneToMany(orphanRemoval=true)
	public Vector<Offer> offers;
	@OneToMany(orphanRemoval=true)

	public Vector<Valoracion> valoraciones;

	public RuralHouse() {
		super();
	}

	public RuralHouse(String description, String city, String direccion, String numHabitaciones,
			String m2, Owner owner) {
		this.houseNumber = houseNumber;
		this.description = description;
		this.city = city;
		this.direccion = direccion;
		this.numHabitaciones=numHabitaciones;
		this.m2 = m2;
		this.owner = owner;
		this.media=0;
		this.mediastring="0";
		this.cont=1;
		this.puntuaciontotal=0;
		offers = new Vector<Offer>();
		valoraciones = new Vector<Valoracion>();
	}
	/**
	 * @return the puntuacion
	 */


	public RuralHouse(Integer houseNumber,String description, String city, String direccion, String numHabitaciones,
			String m2, Owner owner) {

		this.houseNumber = houseNumber;
		this.description = description;
		this.city = city;
		this.direccion = direccion;
		this.numHabitaciones=numHabitaciones;
		this.m2 = m2;
		this.owner = owner;
		offers = new Vector<Offer>();
		valoraciones = new Vector<Valoracion>();
	}

	/**
	 * @return the puntuaciontotal
	 */
	public float getPuntuaciontotal() {
		return puntuaciontotal;
	}

	/**
	 * @param puntuaciontotal the puntuaciontotal to set
	 */
	public void setPuntuaciontotal(float puntuaciontotal) {
		this.puntuaciontotal = puntuaciontotal;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @return the mediastring
	 */
	public String getMediastring() {
		return mediastring;
	}

	/**
	 * @param mediastring the mediastring to set
	 */
	public void setMediastring(String mediastring) {
		this.mediastring = mediastring;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the numHabitaciones
	 */
	public String getNumHabitaciones() {
		return numHabitaciones;
	}

	/**
	 * @param numHabitaciones the numHabitaciones to set
	 */
	public void setNumHabitaciones(String numHabitaciones){
		this.numHabitaciones=numHabitaciones;
	}
	

	/**
	 * @return the m2
	 */
	public String getM2() {
		return m2;
	}

	/**
	 * @param m2 the m2 to set
	 */
	public void setM2(String m2) {
		this.m2 = m2;
	}

	/**
	 * @return the cont
	 */
	public int getCont() {
		return cont;
	}

	/**
	 * @param cont the cont to set
	 */
	public void setCont(int cont) {
		this.cont = cont;
	}

	public Integer getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(Integer houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getDescription() {
		return description;
	}

	/**
	 * @return the media
	 */
	public float getMedia() {
		return media;
	}

	/**
	 * @param media the media to set
	 */
	public void setMedia(float media) {
		this.media = media;
	}



	/**
	 * @return the offers
	 */
	public Vector<Offer> getOffers() {
		return offers;
	}

	/**
	 * @param offers the offers to set
	 */
	public void setOffers(Vector<Offer> offers) {
		this.offers = offers;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	/**
	 * This method creates an offer with a house number, first day, last day and
	 * price
	 * 
	 * @param House
	 *            number, start day, last day and price
	 * @return None
	 */
	public Offer createOffer(Date firstDay, Date lastDay, float price) {
		System.out.println("LLAMADA RuralHouse createOffer, offerNumber=" + " firstDay=" + firstDay + " lastDay="
				+ lastDay + " price=" + price);
		Offer off = new Offer(firstDay, lastDay, price, this);
		offers.add(off);
		return off;
	}

	public Valoracion crearValoracion(String comentario, String puntuacion, String nombre){
		Valoracion val = new Valoracion(this, comentario, puntuacion, nombre);
		valoraciones.add(val);
		return val;
	}
	
	
	
	public void anadirval(Valoracion v){
		System.out.println(valoraciones);
	valoraciones.add(v);
	

	
	}
	
	
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + houseNumber.hashCode();
		return result;
	}

	/**
	 * @return the valoraciones
	 */
	public Vector<Valoracion> getValoraciones() {
		return valoraciones;
	}

	/**
	 * @param valoraciones the valoraciones to set
	 */
	public void setValoraciones(Vector<Valoracion> valoraciones) {
		this.valoraciones = valoraciones;
	}

	@Override
	public String toString() {
		return this.houseNumber + ": " + this.city;
	}

	@Override
	public boolean equals(Object obj) {
		RuralHouse other = (RuralHouse) obj;
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		// if (houseNumber != other.houseNumber) // NO COMPARAR ASÍ ya que
		// houseNumber NO ES "int" sino objeto de "java.lang.Integer"
		if (!houseNumber.equals(other.houseNumber))
			return false;
		return true;
	}

	/**
	 * This method obtains available offers for a concrete house in a certain
	 * period
	 * 
	 * @param houseNumber,
	 *            the house number where the offers must be obtained
	 * @param firstDay,
	 *            first day in a period range
	 * @param lastDay,
	 *            last day in a period range
	 * @return a vector of offers(Offer class) available in this period
	 */
	public Vector<Offer> getOffers(Date firstDay, Date lastDay) {

		Vector<Offer> availableOffers = new Vector<Offer>();
		Iterator<Offer> e = offers.iterator();
		Offer offer;
		while (e.hasNext()) {
			offer = e.next();
			if ((offer.getFirstDay().compareTo(firstDay) >= 0) && (offer.getLastDay().compareTo(lastDay) <= 0))
				availableOffers.add(offer);
		}
		return availableOffers;

	}

	/**
	 * @return the mediaint
	 */

	/**
	 * @param mediaint the mediaint to set
	 */


	/**
	 * @return the mediastring
	 */


	/**
	 * @param mediastring the mediastring to set
	 */
	

	/**
	 * This method obtains the first offer that overlaps with the provided dates
	 * 
	 * @param firstDay,
	 *            first day in a period range
	 * @param lastDay,
	 *            last day in a period range
	 * @return the first offer that overlaps with those dates, or null if there
	 *         is no overlapping offer
	 */

	public Offer overlapsWith(Date firstDay, Date lastDay) {

		Iterator<Offer> e = offers.iterator();
		Offer offer = null;
		while (e.hasNext()) {
			offer = e.next();
			if ((offer.getFirstDay().compareTo(lastDay) < 0) && (offer.getLastDay().compareTo(firstDay) > 0))
				return offer;
		}
		return null;

	}

}
