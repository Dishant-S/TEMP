package com.meru.composite.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.meru.composite.entity.Product;

import org.springframework.http.ResponseEntity;


@FeignClient(name="catalogue-service")
public interface ProductFeignClient {
	
	@RequestMapping(method=RequestMethod.GET, value="/product/{id} ")
	public Product getProduct(@PathVariable Long id);
	
	@RequestMapping(method=RequestMethod.POST, value = "/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product);
	
	@RequestMapping(method=RequestMethod.PUT, value = "/updateProduct/{id}")
	public Product updateProduct(@PathVariable Long id,@RequestBody Product product) ;
	
	@RequestMapping(method=RequestMethod.GET, value="/productCategory/{productCategory}")
	public List<Product> getProductByCategory(@PathVariable String productCategory);	


}
