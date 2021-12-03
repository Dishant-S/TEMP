package com.meru.product.catalogue.dao;

import com.meru.product.catalogue.entity.Product;

public interface ProductCatalogueDAO {

	Product createProduct(Product product);

	Product getProduct(Long id);

	Product updateProduct(Long id, Product product);

}
