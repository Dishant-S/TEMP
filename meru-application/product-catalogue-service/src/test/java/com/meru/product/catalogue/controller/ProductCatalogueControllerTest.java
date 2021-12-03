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

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductCatalogueController.class)
@TestMethodOrder(OrderAnnotation.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class ProductCatalogueControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductCatalogueService productCatalogueService;
	
	@Test
	@Order(1)
	void createProduct() throws Exception {
				
		RequestBuilder request;
		
		List<Product>  productList    = new ArrayList<>();
		
		Product  	   mockProduct    = new Product(1L,"Five Point Someone","Five Point Someone Book",100.0,null,20);
		
		productList.add(mockProduct);
		
		ProductCategory productCategory = new ProductCategory(1L, "Fiction", productList);
		
		mockProduct.setProductCategory(productCategory);

		when(productCatalogueService.createProduct(any(Product.class))).thenReturn(mockProduct);
		
		request = MockMvcRequestBuilders
					.post("/product")
					.accept(MediaType.APPLICATION_JSON).content(asJsonString(mockProduct))
					.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request).andReturn();
		
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		
		System.out.println("Response:"+response.getHeader(HttpHeaders.LOCATION));
		
		assertEquals("http://localhost/product/1",response.getHeader(HttpHeaders.LOCATION));
		
	}
	
	@Test
	@Order(2)
	void updateProduct() throws Exception {
		
		List<Product>  productList    = new ArrayList<>();
		
		Product  	   mockProduct    = new Product(1L,"Five Point Someone","Five Point Someone Book",100.0,null,20);
		
		productList.add(mockProduct);
		
		ProductCategory productCategory = new ProductCategory(1L, "Fiction", productList);
		
		mockProduct.setProductCategory(productCategory);
		
		when(productCatalogueService.updateProduct(anyLong(),any(Product.class))).thenReturn(mockProduct);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/updateProduct/{id}",1)
				.accept(MediaType.APPLICATION_JSON).content(asJsonString(new Product("Five Point Someone","Five Point Someone Book")))
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		
		System.out.println("Response:"+response.getContentAsString());

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	@Order(3)
	void getProduct() throws Exception{
		
		RequestBuilder request;
		
		List<Product>  productList    = new ArrayList<>();
		
		Product  	   mockProduct    = new Product(1L,"Five Point Someone","Five Point Someone Book",100.0,null,20);
		
		productList.add(mockProduct);
		
		ProductCategory productCategory = new ProductCategory(1L, "Fiction", productList);
		
		mockProduct.setProductCategory(productCategory);
		
		System.out.println("Response :"+mockProduct.toString());
		
		when(productCatalogueService.getProduct(anyLong())).thenReturn(mockProduct);
		
		request = MockMvcRequestBuilders.get("/product/{id}",1).accept(MediaType.APPLICATION_JSON);
		
		String expectedResult= "{\r\n" + 
				"    \"id\": 1,\r\n" + 
				"    \"productName\": \"Five Point Someone\",\r\n" + 
				"    \"description\": \"Five Point Someone Book\",\r\n" + 
				"    \"price\": 100.0,\r\n" + 
				"    \"productCategory\": {\r\n" + 
				"        \"id\": 1,\r\n" + 
				"        \"productCategoryName\": \"Fiction\"\r\n" + 
				"    },\r\n" + 
				"    \"availableItemCount\": 20\r\n" + 
				"}";
		
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
