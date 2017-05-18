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

	@Id
	@GeneratedValue
	private Integer numReserva;
	private String precioTotal;
	private String telefono;
	private String numNoches;
	@XmlIDREF
	private Users client;
	@XmlIDREF
	private Offer oferta;
	private RuralHouse ruralHouse;

	
	public Reserva() {	}

	public Reserva(RuralHouse ruralHouse,Offer oferta,String telefono, String precioTotal,String numNoches, Users client) {
		super();
		this.precioTotal = precioTotal;
		this.telefono = telefono;
		this.oferta = oferta;
		this.client = client;
		this.ruralHouse=ruralHouse;
		this.numNoches= numNoches;
		this.numReserva = numReserva;

	}

	/**
	 * @return the numReserva
	 */
	public Integer getNumReserva() {
		return numReserva;
	}

	/**
	 * @param numReserva the numReserva to set
	 */
	public void setNumReserva(Integer numReserva) {
		this.numReserva = numReserva;
	}

	/**
	 * @return the precioTotal
	 */
	public String getPrecioTotal() {
		return precioTotal;
	}

	/**
	 * @param precioTotal the precioTotal to set
	 */
	public void setPrecioTotal(String precioTotal) {
		this.precioTotal = precioTotal;
	}

	/**
	 * @return the ruralHouse
	 */
	public RuralHouse getRuralHouse() {
		return ruralHouse;
	}

	/**
	 * @param ruralHouse the ruralHouse to set
	 */
	public void setRuralHouse(RuralHouse ruralHouse) {
		this.ruralHouse = ruralHouse;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
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

	/**
	 * @return the numNoches
	 */
	public String getNumNoches() {
		return numNoches;
	}

	/**
	 * @param numNoches the numNoches to set
	 */
	public void setNumNoches(String numNoches) {
		this.numNoches = numNoches;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString2() {
		return "Reserva [numReserva=" + numReserva + ", precioTotal=" + precioTotal + ", telefono=" + telefono
				+ ", numNoches=" + numNoches + ", client=" + client + ", oferta=" + oferta + ", ruralHouse="
				+ ruralHouse + "]";
	}

	@Override
	public String toString(){
		String nl = System.getProperty("line.separator");
		String me= numReserva+ ":" + ruralHouse.getCity() ;
		
		return me;
	}
	
	

}
