package com.meru.product.catalogue.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meru.product.catalogue.entity.Product;
import com.meru.product.catalogue.entity.ProductCategory;
import com.meru.product.catalogue.service.ProductCatalogueService;
import com.meru.product.catalogue.service.ProductCategoryService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductCategoryController.class)
@TestMethodOrder(OrderAnnotation.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class ProductCategoryControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductCategoryService productCategoryService;
	
	@Test
	@Order(1)
	void getProductByCategory() throws Exception{
		
		RequestBuilder request;
		
		List<Product>  productList    = new ArrayList<>();
		
		Product  	   mockProduct    = new Product(1L,"Five Point Someone","Five Point Someone Book",100.0,null,20);
		
		productList.add(mockProduct);
		
		ProductCategory productCategory = new ProductCategory(1L, "Fiction", productList);
		
		mockProduct.setProductCategory(productCategory);
		
		System.out.println("Response :"+mockProduct.toString());
		
		when(productCategoryService.getProductByCategory(any())).thenReturn(productList);
		
		request = MockMvcRequestBuilders.get("/productCategory/{productCategory}","Fiction").accept(MediaType.APPLICATION_JSON);
		
		String expectedResult= "[{\r\n" + 
				"    \"id\": 1,\r\n" + 
				"    \"productName\": \"Five Point Someone\",\r\n" + 
				"    \"description\": \"Five Point Someone Book\",\r\n" + 
				"    \"price\": 100.0,\r\n" + 
				"    \"productCategory\": {\r\n" + 
				"        \"id\": 1,\r\n" + 
				"        \"productCategoryName\": \"Fiction\"\r\n" + 
				"    },\r\n" + 
				"    \"availableItemCount\": 20\r\n" + 
				"}]";
		
		mockMvc.perform(request)
			   .andExpect(status().isOk())
			   .andExpect(content().json(expectedResult))
			   .andReturn();
		
	}
	
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}  

}
