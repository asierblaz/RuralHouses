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
	//private Vector<Activity> activities;

	public Owner(String nombre, String usuario, String pass, String cuenta) {
		super(nombre, usuario, pass, cuenta);
		ruralHouses = new Vector<RuralHouse>();
	}

}
/*public Vector<RuralHouse> getRuralHouses() {
return ruralHouses;
}

public Vector<Activity> getActivies() {
return activities;
}

public RuralHouse addRuralHouse(int houseNumber, String description,
	String city, String address, boolean[] services) {
RuralHouse rh = new RuralHouse(houseNumber, this, description, city,
		address, services);
ruralHouses.add(rh);
return rh;

}

public Activity addActivity(int activityNumber, String description,
	double price, String name) {
Activity a = new Activity(activityNumber, description, price, name, this);
activities.add(a);
return a;

}

}*/