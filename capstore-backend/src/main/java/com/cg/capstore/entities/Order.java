package com.cg.capstore.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="ORDERS")
public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ORDER_ID")
	private long orderId;
	
	@Column(name="ORDER_AMOUNT")
	private double orderAmount;	
	
	@Column(name="ORDER_STATUS")
	@Pattern(regexp="Placed|Accepted|Shipped|(Out For Delivery)|Delivered|(Request For Return)|Returned|Refunded|Cancelled")
	private String orderStatus;
	
	@Column(name="ORDER_DATE")
	private Timestamp orderDate;
	
	@Column(name="STATUS_DATE")
	private Timestamp statusDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="TRANS_ID")
	private Transaction transaction;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ADDRESS_ID")
	private Address address;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="USERNAME")
	private CustomerDetails customer;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PRODUCT_ID")
	private Product product;
	
	@Column(name="QUANTITY")
	private int quantity;
	
	public Order() {
		
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}


	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public Timestamp getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Timestamp statusDate) {
		this.statusDate = statusDate;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public CustomerDetails getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDetails customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
