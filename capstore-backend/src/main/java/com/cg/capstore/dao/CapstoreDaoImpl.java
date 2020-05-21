package com.cg.capstore.dao;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cg.capstore.entities.Address;
import com.cg.capstore.entities.Cart;
import com.cg.capstore.entities.Coupon;
import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.Order;
import com.cg.capstore.entities.Product;
import com.cg.capstore.entities.ProductFeedback;
import com.cg.capstore.entities.Transaction;
import com.cg.capstore.entities.Wishlist;

@Repository
public class CapstoreDaoImpl implements CapstoreDao{

	@Autowired
	EntityManager entityManager;
	
	
	@Override
	public int payForOrder(double amount) {
		
		String cmd="select customer from Customer customer";
		TypedQuery<CustomerDetails> q1=entityManager.createQuery(cmd,CustomerDetails.class);
		q1.getResultList();
		return 0;
	}


	@Override
	public List<Product> getAllProducts() {
		String command = "select prod from Product prod";
		TypedQuery<Product> query = entityManager.createQuery(command, Product.class);
		List<Product> products = query.getResultList();
 		return products;
	}
	
	@Override
	public List<Product> getAllProductsBySubCategory(String subCategory) {
		String command = "select prod from Product prod where prod.subCategory in (SELECT s.id from SubCategory s where s.name=:subCategory)";
		TypedQuery<Product> query = entityManager.createQuery(command, Product.class);
		query.setParameter("subCategory",subCategory);
		List<Product> products = query.getResultList();
			return products;
	}
	
	@Override
	public List<Product> getAllProductsByCategory(String category) {
		String command = "select prod from Product prod where prod.subCategory in (select s.id from SubCategory s where s.category in (select c.id from Category c where c.categoryName =:categoryName))";
		TypedQuery<Product> query = entityManager.createQuery(command, Product.class);
		query.setParameter("categoryName",category);
		List<Product> products = query.getResultList();
			return products;
	}
	
	@Override
	public Set<Product> similarProducts(int productId){
		Set<Product> set = new HashSet<Product>();
		Product product = findProduct(productId);
		String brand = product.getProductBrand();
		String qStr = "SELECT a from Product a where a.productBrand=:brand";
		TypedQuery<Product> query = entityManager.createQuery(qStr, Product.class);
		query.setParameter("brand",brand);
		set.addAll(query.getResultList());
		if(set.size()<5){
			qStr = "SELECT a from Product a where a.subCategory = :subCategory";
			query = entityManager.createQuery(qStr, Product.class);
			query.setParameter("subCategory",product.getSubCategory());
			set.addAll(query.getResultList());
		}
		
		//Select Prod_id from Products where sub_cat_id in (Select id from s_category where cat_id =1 ) ;
		if(set.size()<5){
			qStr = "SELECT a from Product a where a.subCategory in (Select s from SubCategory s where s.category =:category)";
			query = entityManager.createQuery(qStr, Product.class);
			query.setParameter("category",product.getSubCategory().getCategory());
			set.addAll(query.getResultList());
		}
		
		set.remove(product);
		return set;
	}
	

	
	@Override
	public List<Product> getAllProds(String username){
		CustomerDetails customer=findCustomer(username);
	
		String qStr = "SELECT a from Wishlist a where customer=:username ";
		TypedQuery<Wishlist> query = entityManager.createQuery(qStr, Wishlist.class);
		query.setParameter("username", customer);
		List<Wishlist> list=query.getResultList();
		List<Product> l=new LinkedList<Product>() ;
		for(Wishlist w:list) {
			l.add(w.getProduct());
		}
		return l;
		
	}

	@Override
	public boolean addWishlist(String username,int productId){
		Product product=findProduct(productId);
		CustomerDetails customer=findCustomer(username);
		Wishlist wishlist=new Wishlist();

		wishlist.setCustomer(customer);
		wishlist.setProduct(product);

		entityManager.persist(wishlist);
		return true;
	}
	
	@Override
	public void removeWishlist(String username,int productId){
		
		CustomerDetails customer=findCustomer(username);
		Product product=findProduct(productId);

		String qStr = "SELECT a from Wishlist a where customer=:customer and product=:product";
		TypedQuery<Wishlist> query = entityManager.createQuery(qStr, Wishlist.class);
		query.setParameter("customer", customer);
		query.setParameter("product", product);
		Wishlist wishlist=query.getSingleResult();
		entityManager.remove(wishlist);
		
	}

	

