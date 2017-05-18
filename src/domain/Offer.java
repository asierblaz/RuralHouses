package domain;

import java.awt.print.Book;
import java.io.*;
import java.util.Date;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;


@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Offer implements Serializable {
	

	@Id
	@GeneratedValue
	private Integer offerNumber;
	private Date firstDay; // Dates are stored as java.util.Date objects instead of java.sql.Date objects
	private Date lastDay;  // because, they are not well stored in db4o as java.util.Date objects
	private float price;   // This is coherent because objects of java.sql.Date are objects of java.util.Date 
	@XmlIDREF
	private RuralHouse ruralHouse;
	private Reserva reserva;

	public Offer(){}
	public Offer(Date firstDay, Date lastDay, float price, RuralHouse ruralHouse){
		  this.firstDay=firstDay;
		  this.lastDay=lastDay;
		  this.price=price;
		  this.ruralHouse=ruralHouse;
		  this.reserva=reserva;
		  this.offerNumber= offerNumber;
	}
	/**
	 * Get the house number of the offer
	 * 
	 * @return the house number
	 */
	public RuralHouse getRuralHouse() {
		return this.ruralHouse;
	}

	/**
	 * Set the house number to a offer
	 * 
	 * @param house number
	 */
	public void setRuralHouse(RuralHouse ruralHouse) {
		this.ruralHouse = ruralHouse;
	}


	/**
	 * @return the reserva
	 */
	public Reserva getReserva() {
		return reserva;
	}
	/**
	 * @param reserva the reserva to set
	 */
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	/**
	 * @param offerNumber the offerNumber to set
	 */
	public void setOfferNumber(Integer offerNumber) {
		this.offerNumber = offerNumber;
	}
	/**
	 * Get the offer number
	 * 
	 * @return offer number
	 */
	public int getOfferNumber() {
		return this.offerNumber;
	}

	

	/**
	 * Get the first day of the offer
	 * 
	 * @return the first day
	 */
	public Date getFirstDay() {
		return this.firstDay;
	}

	/**
	 * Set the first day of the offer
	 * 
	 * @param firstDay
	 *            The first day
	 */
	public void setFirstDay(Date firstDay) {
		this.firstDay = firstDay;
	}

	/**
	 * Get the last day of the offer
	 * 
	 * @return the last day
	 */
	public Date getLastDay() {
		return this.lastDay;
	}

	/**
	 * Set the last day of the offer
	 * 
	 * @param lastDay
	 *            The last day
	 */
	public void setLastDay(Date lastDay) {
		this.lastDay = lastDay;
	}

	/**
	 * Get the price
	 * 
	 * @return price
	 */
	public float getPrice() {
		return this.price;
	}

	public Reserva crearReserva(RuralHouse ruralHouse,Offer oferta,String telefono, String precioTotal,String numNoches, Users client) {
		return reserva= new Reserva(ruralHouse,this, telefono, precioTotal, numNoches, client);
	}

	/**
	 * Set the price
	 * 
	 * @param price
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	
	public String toStringg(){
		return offerNumber+";"+firstDay.toString()+";"+lastDay.toString()+";"+price;
	}
	public String toString(){
		String nl = System.getProperty("line.separator");
		String me= offerNumber+ nl +" Casa= " + ruralHouse.getCity() + nl+  " PrimerDía= " + firstDay+ nl + " UltimoDía= " + lastDay+ nl+ " PrecioPorNoche= " + price  ;
		
		return me;
		
	}
	public String toString2(){
		return offerNumber+":"+ruralHouse.getCity();

	}
}