package com.meru.userService.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meru.userService.dao.UserDAO;
import com.meru.userService.entity.User;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserDAO userDAO;

	public User signUp(User customer) {
		return userDAO.signUp(customer);
	}

	public User updateUser(Long id, User customer) {
		return userDAO.updateUser(id,customer);
	}

	public User getUserDetails(Long id) {
		return userDAO.getUserDetails(id);
	}

}