	@Override
	public void deleteAllWishlists(String username){
		CustomerDetails customer=findCustomer(username);

		String qStr = "SELECT a from Wishlist a where customer=:customer";
		TypedQuery<Wishlist> query = entityManager.createQuery(qStr, Wishlist.class);
		query.setParameter("customer", customer);
		List<Wishlist> list=query.getResultList();
		for(Wishlist wishlist:list) {
			entityManager.remove(wishlist);
		}
	}
	
	
	@Override
	public Product findProduct(int productId){
		String qStr = "SELECT a from Product a where productId=:productid";
		TypedQuery<Product> query = entityManager.createQuery(qStr, Product.class);
		query.setParameter("productid", productId);
		Product product=query.getSingleResult();
		return product;
	}
	
	
	@Override
	public List<Product> getSearchProducts(String prod_name)
	{
		ArrayList<Product> products=new ArrayList<Product>();
		ArrayList<Product> allProducts=new ArrayList<Product>();
		allProducts.addAll(getAllProducts());
		for(int i=0;i<allProducts.size();i++) {
    	   if(allProducts.get(i).getProductName().substring(0,prod_name.length()).equals(prod_name))
    			   {
    		   products.add(allProducts.get(i));
    			   }
       }
 		return products;
	}
	
	
	
	@Override
	public List<ProductFeedback> getFeedbacksById(int productId){
		String qStr = "SELECT f from ProductFeedback f where product=:prod ";
		TypedQuery<ProductFeedback> query = entityManager.createQuery(qStr, ProductFeedback.class);
		query.setParameter("prod", entityManager.find(Product.class, productId));
		List<ProductFeedback> list=query.getResultList();
		return list;
	}

	@Override
	public Set<Order> getAllOrders(String username){
		CustomerDetails customer=findCustomer(username);
		return customer.getOrders();			
	}
	
	@Override
	public List<Address> findAddress(String  username)
	{
		String qStr = "SELECT a from Address a where customer=:username ";
		TypedQuery<Address> query = entityManager.createQuery(qStr, Address.class);
		query.setParameter("username", findCustomer(username));
		List<Address> add=query.getResultList();
		return add;			
		
		
	}

	@Override
	public int addAddress(Address add,String username) {
		CustomerDetails customer=findCustomer(username);
		add.setCustomer(customer);
		entityManager.merge(add);
		return 1;
	}
	
	@Override
	public Address findOneAddress(int id)
	{
		String qStr = "SELECT a from Address a where addressId=:id ";
		TypedQuery<Address> query = entityManager.createQuery(qStr, Address.class);
		query.setParameter("id", id);
		Address add=query.getSingleResult();
		return add;			
	}
	
	@Override
	public int placingOrder(CustomerDetails customer, Order order, Transaction transaction) {
		
		entityManager.merge(customer);
		entityManager.persist(transaction);
		entityManager.persist(order);
		return 1;
		
	}
	

	@Override
	public List<Product> getCartProducts(int[] id,int[] quantity){
		List<Product> list=new LinkedList<Product>();
		for(int i=0;i<id.length;i++) {
			Product p=getProduct(id[i]);
			p.setNoOfProducts(quantity[i]);
			list.add(p);
		}
		return list;
	}
	
	@Override
	public void placingCartOrder( Order order) {
			entityManager.persist(order);
	}
		
	@Override
	public int placingCartOrder(CustomerDetails customer,  Transaction transaction) {
		
		entityManager.merge(customer);
		entityManager.persist(transaction);
		return transaction.getTransactionId();
	}


	
	
	@Override
	public void mergeAmount(Order order) {
		entityManager.merge(order);
	}



	@Override
	public boolean addMoneyToWallet(String username,int balance) {
		CustomerDetails customer=entityManager.find(CustomerDetails.class, username);
		customer.setBalance(customer.getBalance()+balance);
		entityManager.merge(customer);
		return true;
	}
	
