package com.meru.product.catalogue.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meru.product.catalogue.dao.ProductCatalogueDAO;
import com.meru.product.catalogue.entity.Product;

@Service
@Transactional
public class ProductCatalogueService {
	
	@Autowired
	private ProductCatalogueDAO productCatalogueDAO;

	public Product createProduct(Product product) {
		return productCatalogueDAO.createProduct(product);
	}

	public Product getProduct(Long id) {
		return productCatalogueDAO.getProduct(id);
	}

	public Product updateProduct(Long id, Product product) {
		return productCatalogueDAO.updateProduct(id,product);
	}

}
