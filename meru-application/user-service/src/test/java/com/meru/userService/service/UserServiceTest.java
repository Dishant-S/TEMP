package com.meru.userService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.meru.userService.dao.UserDAO;
import com.meru.userService.entity.Address;
import com.meru.userService.entity.User;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserDAO  userDAO;
	
	@Test
	@Order(1)
	void signUp() {
		
		List<Address>  addressList = new ArrayList<>();
		
		User  		   mockUser    = new User(1L,"Tom","Curran","tc@gmail.com","123","124",addressList);
		
		addressList.add(new Address(1L, 2, "3rd Street", 4, 250002, "Delhi","India", mockUser));
		
		mockUser.setAddressList(addressList);
		
		when(userDAO.signUp(any(User.class))).thenReturn(mockUser);
		
		userDAO.signUp(mockUser);
	
		System.out.println(mockUser);
		
		verify(userDAO, atLeastOnce()).signUp(mockUser);
	}
	
	@Test
	@Order(2)
	void updateUser() {
		
		List<Address>  addressList = new ArrayList<>();
		
		User  		   mockUser    = new User(1L,"Tom","Curran","tc@gmail.com","123","124",addressList);
		
		addressList.add(new Address(1L, 2, "3rd Street", 4, 250002, "Delhi","India", mockUser));
		
		mockUser.setAddressList(addressList);
		
		User  		   updatedMockUser    = new User(1L,"Jos","Butler","tc@gmail.com","123","124",addressList);
		
		addressList.add(new Address(1L, 2, "3rd Street", 4, 250002, "Delhi","India", mockUser));
		
		mockUser.setAddressList(addressList);
		
		when(userDAO.updateUser(anyLong(), any(User.class))).thenReturn(updatedMockUser);
				
		User savedUser = userDAO.updateUser(mockUser.getId(),mockUser);
		
		System.out.println(savedUser.toString());
		
		verify(userDAO, atLeastOnce()).updateUser(mockUser.getId(),mockUser);
		
	}
	
	@Test
	@Order(2)
	void getUserDetails() {
		
		List<Address>  addressList = new ArrayList<>();
		
		User  		   mockUser    = new User(1L,"Tom","Curran","tc@gmail.com","123","124",addressList);
		
		addressList.add(new Address(1L, 2, "3rd Street", 4, 250002, "Delhi","India", mockUser));
		
		mockUser.setAddressList(addressList);

		when(userDAO.getUserDetails(anyLong())).thenReturn(mockUser);

		User savedUser = userDAO.getUserDetails(mockUser.getId());
		
		System.out.println(savedUser.toString());
		
		verify(userDAO, atLeastOnce()).getUserDetails(mockUser.getId());
		
	}
}
