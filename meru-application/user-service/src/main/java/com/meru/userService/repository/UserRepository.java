package com.meru.userService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.meru.userService.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
