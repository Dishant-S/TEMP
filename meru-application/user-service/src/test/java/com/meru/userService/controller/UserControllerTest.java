package com.meru.userService.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meru.userService.controller.UserController;
import com.meru.userService.entity.Address;
import com.meru.userService.entity.PromotionsSO;
import com.meru.userService.entity.User;
import com.meru.userService.service.UserService;
import com.meru.userService.util.UserServiceUtil;



@ExtendWith(SpringExtension.class)   //@RunsWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@TestMethodOrder(OrderAnnotation.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
//@ContextConfiguration(classes = {UserServiceUtil.class})
class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private UserServiceUtil userServiceUtil;
	
	@MockBean
    private KafkaTemplate<String, PromotionsSO> kafkaTemplate;
	
	@Test
	@Order(1)
	void signUp() throws Exception {
				
		RequestBuilder request;
		
		List<Address>  addressList = new ArrayList<>();
		
		User  		   mockUser    = new User(1L,"Tom","Curran","tc@gmail.com","123","124",addressList);
		
		addressList.add(new Address(1L, 2, "3rd Street", 4, 250002, "Delhi","India", mockUser));
		
		mockUser.setAddressList(addressList);

		when(userService.signUp(any(User.class))).thenReturn(mockUser);
		
		request = MockMvcRequestBuilders
					.post("/signUp")
					.accept(MediaType.APPLICATION_JSON).content(asJsonString(mockUser))
					.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request).andReturn();
		
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		
		System.out.println("Response:"+response.getHeader(HttpHeaders.LOCATION));
		
		assertEquals("http://localhost/signUp/1",response.getHeader(HttpHeaders.LOCATION));
		
		

		
		
	}
	
	@Test
	@Order(2)
	void updateUser() throws Exception {
		
		List<Address>  addressList = new ArrayList<>();
		
		User  		   mockUser    = new User(1L,"Tom","Curran","tc@gmail.com","123","124",addressList);
		
		addressList.add(new Address(1L, 2, "3rd Street", 4, 250002, "Delhi","India", mockUser));
		
		mockUser.setAddressList(addressList);
		
		when(userService.updateUser(anyLong(),any(User.class))).thenReturn(mockUser);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/updateUser/{id}",1)
				.accept(MediaType.APPLICATION_JSON).content(asJsonString(new User("Tom","Sharma")))
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		
		System.out.println("Response:"+response.getContentAsString());

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	@Order(3)
	void getUserDetails() throws Exception{
		
		RequestBuilder request;
		
		List<Address>  addressList = new ArrayList<>();
		
		User  		   mockUser    = new User(1L,"TONY","STARK","TS@gmail.com","Avenger","0",addressList);
		
		addressList.add(new Address(34L, 745, "First Lane", 137, 240002, "DELHI","INDIA", mockUser));
		
		mockUser.setAddressList(addressList);
		
		System.out.println("Response :"+mockUser.toString());
		
		when(userService.getUserDetails(anyLong())).thenReturn(mockUser);
		
		request = MockMvcRequestBuilders.get("/user/{id}",1).accept(MediaType.APPLICATION_JSON);
		
		String expectedResult= "{\r\n" + 
				"    \"id\": 1,\r\n" + 
				"    \"firstName\": \"TONY\",\r\n" + 
				"    \"lastName\": \"STARK\",\r\n" + 
				"    \"email\": \"TS@gmail.com\",\r\n" + 
				"    \"password\": \"Avenger\",\r\n" + 
				"    \"phoneNumber\": \"0\",\r\n" + 
				"    \"addressList\": [\r\n" + 
				"        {\r\n" + 
				"            \"id\": 34,\r\n" + 
				"            \"houseNumber\": 745,\r\n" + 
				"            \"street\": \"First Lane\",\r\n" + 
				"            \"streetNumber\": 137,\r\n" + 
				"            \"zipCode\": 240002,\r\n" + 
				"            \"state\": \"DELHI\",\r\n" + 
				"            \"country\": \"INDIA\"\r\n" + 
				"        }\r\n" + 
				"    ]\r\n" + 
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
