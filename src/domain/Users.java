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
public class Users implements Serializable {

	 
	private String cuenta ="";
	private String nombre = "";
@Id	private String usuario =""; //solo puede haber un tipo de usuario
	private String pass ="";
	private Vector<Reserva> reservas;
	

	
	public Users(String nombre, String usuario, String pass, String cuenta){
		this.cuenta= cuenta;
		this.nombre= nombre;
		this.usuario= usuario;
		this.pass= pass;
		reservas= new Vector<Reserva>();
	}

	/**
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * @return the reservas
	 */
	public Vector<Reserva> getReservas() {
		return reservas;
	}

	/**
	 * @param reservas the reservas to set
	 */
	public void setReservas(Vector<Reserva> reservas) {
		this.reservas = reservas;
	}

	/**
	 * @param cuenta the cuenta to set
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Reserva anadirReserva(Reserva r){
		reservas.add(r);
		return r;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
	
	
	
	
	
	
	
	
	
}
