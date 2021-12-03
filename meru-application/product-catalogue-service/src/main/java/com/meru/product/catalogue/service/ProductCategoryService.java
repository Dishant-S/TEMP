package com.meru.product.catalogue.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meru.product.catalogue.dao.ProductCategoryDAO;
import com.meru.product.catalogue.entity.Product;

@Service
@Transactional
public class ProductCategoryService {
	
	@Autowired
	private ProductCategoryDAO productCategoryDAO;

	public List<Product> getProductByCategory(String productCategory) {
		return productCategoryDAO.getProductByCategory(productCategory);
	}

}
