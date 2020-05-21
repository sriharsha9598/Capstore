package com.cg.capstore;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.capstore.entities.Address;
import com.cg.capstore.entities.Cart;
import com.cg.capstore.entities.Coupon;
import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.Order;
import com.cg.capstore.entities.Product;
import com.cg.capstore.entities.ProductFeedback;
import com.cg.capstore.entities.Transaction;
import com.cg.capstore.service.CapstoreServImpl;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CapstoreController {

	
	
		@Autowired
		CapstoreServImpl service;
		
		
		/**
		 * Description : getting all products by sub category 
		 * @param subCategory
		 * @return
		 */
		@GetMapping("/getAllProdBySubCategory/{subCategory}")
		public List<Product>  getAllProductsBySubCategory(@PathVariable  String subCategory)
		{
			return service.getAllProductsBySubCategory(subCategory);
			
		}
		
		
		/**
		 * Description : getting all feedbacks by product id 
		 * @param productId: the id of a product
		 * @return
		 */
		@GetMapping("/getFeedbacksById/{productId}")
		public List<ProductFeedback>  getFeedbacksById(@PathVariable int productId)
		{
			return service.getFeedbacksById(productId);
		}
		
		
		
		/**
		 * Description : getting all the products by main category 
		 * @param category
		 * @return
		 */
		@GetMapping("/getAllProdByCategory/{category}")
		public List<Product>  getAllProductsByCategory(@PathVariable  String category)
		{
			return service.getAllProductsByCategory(category);
			
		}
		
		
		
		/**
		 * Description : gets all the feedbacks for each product by product id
		 * @param productId - product Id of the product
		 * @return
		 */

		@GetMapping("/getFeedbacksByProdId/{productId}")
		public List<ProductFeedback>  getFeedbacksByProdId(@PathVariable int productId)
		{
			return service.getFeedbacksById(productId);
			
		}
		
		
		
		/**
		 * Description : Gets All the products available 
		 * @return
		 */
		@GetMapping("/getAllProd/")
		public List<Product>  getAllProducts()
		{
			return service.getAllProducts();
			
		}
		
		
		
	
		
		/**
		 * Description : Gets all the similar products according to categories and sub categories of the product using 
		 * 				 product Id which is on show in the display page
		 * @param id   - product Id using which comparison is done
		 * @return
		 */
		@GetMapping("/getSimilarProd/{id}")
		public Set<Product> getProducts(@PathVariable Integer id){
			return service.products(id);
		}
		
		
		/**
		 * Description : Gets all the wish listed items of a particular user
		 * @param username 
		 * @return
		 */
	
		@GetMapping("/getAllWishlists/{username}")
		public List<Product> getWishlistedproducts(@PathVariable String username) {
			return service.getAllProds(username);
		}
		
		
		
		/**
		 * Description : Adding a specific product to the wish list using product Id for a user
		 * @param username 
		 * @param productId - product id of a product which is to be added
		 * @return
		 */
		@GetMapping("/addWishlist/{username}/{productId}")
		public boolean addWishlist(@PathVariable String username,@PathVariable int productId) {
			return service.addWishlist(username,productId);
		}
		
		
		/**
	-	 * Description : Removing a product from wishList using product Id
		 * @param username
		 * @param productId
		 * @return
		 */
		@GetMapping("/removeWishlist/{username}/{productId}")
		public boolean removeWishlist(@PathVariable String username,@PathVariable int productId) {
			service.removeWishlist(username,productId);
			return true;
		}
		
		
		/**
		 * Description : Gives an option to user to delete all the wish listed items
		 * @param username
		 * @return
		 */
		@GetMapping("/deleteAllWishlists/{username}")
		public boolean deleteAllWishlists(@PathVariable String username) {
			service.deleteAllWishlists(username);
			return true;
		}
		
		
		
		/**
		 * Description : Searching using letters entered by the user in the search box
		 * @param search - search string 
		 * @return
		 */
		
		@GetMapping("/getSearchProducts/{search}")
		public List<Product> getSearchProducts(@PathVariable String search)
		{
			return service.getSearchProducts(search);
		}
		
		
		/**
		 * Method Name :  findCustomer()
		 * Description : find a specific customer using user name
		 * @param userName
		 * @return
		 */
		@GetMapping ("/getCust/{userName}")
		public  CustomerDetails findCustomer(@PathVariable String userName)
		{
			return service.findCustomer(userName);
		}
		

		/**
		 * Description : list of all addresses saved  under the customer
		 * @param username
		 * @return
		 */
		@GetMapping("/getAddrByName/{username}")
		public List<Address> getAddrByName(@PathVariable String username)
		{
			return service.getAddrByName(username);
		}
		
		
		
		/**
		 * Description : Add New Address for specific customer
		 * @param add  : Address body
		 * @param username
		 * @return
		 */

		@PostMapping("/addAddress/{username}")
		public int addAddress(@RequestBody Address add,@PathVariable String username) {
			return service.addAddress(add,username);
		}
		
		
		/**
		 * Description : Gets the whole Product object using product Id 
		 * @param productId : Product Id through which we have to get whole product
		 * @return
		 */
		@GetMapping("/getProd/{productId}")
		public Product getProduct(@PathVariable int productId)
		{
			return service.getProduct(productId);
		}
		
		
	
		
		/**
		 * Method Name : placeOrder()
		 * Return value : integer
		 * Description : Placing order for customer using order id and address id
		 * NOTE : order applying for single product coming from BUY NOW option of PRODUCTS PAGE
		 *
		 * @param userName
		 * @param productId - product id
		 * @param addrId    - address id
		 * @return
		 */
		@GetMapping("/placeOrder/{userName}/{productId}/{quantity}/{amount}/{addrId}")
		public int placeOrder( @PathVariable String userName, @PathVariable int productId, @PathVariable int quantity, @PathVariable double amount, @PathVariable int addrId)
		{
			return service.placeOrder(userName, productId ,quantity,amount, addrId);
		}
		
	
		
		/**
		 * Description : Get the list of all products under cart
		 * @param id  - product Id's in the cart
		 * @param quantity - quantities in the cart
		 * @return
		 */
		
		@GetMapping("/getCartOrders/{id}/{quantity}")
		public List<Product> getCartProducts(@PathVariable int[] id,@PathVariable int[] quantity){
			return service.getCartProducts(id, quantity);
		}
		
	
		
		/**
		 * Method Name : PlacingCartOrder() 
		 * Description : when called from cart page with list of items
		 * 				 Placing order for list of all items from the cart using
		 *
		 * @param username 
		 * @param productId  -  arrays of product id's of all products,
		 * @param quantity   -  arrays of quantities of each product,
		 * @param price		 -  total  sum of prices for cart products
		 * @param addrId	 -  Address Id selected for product delivery
		 * @return
		 */
		@GetMapping("/placeCartOrders/{username}/{productId}/{quantity}/{price}/{addrId}")
		public int placingCartOrder(@PathVariable String username,@PathVariable int[] productId,@PathVariable int[] quantity,
									@PathVariable double price,@PathVariable int addrId) {
			return service.placingCartOrder(username, productId, quantity, price, addrId);
		}
		
		

	
		/**
		 * Description : Gets all the list of all orders under a specific user
		 * @param username
		 * @return
		 */
		@GetMapping ("/getOrders/{username}")
		public Set<Order> getAllOrders(@PathVariable String username){
			return service.getAllOrders(username);			
		}

	
	
		/**
		 * Description : adding money to the wallet for a specific user
		 * @param username
		 * @param balance
		 * @return
		 */
		@PutMapping("/addMoneyToWallet/{username}")
		public boolean addMoneyToWallet(@PathVariable("username") String username,@RequestBody int balance) {
			return service.addMoneyToWallet(username,balance);
		}
		
		
		
		/**
		 * Description : adding a product to the cart using user name
		 * @param userName
		 * @param cart 
		 * @return
		 */
		@PostMapping("/addProductTocart/{userName}")
		public boolean addProductTocart(@PathVariable("userName") String userName,@RequestBody Cart cart) {
			return service.addProductToCart(cart, userName);
		}
		
		/**
		 * Description : Get the list of all items in the cart using user name
		 * @param userName
		 * @return
		 */
		@GetMapping("/getCustomerCart/{userName}")
		public ResponseEntity<List<Cart>> getCustomerCart(@PathVariable("userName") String userName){
			List<Cart> cart=service.getCustomerCart(userName);
			return new ResponseEntity<List<Cart>>(cart,HttpStatus.OK);
		}
		
		
		
		/**
		 * Description : Removing a single product from the cart
		 * @param cartId
		 * @return
		 */
		@DeleteMapping("/removeProductFromCart/{cartId}")
		public boolean removeProductFromCart(@PathVariable("cartId") int cartId) {
			return service.removeProductFromCart(cartId);
		}


		/**
		 * Description  - To get the corresponding list coupons for the product. 
		 * @param   productId    - Id for the product.
		 * @return  List<Coupon>. 
		 */
		@GetMapping("/getAllCouponsById/{productId}")
		public  List<Coupon> getAllCouponsById(@PathVariable int productId){
			return service.getAllCoupons(productId);
		}
		
		/**
		 * Description  - To get the corresponding list coupons for the array of products. 
		 * @param   productId    - Array of Ids for the products.
		 * @return  List<Coupon>. 
		 */
		@GetMapping("/getCoupons/{productIds}")
		public  List<Coupon> getAllCoupons(@PathVariable int[] productIds){
			return service.getAllCoupons(productIds);
		}
		
		/**
		 * Description  - To validate the Coupon. 
		 * @param   couponCode   - Id for the Coupon.
		 * @param   amount    	 - Order Amount for validation of Coupon.
		 * @return  int. 
		 */
		@GetMapping("/validateCoupon/{couponCode}/{amount}")
		public  int validateCoupon(@PathVariable String couponCode,@PathVariable int amount){
			return service.validateCoupon(couponCode, amount);
		}
		

		/**
		 * Description : Get all the orders for a specific transacion
		 * @param id - Transaction Id
		 * @return
		 */
		@GetMapping("/getOrdersForInvoice/{id}")
		public Set<Order> getOrderDetailsForInvoice(@PathVariable int id){
			
			return service.getOrderDetailsForInvoice(id);
		}
		
		
		/**
		 * Description : Get the address details for a specific transaction
		 * @param id - Transaction Id
		 * @return
		 */
		@GetMapping("/getAddressForInvoice/{id}")
		public Address getAddressDetailsForInvoice(@PathVariable int id) {
		
			return service.getAddressDetailsForInvoice(id);
		}
		
		
		/**
		 * Description : Get the Customer Details for a specific transaction
		 * @param id  - Transaction Id
		 * @return
		 */
		@GetMapping("/getCustomerForInvoice/{id}")
		public CustomerDetails getCustomerDetailsForInvoice(@PathVariable int id) {
		
			return service.getCustomerDetailsForInvoice(id);
		}
		
		
		
		/**
		 * Description : Gets the products details for a specific transaction
		 * @param id - Transaction Id
		 * @return
		 */
		@GetMapping("/getProductForInvoice/{id}")
			public Set<Product> getProductDetailsForInvoice(@PathVariable int id) {
				
				return service.getProductDetailsForInvoice(id);
			}

		/**
		 * Description : Gets the transaction details for a specific transaction
		 * @param id - Transaction Id
		 * @return
		 */
		@GetMapping("/getTransactionDetails/{id}")
			public Transaction getTransactionsDetailsForInvoice(@PathVariable int id) {
				
				return service.getTransactionDetails(id);
			}

}