	@Override
	public boolean addProductToCart(Cart cart,String userName) {
		CustomerDetails customer=entityManager.find(CustomerDetails.class, userName);
		cart.setCustomer(customer);
		entityManager.persist(cart);
		return true;
	}
	
	@Override
	public List<Cart> getCustomerCart(String userName){
		CustomerDetails customer=entityManager.find(CustomerDetails.class, userName);
		String command="select cart from Cart cart where customer=:customer";
		TypedQuery<Cart> query=entityManager.createQuery(command,Cart.class);
		query.setParameter("customer", customer);
		List<Cart> cart=query.getResultList();
		return cart;
		
	}

	@Override
	public boolean removeProductFromCart(int cartId) {
		Cart cart=entityManager.find(Cart.class, cartId);
		entityManager.remove(cart);
		return true;
	}

	@Override
	public List<Coupon> getAllCoupons(String UserName) {
		String command = "select c from Coupon c where c.user.username=:userName AND c.isActive = TRUE";
		TypedQuery<Coupon> query = entityManager.createQuery(command, Coupon.class);
		query.setParameter("userName", UserName);
		List<Coupon> coupons = getAllCouponsByAdmin();
		coupons.addAll(query.getResultList());
		return coupons;
	}
	
	@Override
	public List<Coupon> getAllCouponsByAdmin() {
		String command = "select c from Coupon c where c.issuedBy=:Admin AND c.isActive = TRUE";
		TypedQuery<Coupon> query = entityManager.createQuery(command, Coupon.class);
		String admin="Admin";
		query.setParameter("Admin", admin);
		List<Coupon> coupons = query.getResultList();
		return coupons;
	}

	@Override
	public Order getOrder(long orderId) {
		return entityManager.find(Order.class, orderId);	
	}

	@Override
	public Coupon getCouponById(String couponCode) {
		return entityManager.find(Coupon.class, couponCode);	
	}

	
	
	@Override
	public List<ProductFeedback> getFeedbacksbyId(int id){
		String command = "select f from ProductFeedback f where product=:prod";
		TypedQuery<ProductFeedback> query = entityManager.createQuery(command, ProductFeedback.class);
		query.setParameter("prod", entityManager.find(Product.class, id));
		List<ProductFeedback> list = query.getResultList();
		return list;
	}
	
	

	@Override
	public CustomerDetails findCustomer(String username){
		String qStr = "SELECT a from CustomerDetails a where username=:username ";
		TypedQuery<CustomerDetails> query = entityManager.createQuery(qStr, CustomerDetails.class);
		query.setParameter("username", username);
		CustomerDetails customer=query.getSingleResult();
		return customer;			
	}
	
	
	@Override
	public Product getProduct(int prod_id) {
	
		String command = "select prod from Product prod where productId=:id";
		TypedQuery<Product> query = entityManager.createQuery(command,Product.class);
		query.setParameter("id", prod_id);
		Product product = query.getSingleResult();
		return product;
	}

	@Override
	public Transaction getTransactionDetails(int id) {
		
		Transaction transaction = entityManager.find(Transaction.class, id);
		return transaction;
		
	}

	@Override
	public Set<Order> getOrderDetailsForInvoice(int id) {
		Transaction transaction = entityManager.find(Transaction.class, id);
		Set<Order> set= transaction.getOrders();
	
		return set;
	}

	@Override
	public Address getAddressDetailsForInvoice(int id) {
		Transaction transaction = entityManager.find(Transaction.class, id);
		Set<Order> set= transaction.getOrders();
		Order b = null;
		for(Order a:set)
		{
			 b=a;
			
			break;
		}
		
		return b.getAddress();
	}

	@Override
	public CustomerDetails getCustomerDetailsForInvoice(int id) {
		Transaction transaction = entityManager.find(Transaction.class, id);
		Set<Order> set = transaction.getOrders();
		
		Order b = null;
		for(Order a:set)
		{
			 b = a;
			
			break;
		}
		
		return b.getCustomer();

}

	@Override
	public Set<Product> getProductDetailsForInvoice(int id) {
		Transaction transaction = entityManager.find(Transaction.class, id);
		Set<Order> set = transaction.getOrders();
		Set<Product> s = new HashSet<Product>();
		for(Order a:set)
		{
			s.add(a.getProduct());
		}
		return s;
	}


	
}
