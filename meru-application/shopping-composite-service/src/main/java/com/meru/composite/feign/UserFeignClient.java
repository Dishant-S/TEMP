package com.meru.composite.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.ResponseEntity;

import com.meru.composite.entity.User;

@FeignClient(name="user-service")
public interface UserFeignClient {
	
	@RequestMapping(method=RequestMethod.GET, value="/user/{id} ")
	public User getUserDetails(@PathVariable Long id);
	
	@RequestMapping(method=RequestMethod.POST, value = "/signUp")
    public ResponseEntity<User> addUser(@RequestBody User User);
	
	@RequestMapping(method=RequestMethod.PUT, value = "/updateUser/{id}")
	public User updateUser(@PathVariable Long id,@RequestBody User User) ;


}
