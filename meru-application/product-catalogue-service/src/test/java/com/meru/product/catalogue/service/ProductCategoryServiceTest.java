package com.meru.product.catalogue.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.meru.product.catalogue.dao.ProductCategoryDAO;
import com.meru.product.catalogue.entity.Product;
import com.meru.product.catalogue.entity.ProductCategory;


@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class ProductCategoryServiceTest {

	@InjectMocks
	private ProductCategoryService productCategoryService;
	
	@Mock
	private ProductCategoryDAO  productCategoryDAO;
	
	@Test
	@Order(1)
	void getProduct() {
		
		List<Product>  productList    = new ArrayList<>();
		
		Product  	   mockProduct    = new Product(1L,"Five Point Someone","Five Point Someone Book",100.0,null,20);
		
		productList.add(mockProduct);
		
		ProductCategory productCategory = new ProductCategory(1L, "Fiction", productList);
		
		mockProduct.setProductCategory(productCategory);
		
		System.out.println("Response :"+mockProduct.toString());
		
		when(productCategoryDAO.getProductByCategory(any())).thenReturn(productList);

		List<Product> savedProductList = productCategoryDAO.getProductByCategory("Fiction");
		
		for(Product savedProduct: savedProductList) {
			System.out.println(savedProduct.toString());
		}
		
		verify(productCategoryDAO, atLeastOnce()).getProductByCategory("Fiction");
		
	}
}
