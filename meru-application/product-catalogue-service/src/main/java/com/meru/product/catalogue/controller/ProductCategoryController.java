package com.meru.product.catalogue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.meru.product.catalogue.entity.Product;
import com.meru.product.catalogue.service.ProductCategoryService;

@RestController
@RefreshScope
public class ProductCategoryController {
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@GetMapping("/productCategory/{productCategory}")
	public List<Product> getProductByCategory(@PathVariable String productCategory){
		return productCategoryService.getProductByCategory(productCategory);
	}

}
