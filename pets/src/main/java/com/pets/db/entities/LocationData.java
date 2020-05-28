package com.pets.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "location_data")
public class LocationData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne(mappedBy = "locationData")
    private User user;
	
	@Column(name = "contry")
	private String contry;
	
	@Column(name = "city")
	private String city;

	@Column(name = "street")
	private String street;
	
	public LocationData() {}
	
    public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	public String getContry() {
		return contry;
	}


	public void setContry(String contry) {
		this.contry = contry;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


}
