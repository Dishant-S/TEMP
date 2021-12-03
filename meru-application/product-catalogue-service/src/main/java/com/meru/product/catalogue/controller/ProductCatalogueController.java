package com.meru.product.catalogue.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.meru.product.catalogue.entity.Product;
import com.meru.product.catalogue.exception.ProductNotFoundException;
import com.meru.product.catalogue.service.ProductCatalogueService;

@RestController
@RefreshScope
public class ProductCatalogueController {
	
	@Autowired
	private ProductCatalogueService productCatalogueService;
	
	@PostMapping("/product")
	public ResponseEntity<?> createProduct(@RequestBody Product product){

        Product createdProduct = productCatalogueService.createProduct(product);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{productId}")
                .buildAndExpand(createdProduct.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
	
	@GetMapping("/product/{id}")
	public Product getProduct(@PathVariable Long id){
		return productCatalogueService.getProduct(id);
	}

	 
    @PutMapping("/updateProduct/{id}")
	public Product updateProduct(@PathVariable Long id,@RequestBody Product product) {
    	Product updatedProduct = productCatalogueService.updateProduct(id, product);
		if(updatedProduct==null) {
			throw new ProductNotFoundException("Product with id:"+id+" not available");
		}
		return updatedProduct;
	}

}
