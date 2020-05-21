package com.cg.capstore.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="CUSTOMERS")
public class CustomerDetails implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="PHONE_NO")
	private String phoneNo;
	
	@Column(name="ALT_PHONE_NO")
	private String alternatePhoneNo;
	
	@Column(name="ALT_EMAIL")
	private String alternateEmail;
	
	@Column(name="GENDER")
	@Pattern(regexp="Male|Female|Other")
	private String gender;
	
	@Column(name="W_BAL")
	private int balance;

	@OneToMany(mappedBy="customer")
	private Set<Order> orders = new HashSet<Order>();
	
	@OneToMany(mappedBy="customer")
	private Set<Address> addresses =new HashSet<Address>();
	
	@OneToMany(mappedBy="customer")
	private Set<CommonFeedback> feedbacks =new HashSet<CommonFeedback>();
	
	@OneToMany(mappedBy="customer")
	private Set<ProductFeedback> productFeedbacks = new HashSet<ProductFeedback>();
	
	@OneToMany(mappedBy="customer")
	private Set<Cart> carts = new HashSet<Cart>();
	
	@OneToMany(mappedBy="customer")
	private Set<Wishlist> wishlists = new HashSet<Wishlist>();
	
	
	public CustomerDetails() {
		
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAlternatePhoneNo() {
		return alternatePhoneNo;
	}

	public void setAlternatePhoneNo(String alternatePhoneNo) {
		this.alternatePhoneNo = alternatePhoneNo;
	}

	public String getAlternateEmail() {
		return alternateEmail;
	}

	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}

	@JsonIgnore
	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	@JsonIgnore
	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	@JsonIgnore
	public Set<CommonFeedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(Set<CommonFeedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	@JsonIgnore
	public Set<ProductFeedback> getProductFeedbacks() {
		return productFeedbacks;
	}

	public void setProductFeedbacks(Set<ProductFeedback> productFeedbacks) {
		this.productFeedbacks = productFeedbacks;
	}

	@JsonIgnore
	public Set<Cart> getCarts() {
		return carts;
	}

	public void setCarts(Set<Cart> carts) {
		this.carts = carts;
	}

	@JsonIgnore
	public Set<Wishlist> getWishlists() {
		return wishlists;
	}

	public void setWishlists(Set<Wishlist> wishlists) {
		this.wishlists = wishlists;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public void addOrder(Order order) {
		order.setCustomer(this);
		this.getOrders().add(order);
	}
	
	public void removeOrder(Order order) {
		order.setCustomer(null);
		this.getOrders().remove(order);
	}
	
	public void addWishlist(Wishlist wishlist) {
		wishlist.setCustomer(this);
		this.getWishlists().add(wishlist);
	}
	
	public void removeWishlist(Wishlist wishlist) {
		wishlist.setCustomer(null);
		this.getWishlists().remove(wishlist);
	}
	
	public void addCart(Cart cart) {
		cart.setCustomer(this);
		this.getCarts().add(cart);
	}
	
	public void removeCart(Cart cart) {
		cart.setCustomer(null);
		this.getCarts().remove(cart);
	}
	
	public void addCommonFeedback(CommonFeedback commonFeedback) {
		commonFeedback.setCustomer(this);
		this.getFeedbacks().add(commonFeedback);
	}
	
	public void removeCommonFeedback(CommonFeedback commonFeedback) {
		commonFeedback.setCustomer(null);
		this.getFeedbacks().remove(commonFeedback);
	}
	
	public void addAddress(Address address) {
		address.setCustomer(this);
		this.getAddresses().add(address);
	}
	
	public void removeAddress(Address address) {
		address.setCustomer(null);
		this.getAddresses().remove(address);
	}

	public void addProductFeedbacks(ProductFeedback productFeedbacks) {
		productFeedbacks.setCustomer(this);
		this.getProductFeedbacks().add(productFeedbacks);
	}
	
	public void removeProductFeedbacks(ProductFeedback productFeedbacks) {
		productFeedbacks.setCustomer(null);
		this.getProductFeedbacks().remove(productFeedbacks);
	}
	
	
	
	
	}
