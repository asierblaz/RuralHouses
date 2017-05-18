package domain;

import java.io.*;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity

public class Valoracion implements Serializable{

	@Id
	@GeneratedValue
	private Integer comentNumber;
	private String comentario;
	private String puntuacion;
	private String nombre;
	@XmlIDREF
	private RuralHouse ruralHouse;
	
	
	public Valoracion(){}
	public Valoracion(RuralHouse ruralHouse, String comentario, String puntuacion, String nombre) {
		super();
		this.comentario = comentario;
		this.puntuacion = puntuacion;
		this.ruralHouse = ruralHouse;
		this.nombre= nombre;
		this.comentNumber=comentNumber;
	}


	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * @return the comentNumber
	 */
	public Integer getComentNumber() {
		return comentNumber;
	}


	/**
	 * @param comentNumber the comentNumber to set
	 */
	public void setComentNumber(Integer comentNumber) {
		this.comentNumber = comentNumber;
	}


	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}


	/**
	 * @param comentario the comentario to set
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}


	/**
	 * @return the puntuacion
	 */
	public String getPuntuacion() {
		return puntuacion;
	}


	/**
	 * @param puntuacion the puntuacion to set
	 */
	public void setPuntuacion(String puntuacion) {
		this.puntuacion = puntuacion;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String nl = System.getProperty("line.separator");
		String me= "USUARIO: " + nombre + nl+  "PUNTUACÓN: " + puntuacion+ nl + "COMENTARIO: " + comentario+ nl+ "CASA: " + ruralHouse + nl +"******************************************************************************************"+nl ;
		
		return me;
		}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	
	
	
	}
