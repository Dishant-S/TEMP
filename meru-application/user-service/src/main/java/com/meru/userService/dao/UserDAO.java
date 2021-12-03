package com.meru.userService.dao;

import com.meru.userService.entity.User;

public interface UserDAO {

	User signUp(User customer);

	User updateUser(Long id, User customer);

	User getUserDetails(Long id);

}
