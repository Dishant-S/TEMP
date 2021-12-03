package com.meru.userService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.meru.userService.entity.Address;
import com.meru.userService.entity.User;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class MeruApplicationUserServiceTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	@Order(1)
	public void addUser() {
		
		List<Address>  addressList = new ArrayList<>();
		
		User  		   mockUser    = new User(1L,"Tom","Curran","tc@gmail.com","123","124",addressList);
		
		addressList.add(new Address(1L, 2, "3rd Street", 4, 250002, "Delhi","India", mockUser));
		
		mockUser.setAddressList(addressList);
		
		HttpHeaders headers   = new org.springframework.http.HttpHeaders();
		 
		headers.set("Content-Type", "application/json");
		
		HttpEntity<User> request=new HttpEntity<>(mockUser,headers);
		
		ResponseEntity<String> result = this.restTemplate.postForEntity("/signUp", request, String.class);
	
		assertEquals(201, result.getStatusCodeValue());
	}
	
	
	@Test
	@Order(2)
	public void updateUser() {
		
		Map < String, String > params = new HashMap < String, String > ();
	    
		params.put("id", "1");
		
		List<Address>  addressList = new ArrayList<>();
		
		User  		   mockUser    = new User(1L,"Tom","Curran","tc@gmail.com","123","124",addressList);
		
		addressList.add(new Address(1L, 2, "3rd Street", 4, 250002, "Delhi","India", mockUser));
		
		mockUser.setAddressList(addressList);
		
		HttpHeaders headers   = new org.springframework.http.HttpHeaders();
		 
		headers.set("Content-Type", "application/json");
		
		HttpEntity<User> request=new HttpEntity<>(mockUser,headers);
		
		this.restTemplate.put("/updateCustomer/{id}", request, params);
		
		assertEquals("tc@gmail.com", mockUser.getEmail());
		
	}
	
	@Test
	@Order(3)
	public void getUserDetails() {
		
		 Map < String, String > params = new HashMap < String, String > ();
	     
		 params.put("id", "68");
		 
		 User user = this.restTemplate.getForObject("/user/{id}", User.class,params);

		 System.out.println(user.toString());
		 
		 assertEquals(68L, user.getId());
		
	}

}
