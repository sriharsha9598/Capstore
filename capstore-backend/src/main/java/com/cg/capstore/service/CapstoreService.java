package com.cg.capstore.service;

import java.util.List;
import java.util.Set;

import com.cg.capstore.entities.Address;
import com.cg.capstore.entities.Cart;
import com.cg.capstore.entities.Coupon;
import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.Order;
import com.cg.capstore.entities.Product;
import com.cg.capstore.entities.ProductFeedback;
import com.cg.capstore.entities.Transaction;


public interface CapstoreService {

	Product getProduct(int prod_id);
	
	List<Product> getAllProductsBySubCategory(String subCategory);
	
	List<Product> getAllProductsByCategory(String category);
	
	List<Product> getAllProducts();
	
	public boolean addWishlist(String username,int productId);
	
	public boolean removeWishlist(String username,int productId);
	
	public boolean deleteAllWishlists(String username);
	
	public List<Product> getAllProds(String username);
	
	List<Product> getSearchProducts(String search);

	List<ProductFeedback> getFeedbacksById(int productId);

	Set<Order> getAllOrders(String username);

	int addAddress(Address address, String username);
	
	List<Address> getAddrByName(String username);
	
	List<Product> getCartProducts(int[] id, int[] quantity);

	Set<Product> getProductDetailsForInvoice(int id);

	CustomerDetails getCustomerDetailsForInvoice(int id);

	Address getAddressDetailsForInvoice(int id);

	Set<Order> getOrderDetailsForInvoice(int id);

	Transaction getTransactionDetails(int id);

	int placingCartOrder(String username, int[] productId, int[] quantity, double price, int addrId);

	boolean removeProductFromCart(int cartId);

	List<Cart> getCustomerCart(String userName);

	boolean addProductToCart(Cart cart, String userName);

	boolean addMoneyToWallet(String username, int balance);

	int validateCoupon(String couponCode, int amount);

	List<Coupon> getAllCoupons(int productId);

	List<Coupon> getAllCoupons(int[] productIds);

	CustomerDetails findCustomer(String userName);
	


}
