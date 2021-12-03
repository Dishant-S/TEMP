package com.meru.composite.controller;

import java.util.List;

import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meru.composite.entity.Product;
import com.meru.composite.entity.User;
import com.meru.composite.feign.ProductFeignClient;
import com.meru.composite.feign.UserFeignClient;



@RestController
@RefreshScope
public class CompositeServiceController {
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	@Autowired
	private ProductFeignClient productFeignClient;
	
	@GetMapping("/user/{id}")
	public User getUserDetails(@PathVariable Long id){
		return  userFeignClient.getUserDetails(id);
	}
	
	@PostMapping(value = "/signUp")
    public ResponseEntity<User> addUser(@RequestBody User user){
		return userFeignClient.addUser(user);
	}
	
	@PutMapping(value = "/updateUser/{id}")
	public User updateUser(@PathVariable Long id,@RequestBody User user) {
		return userFeignClient.updateUser(id, user);
	}
	
	@PostMapping("/product")
	public ResponseEntity<?> createProduct(@RequestBody Product product){
		return productFeignClient.createProduct(product);
	}
	
	@GetMapping("/product/{id}")
	public Product getProduct(@PathVariable Long id){
		return productFeignClient.getProduct(id);
	}
	
	@PutMapping("/updateProduct/{id}")
	public Product updateProduct(@PathVariable Long id,@RequestBody Product product) {
		return productFeignClient.updateProduct(id,product);
	}
	
	@GetMapping("/productCategory/{productCategory}")
	public List<Product> getProductByCategory(@PathVariable String productCategory){
		return productFeignClient.getProductByCategory(productCategory);
	}

}
