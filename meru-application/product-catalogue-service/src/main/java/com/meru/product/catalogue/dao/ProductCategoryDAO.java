package com.meru.product.catalogue.dao;

import java.util.List;

import com.meru.product.catalogue.entity.Product;

public interface ProductCategoryDAO {

	List<Product> getProductByCategory(String productCategory);

}
