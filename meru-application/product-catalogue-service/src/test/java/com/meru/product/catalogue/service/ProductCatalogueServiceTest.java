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

import com.meru.product.catalogue.dao.ProductCatalogueDAO;
import com.meru.product.catalogue.entity.Product;
import com.meru.product.catalogue.entity.ProductCategory;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class ProductCatalogueServiceTest {
	
	@InjectMocks
	private ProductCatalogueService productCatalogueService;
	
	@Mock
	private ProductCatalogueDAO  productCatalogueDAO;
	
	@Test
	@Order(1)
	void createProduct() {
		
		List<Product>  productList    = new ArrayList<>();
		
		Product  	   mockProduct    = new Product(1L,"Five Point Someone","Five Point Someone Book",100.0,null,20);
		
		productList.add(mockProduct);
		
		ProductCategory productCategory = new ProductCategory(1L, "Fiction", productList);
		
		mockProduct.setProductCategory(productCategory);
		
		when(productCatalogueDAO.createProduct(any(Product.class))).thenReturn(mockProduct);
		
		productCatalogueDAO.createProduct(mockProduct);
	
		System.out.println(mockProduct.toString());
		
		verify(productCatalogueDAO, atLeastOnce()).createProduct(mockProduct);
	}
	
	@Test
	@Order(2)
	void updateProduct() {
		
		List<Product>  productList    = new ArrayList<>();
		
		Product  	   mockProduct    = new Product(1L,"Five Point Someone","Five Point Someone Book",100.0,null,20);
		
		productList.add(mockProduct);
		
		ProductCategory productCategory = new ProductCategory(1L, "Fiction", productList);
		
		mockProduct.setProductCategory(productCategory);
		
		when(productCatalogueDAO.updateProduct(anyLong(),any(Product.class))).thenReturn(mockProduct);
				
		Product savedProduct = productCatalogueDAO.updateProduct(mockProduct.getId(),mockProduct);
		
		System.out.println(savedProduct.toString());
		
		verify(productCatalogueDAO, atLeastOnce()).updateProduct(mockProduct.getId(),mockProduct);
		
	}
	
	@Test
	@Order(3)
	void getProduct() {
		
		List<Product>  productList    = new ArrayList<>();
		
		Product  	   mockProduct    = new Product(1L,"Five Point Someone","Five Point Someone Book",100.0,null,20);
		
		productList.add(mockProduct);
		
		ProductCategory productCategory = new ProductCategory(1L, "Fiction", productList);
		
		mockProduct.setProductCategory(productCategory);
		
		System.out.println("Response :"+mockProduct.toString());
		
		when(productCatalogueDAO.getProduct(anyLong())).thenReturn(mockProduct);

		Product savedProduct = productCatalogueDAO.getProduct(mockProduct.getId());
		
		System.out.println(savedProduct.toString());
		
		verify(productCatalogueDAO, atLeastOnce()).getProduct(savedProduct.getId());
		
	}

}
