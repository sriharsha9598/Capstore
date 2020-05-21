package com.cg.capstore.entities;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="PRODUCTS")
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="PROD_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;
	
	@Column(name="PROD_NAME")
	private String productName;
	
	@Column(name="PROD_IMAGE")
	private String productImage;
	
	@Column(name="PROD_PRICE")
	private double productPrice;
	
	@Column(name="PROD_RATING")
	private int productRating;
	
	@Column(name="PROD_VIEWS")
	private int noOfViews;
	
	@Column(name="PROD_BRAND")
	private String productBrand;
	
	@Column(name="PROD_COUNT")
	private int noOfProducts;
	
	@Column(name="PROD_INFO",length=4000)
	private String productInfo;
	
	@Column(name="PROD_DISC")
	private int discount;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="SUB_CAT_ID")
	private SubCategory subCategory;
	
	@Column(name="PRODUCT_IS_ACTIVATED")
	private boolean productActivated;
	
	@Column(name="FEATURED")
	private boolean isFeatured;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="M_USERNAME")
	private MerchantDetails merchant;
	
	@OneToMany(mappedBy="product")
	private Set<ProductFeedback> feedbacks = new HashSet<>();


	public int getProductId() {
		return productId;
	}


	
	public int getDiscount() {
		return discount;
	}



	public void setDiscount(int discount) {
		this.discount = discount;
	}



	public void setProductId(int productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductImage() {
		return productImage;
	}


	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}


	public double getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}


	public int getProductRating() {
		return productRating;
	}


	public void setProductRating(int productRating) {
		this.productRating = productRating;
	}


	public int getNoOfViews() {
		return noOfViews;
	}


	public void setNoOfViews(int noOfViews) {
		this.noOfViews = noOfViews;
	}


	public String getProductBrand() {
		return productBrand;
	}


	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}


	public int getNoOfProducts() {
		return noOfProducts;
	}


	public void setNoOfProducts(int noOfProducts) {
		this.noOfProducts = noOfProducts;
	}


	public String getProductInfo() {
		return productInfo;
	}


	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}

	public boolean isProductActivated() {
		return productActivated;
	}


	public void setProductActivated(boolean productActivated) {
		this.productActivated = productActivated;
	}


	@JsonIgnore
	public Set<ProductFeedback> getFeedbacks() {
		return feedbacks;
	}


	public void setFeedbacks(Set<ProductFeedback> feedbacks) {
		this.feedbacks = feedbacks;
	}


	public boolean isFeatured() {
		return isFeatured;
	}


	public void setFeatured(boolean isFeatured) {
		this.isFeatured = isFeatured;
	}


	public SubCategory getSubCategory() {
		return subCategory;
	}


	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}


	public MerchantDetails getMerchant() {
		return merchant;
	}


	public void setMerchant(MerchantDetails merchant) {
		this.merchant = merchant;
	}

	public void addFeedback(ProductFeedback feedback) {
		feedback.setProduct(this);
		this.getFeedbacks().add(feedback);
		
	}
	
	public void removeFeedback(ProductFeedback feedback) {
		feedback.setProduct(null);
		this.getFeedbacks().remove(feedback);
		
	}


	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productImage=" + productImage
				+ ", productPrice=" + productPrice + ", productRating=" + productRating + ", noOfViews=" + noOfViews
				+ ", productBrand=" + productBrand + ", noOfProducts=" + noOfProducts + ", productInfo=" + productInfo
				+ ", subCategory=" + subCategory + ", productActivated=" + productActivated + ", isFeatured="
				+ isFeatured + ", merchant=" + merchant + ", feedbacks=" + feedbacks + "]";
	}
	
	
}
