package com.cg.capstore.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="TRANSACTIONS")
public class Transaction implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="TRANSACTION_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int transactionId;
	
	@Column(name="TRANSACTION_DATE")
	private Timestamp transactionDate;
	
	@Column(name="TRANSACTION_MONEY")
	private double transactionMoney;
	
	@Column(name="TRANSACTION_METHOD")
	@Pattern(regexp="Credit|Debit|UPI|Wallet")
	private String transactionMethod;
	
	@Column(name="TRANSACTION_STATUS")
	@Pattern(regexp="Success|Fail|Pending")
	private String transactionStatus;
	
	@OneToMany(mappedBy="transaction")
	private Set<Order> orders = new HashSet<Order>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="COUP_CODE")
	private Coupon coupon;
	
	public Transaction() {
		
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public Timestamp getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}

	public double getTransactionMoney() {
		return transactionMoney;
	}

	public void setTransactionMoney(double transactionMoney) {
		this.transactionMoney = transactionMoney;
	}

	public String getTransactionMethod() {
		return transactionMethod;
	}

	public void setTransactionMethod(String transactionMethod) {
		this.transactionMethod = transactionMethod;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	
	public void addOrder(Order order) {
		order.setTransaction(this);
		this.orders.add(order);
	}
	
	public void removeOrder(Order order) {
		order.setTransaction(null);
		this.orders.remove(order);
	}

	@JsonIgnore
	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
	
	
}
