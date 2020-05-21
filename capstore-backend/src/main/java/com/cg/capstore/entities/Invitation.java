package com.cg.capstore.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="INVITES")
public class Invitation implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	
	@Column(name="HEADER")
	private String header;
	
	@Column(name="MESSAGE")
	private String message;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="USERNAME")
	private MerchantDetails merchant;
	
	public  Invitation () {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MerchantDetails getMerchant() {
		return merchant;
	}

	public void setMerchant(MerchantDetails merchant) {
		this.merchant = merchant;
	}
	
	
}
