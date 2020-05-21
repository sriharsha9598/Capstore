package com.cg.capstore.dao;

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

public interface CapstoreDao {

	int payForOrder(double amount);

	Product getProduct(int prod_id);

	List<Product> getAllProductsBySubCategory(String subCategory);
	List<Product> getAllProductsByCategory(String category);
	List<Product> getAllProducts();
	
	public List<Product> getAllProds(String username);
	public boolean addWishlist(String username,int productId);
	public void removeWishlist(String username,int productId);
	public void deleteAllWishlists(String username);

	public CustomerDetails findCustomer(String username);
	public Product findProduct(int productId);
	
	List<Product> getSearchProducts(String search);

	Set<Product> similarProducts(int productId);

	Set<Product> getProductDetailsForInvoice(int id);

	CustomerDetails getCustomerDetailsForInvoice(int id);

	Address getAddressDetailsForInvoice(int id);

	Set<Order> getOrderDetailsForInvoice(int id);

	Transaction getTransactionDetails(int id);

	int placingCartOrder(CustomerDetails customer, Transaction transaction);

	void placingCartOrder(Order order);

	Address findOneAddress(int id);

	int placingOrder(CustomerDetails customer, Order order, Transaction transaction);

	List<ProductFeedback> getFeedbacksbyId(int id);

	Coupon getCouponById(String couponCode);

	Order getOrder(long orderId);

	List<Coupon> getAllCouponsByAdmin();

	List<Coupon> getAllCoupons(String UserName);

	boolean removeProductFromCart(int cartId);

	List<Cart> getCustomerCart(String userName);

	boolean addProductToCart(Cart cart, String userName);

	boolean addMoneyToWallet(String username, int balance);

	void mergeAmount(Order order);

	List<Product> getCartProducts(int[] id, int[] quantity);

	int addAddress(Address add, String username);

	List<Address> findAddress(String username);

	Set<Order> getAllOrders(String username);

	List<ProductFeedback> getFeedbacksById(int productId);

}



