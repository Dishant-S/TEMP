package com.meru.product.catalogue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.meru.product.catalogue.entity.Product;
import com.meru.product.catalogue.entity.ProductCategory;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class MeruApplicationProductCatalogueServiceTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	@Order(1)
	public void createProduct() {
		
		List<Product>  productList    = new ArrayList<>();
		
		Product  	   mockProduct    = new Product(1L,"Five Point Someone","Five Point Someone Book",100.0,null,20);
		
		productList.add(mockProduct);
		
		ProductCategory productCategory = new ProductCategory(1L, "Fiction", productList);
		
		mockProduct.setProductCategory(productCategory);
		
		HttpHeaders headers   = new org.springframework.http.HttpHeaders();
		 
		headers.set("Content-Type", "application/json");
		
		HttpEntity<Product> request=new HttpEntity<>(mockProduct,headers);
		
		ResponseEntity<String> result = this.restTemplate.postForEntity("/product", request, String.class);
	
		assertEquals(201, result.getStatusCodeValue());
	}
	
	
	@Test
	@Order(2)
	public void updateProduct() {
		
		Map < String, String > params = new HashMap < String, String > ();
	    
		params.put("id", "1");
		
		List<Product>  productList    = new ArrayList<>();
		
		Product  	   mockProduct    = new Product(1L,"Six Point Someone","Six Point Someone Book",100.0,null,20);
		
		productList.add(mockProduct);
		
		ProductCategory productCategory = new ProductCategory(1L, "Fiction", productList);
		
		mockProduct.setProductCategory(productCategory);
		
		System.out.println("Response :"+mockProduct.toString());
		
		HttpHeaders headers   = new org.springframework.http.HttpHeaders();
		 
		headers.set("Content-Type", "application/json");
		
		HttpEntity<Product> request=new HttpEntity<>(mockProduct,headers);
		
		this.restTemplate.put("/updateCustomer/{id}", request, params);
		
		assertEquals("Six Point Someone", mockProduct.getProductName());
		
	}
	
	@Test
	@Order(3)
	public void getProduct() {
		
		 Map < String, String > params = new HashMap < String, String > ();
	     
		 params.put("id", "2");
		 
		 Product product = this.restTemplate.getForObject("/product/{id}", Product.class,params);

		 System.out.println(product.toString());
		 
		 assertEquals(2, product.getId());
		
	}

}
