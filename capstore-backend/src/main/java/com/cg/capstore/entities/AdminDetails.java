package com.cg.capstore.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ADMINS")
public class AdminDetails implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String username;	
	
	@Column(name="MIN_ORD_AMT")
	private int minOrderAmount;
	
	@Column(name="C_EMAIL")
	private String contactEmail;
	
	@Column(name="O_EMAIL")
	private String orderEmail;
	
	@Column(name="C_PHONE")
	private String contactPhone;
	
	@Column(name="O_PHONE")
	private String orderPhone;
	
	@Column(name="T_PHONE")
	private String tollFreeNo;
	
	@OneToOne
	@JoinColumn(name="ADDRESS_ID")
	private Address address;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getMinOrderAmount() {
		return minOrderAmount;
	}

	public void setMinOrderAmount(int minOrderAmount) {
		this.minOrderAmount = minOrderAmount;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getOrderEmail() {
		return orderEmail;
	}

	public void setOrderEmail(String orderEmail) {
		this.orderEmail = orderEmail;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getOrderPhone() {
		return orderPhone;
	}

	public void setOrderPhone(String orderPhone) {
		this.orderPhone = orderPhone;
	}

	public String getTollFreeNo() {
		return tollFreeNo;
	}

	public void setTollFreeNo(String tollFreeNo) {
		this.tollFreeNo = tollFreeNo;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
	
}
