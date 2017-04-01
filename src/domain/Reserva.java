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
	private String telefono;
	private Offer oferta;
	private Users client;
	
	public Reserva(Date fechaReserva, int numReserva, String telefono, Offer oferta, Users client) {
		this.fechaReserva = new Date(System.currentTimeMillis());
		this.numReserva = numReserva;
		this.telefono = telefono;
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
	 * @return the telefono
	 */
	public String gettelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void settelefono(String telefono) {
		this.telefono = telefono;
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
