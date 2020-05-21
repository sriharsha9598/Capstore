package com.cg.capstore.service;
 	

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.capstore.dao.CapstoreDaoImpl;
import com.cg.capstore.entities.Address;
import com.cg.capstore.entities.Cart;
import com.cg.capstore.entities.Coupon;
import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.MerchantDetails;
import com.cg.capstore.entities.Order;
import com.cg.capstore.entities.Product;
import com.cg.capstore.entities.ProductFeedback;
import com.cg.capstore.entities.Transaction;

@Service
@Transactional
public class CapstoreServImpl implements CapstoreService{
	
	@Autowired
	CapstoreDaoImpl dao;
	
		
	@Override
	public List<Product> getAllProducts() {
	
		return dao.getAllProducts();
	}

	@Override
	public List<Product> getAllProductsBySubCategory(String subCategory) {
	
		return dao.getAllProductsBySubCategory(subCategory);
	}
	
	
	@Override
	public List<Product> getAllProductsByCategory(String category) {
	
		return dao.getAllProductsByCategory(category);
	}
	
	
	public Set<Product> products(int id) {
		return dao.similarProducts(id);
	}
	
	
	@Override
	public boolean addWishlist(String username, int productId) {
		
		return dao.addWishlist(username, productId);
	}

	
	@Override
	public boolean removeWishlist(String username, int productId) {
		dao.removeWishlist(username, productId);
		return true;
	}

	@Override
	public boolean deleteAllWishlists(String username) {
		dao.deleteAllWishlists(username);
		return true;
	}
	
	@Override
	public List<Product> getAllProds(String username) {
		
		return dao.getAllProds(username);
	}
	
	@Override
	public List<Product> getSearchProducts(String search)
	{
		return dao.getSearchProducts(search);
	}
	
	


	@Override
	public List<ProductFeedback> getFeedbacksById(int productId){
		return dao.getFeedbacksById(productId);
	}



		
	@Override
	public int addAddress(Address address, String username) {
		return dao.addAddress(address, username);
	}


	@Override
	public Set<Order> getAllOrders(String username) {
	// TODO Auto-generated method stub
	return dao.getAllOrders(username);
}
	
	@Override
	public List<Address> getAddrByName(String username) {
		return dao.findAddress(username);
	}
	
	@Override
	public List<Product> getCartProducts(int[] id,int[] quantity){
		return dao.getCartProducts(id, quantity);
	}
	

	@Override
	public List<Coupon> getAllCoupons(int[] productIds){
		
		List<Coupon> coupons=new ArrayList<Coupon>();
		Set<MerchantDetails> merchantSet=new HashSet<MerchantDetails> ();
		
		for(int i=0;i<productIds.length;i++) {
			merchantSet.add(dao.getProduct(productIds[i]).getMerchant());
		}
		if(merchantSet.size()>1) {
			return dao.getAllCouponsByAdmin();
		} 
	    coupons.addAll(getAllCoupons(productIds[0]));
	    return coupons;
	}

	@Override
	public List<Coupon> getAllCoupons(int productId){
		String UserName=dao.getProduct(productId).getMerchant().getUsername();
		return dao.getAllCoupons(UserName);
	}
	
	@Override
	public int validateCoupon(String couponCode,int amount){
		System.out.println(couponCode);
		Coupon coupon=dao.getCouponById(couponCode);
		System.out.println(coupon.getCouponCode());
		System.out.println(coupon.isActive());
		System.out.println(coupon.getCouponEndDate());
		Timestamp ts = new Timestamp(new Date().getTime());
		
		if(!coupon.isActive()) {
			return 0;
		}
		if(coupon.getCouponEndDate().after(ts) && coupon.getCouponStartDate().before(ts)) {
			if(amount>=coupon.getMinOrderAmount()) {
				return coupon.getCouponAmount();
			}
			else {
				return 0;
			}
		}
		else {
			return 0;
		}
	}

	
	@Override
	public boolean addMoneyToWallet(String username,int balance) {
		return dao.addMoneyToWallet(username,balance);
	}
	
