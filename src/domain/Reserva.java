package domain;

import java.io.*;
import java.util.Date;
import java.util.Vector;

import javax.jws.soap.SOAPBinding.Use;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Reserva implements Serializable {

	private Date fechaReserva;
	private int numReserva;
	private int numTelf;
	private Offer oferta;
	private Users client;
	
	public Reserva(Date fechaReserva, int numReserva, int numTelf, Offer oferta, Users client) {
		this.fechaReserva = fechaReserva;
		this.numReserva = numReserva;
		this.numTelf = numTelf;
		this.oferta = oferta;
		this.client = client;
	}

	/**
	 * @return the fechaReserva
	 */
	public Date getFechaReserva() {
		return fechaReserva;
	}

	/**
	 * @param fechaReserva the fechaReserva to set
	 */
	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	/**
	 * @return the numReserva
	 */
	public int getNumReserva() {
		return numReserva;
	}

	/**
	 * @param numReserva the numReserva to set
	 */
	public void setNumReserva(int numReserva) {
		this.numReserva = numReserva;
	}

	/**
	 * @return the numTelf
	 */
	public int getNumTelf() {
		return numTelf;
	}

	/**
	 * @param numTelf the numTelf to set
	 */
	public void setNumTelf(int numTelf) {
		this.numTelf = numTelf;
	}

	/**
	 * @return the oferta
	 */
	public Offer getOferta() {
		return oferta;
	}

	/**
	 * @param oferta the oferta to set
	 */
	public void setOferta(Offer oferta) {
		this.oferta = oferta;
	}

	/**
	 * @return the client
	 */
	public Users getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Users client) {
		this.client = client;
	}
	
	

}
