package com.cg.capstore.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PRODUCT_FEEDBACKS")
public class ProductFeedback implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="FEEDBACK_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int feedbackId;
	
	@Column(name="FEEDBACK_SUBJECT")
	private String feedbackSubject;
	
	@Column(name="FEEDBACK_MESSAGE")
	private String feedbackMessage;

    @Column(name="RATING")
    private int rating;
        
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PRODUCT_ID")
	private Product product;
	
	@ManyToOne(fetch =  FetchType.EAGER)
	@JoinColumn(name="USERNAME")
	private CustomerDetails customer;
	
	public ProductFeedback() {
		
	}

	public int getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getFeedbackSubject() {
		return feedbackSubject;
	}

	public void setFeedbackSubject(String feedbackSubject) {
		this.feedbackSubject = feedbackSubject;
	}

	public String getFeedbackMessage() {
		return feedbackMessage;
	}

	public void setFeedbackMessage(String feedbackMessage) {
		this.feedbackMessage = feedbackMessage;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public CustomerDetails getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDetails customer) {
		this.customer = customer;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
	

}