	@Override
	public boolean addProductToCart(Cart cart,String userName) {
		return dao.addProductToCart(cart, userName);
	}
	
	@Override
	public List<Cart> getCustomerCart(String userName){
		return dao.getCustomerCart(userName);
	}

	@Override
	public boolean removeProductFromCart(int cartId) {
		return dao.removeProductFromCart(cartId);
	}
			@Override
	public Product getProduct(int prod_id) {
		return dao.getProduct(prod_id);
	}
	
	@Override
	public CustomerDetails findCustomer(String userName) {
		return dao.findCustomer(userName);
	}


	@Override
	public Transaction getTransactionDetails(int id) {
		
		return dao.getTransactionDetails(id);
	}

	@Override
	public Set<Order> getOrderDetailsForInvoice(int id) {
		return dao.getOrderDetailsForInvoice(id);
	}

	@Override
	public Address getAddressDetailsForInvoice(int id) {
		
		return dao.getAddressDetailsForInvoice(id);
	}

	@Override
	public CustomerDetails getCustomerDetailsForInvoice(int id) {
		return dao.getCustomerDetailsForInvoice(id);
	}

	@Override
	public Set<Product> getProductDetailsForInvoice(int id) {
		return dao.getProductDetailsForInvoice(id);
	}
	
	
	public int placeOrder(String userName, int productId,int quantity,double amount, int addrId) {
		Product product = getProduct(productId);
		CustomerDetails customer = findCustomer(userName);
		Order order = new Order();
		Transaction transaction = new Transaction();
	

		if(amount <= customer.getBalance())
		{
			customer.setBalance(customer.getBalance() - (int)product.getProductPrice());
			
			
			order.setCustomer(customer);
			order.setProduct(product);
			order.setAddress(dao.findOneAddress(addrId));
			order.setOrderAmount( amount);
			order.setOrderStatus("Placed");
			Date date=new Date();	
			long time=date.getTime();
			@SuppressWarnings("deprecation")
			Timestamp ts=new Timestamp(time);  
			order.setOrderDate(ts);
			transaction.setTransactionDate(ts); 
			transaction.setTransactionMoney((int)amount);
			transaction.setTransactionStatus("Success");
			transaction.setTransactionMethod("Wallet");
				
			order.setTransaction(transaction);
			order.setQuantity(quantity);
			
			if(dao.placingOrder(customer,  order, transaction)==1) {
					return transaction.getTransactionId();
			}
			else
				return 0;
			}
		else
		{
			return 0;
		}
		}
		@Override
		public int placingCartOrder(String username,int[] productId, int[] quantity,double price,int addrId) {
			Product product = new Product();
			Transaction transaction = new Transaction();
			CustomerDetails customer = findCustomer(username);
			Address addr = dao.findOneAddress(addrId);
			if( price <= customer.getBalance())
			{
				Date date=new Date();	
				long time=date.getTime();
				@SuppressWarnings("deprecation")
				Timestamp ts=new Timestamp(time);  
				transaction.setTransactionDate(ts);
				transaction.setTransactionMoney(price);
				customer.setBalance(customer.getBalance()-(int)price);
				transaction.setTransactionStatus("Success");
				transaction.setTransactionMethod("Wallet");
				dao.placingCartOrder(customer, transaction);
				for(int i=0;i<productId.length;i++) {
					Order order=new Order();
					product=getProduct(productId[i]);
					order.setCustomer(customer);
					order.setProduct(product);
					order.setAddress(addr);
					order.setOrderDate(ts);
					order.setOrderAmount( product.getProductPrice()* quantity[i]);
					order.setOrderStatus("Placed");
					order.setTransaction(transaction);
					order.setQuantity(quantity[i]);
					dao.placingCartOrder(order);
					}
				
					return transaction.getTransactionId();
			}
			else
				return 0;
	}

	
}
