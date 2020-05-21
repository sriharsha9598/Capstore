package com.cg.capstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.cg.capstore.entities.Product;
import com.cg.capstore.service.CapstoreServImpl;



@SpringBootTest
class CapstoreApplicationTests {

	@Autowired
	CapstoreServImpl service;
	
	String userName="Prithve";
	Integer productId= 1018;
	String category = "Clothes";
	String subCategory = "Mobiles";
	String search= "Asus 6Z";

	@Test
	void test1() {
		boolean added = service.addWishlist(userName, productId);
		assertEquals(true, added);
	}
	

	@Test
	void test2() {
		assertEquals(true, service.getAllProds(userName).size()>0);
	}

	@Test
	void test3() {
		boolean removed = service.removeWishlist(userName, productId);
		assertEquals(true, removed);
	}
	@Test
	void test9() {
		boolean deleted = service.deleteAllWishlists(userName);
		assertEquals(true, deleted);
	}
	@Test
	void test4() {
		Product product = service .getProduct(1018); 
		assertEquals(1018,product.getProductId());
	}
	@Test
	void test5() {
		List<Product> products = service.getAllProducts();
		assertEquals(true, products.size() > 0);
	}
	@Test
	void test6() {
		List<Product> products = service.getAllProducts();
		assertEquals(service.getProduct(productId).getProductId(), products.get( productId%100 - 1).getProductId());
	}
	@Test
	void test7() {
		List<Product> products = service.getAllProductsByCategory("Fashion");
		assertEquals(true, products.size() > 0);
	}
	@Test
	void test8() {
		List<Product> products = service.getAllProductsBySubCategory(subCategory);
		assertEquals(true, products.size() > 0);
	}
	
	
}