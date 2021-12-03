package com.meru.userService.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="address")
public class Address {
	
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column (name = "house_number")
    private Integer houseNumber;

    @Column (name = "street")
    private String street;
    
    @Column (name = "street_number")
    private Integer streetNumber;
    
    @Column (name = "zip_code")
    private Integer zipCode;
    
    @Column (name = "state")
    private String state;
    
    @Column (name = "country")
    private String country;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(Integer houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getStreet() {
		return street;
	}

	public Integer getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(Integer streetNumber) {
		this.streetNumber = streetNumber;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address(Long id, Integer houseNumber, String street, Integer streetNumber, Integer zipCode, String state,
			String country, User user) {
		super();
		this.id = id;
		this.houseNumber = houseNumber;
		this.street = street;
		this.streetNumber = streetNumber;
		this.zipCode = zipCode;
		this.state = state;
		this.country = country;
		this.user = user;
	}

	public Address() {
		super();
	}

	
}
