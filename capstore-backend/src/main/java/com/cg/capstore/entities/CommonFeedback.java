package com.cg.capstore.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="COMMON_FEEDBACKS")
public class CommonFeedback implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="FEEDBACK_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int feedbackId;	
	
	@Column(name="FEEDBACK_SUBJECT")
	private String feedbackSubject;
	
	@Column(name="FEEDBACK_MESSAGE")
	private String feedbackMessage;
	
	@ManyToOne
	@JoinColumn(name="C_USERNAME")
	private CustomerDetails customer;
	
	@ManyToOne
	@JoinColumn(name="M_USERNAME")
	private MerchantDetails merchant;
	
	@Column(name="EN_READ")
	private boolean enableRead;
	
	@Column(name="M_RESPONSE")
	private String response;
	
	public CommonFeedback() {
		
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

	public CustomerDetails getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDetails customer) {
		this.customer = customer;
	}

	public MerchantDetails getMerchant() {
		return merchant;
	}

	public void setMerchant(MerchantDetails merchant) {
		this.merchant = merchant;
	}

	public boolean isEnableRead() {
		return enableRead;
	}

	public void setEnableRead(boolean enableRead) {
		this.enableRead = enableRead;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
	
	
}
