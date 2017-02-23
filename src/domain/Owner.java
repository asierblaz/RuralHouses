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

public class Owner extends Users implements Serializable {

	private Vector<RuralHouse> ruralHouses;
	// private Vector<Activity> activities;

	public Owner(String nombre, String usuario, String pass, String cuenta) {
		super(nombre, usuario, pass, cuenta);
		ruralHouses = new Vector<RuralHouse>();
	}

}