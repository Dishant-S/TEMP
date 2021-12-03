package com.meru.userService.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.meru.userService.entity.User;
import com.meru.userService.entity.PromotionsSO;
import com.meru.userService.exception.UserNotFoundException;
import com.meru.userService.service.UserService;
import com.meru.userService.util.UserServiceUtil;

@RestController
@RefreshScope
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserServiceUtil userServiceUtil;
	
	@Autowired
    private KafkaTemplate<String, PromotionsSO> kafkaTemplate;
	
	private static final String TOPIC = "CREATE_PROMOTION";
	
	@PostMapping(value = "/signUp")
    public ResponseEntity<User> addUser(@RequestBody User User){
    	User createdUser    = userService.signUp(User);
    	if(null!= createdUser) {
    		PromotionsSO promotionsSO = userServiceUtil.createPromotionForUser(createdUser);
    		kafkaTemplate.send(TOPIC,promotionsSO);
    	}
		URI location	= ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(createdUser.getId())
						.toUri();
		
		return ResponseEntity.created(location).build();
    }
    
    @PutMapping("/updateUser/{id}")
	public User updateUser(@PathVariable Long id,@RequestBody User User) {
		User createdUser = userService.updateUser(id, User);
		if(createdUser==null) {
			throw new UserNotFoundException("User with id:"+id+" not found");
		}
		return createdUser;
	}
	
	@GetMapping("/user/{id}")
	public User getUserDetails(@PathVariable Long id){
		User User = userService.getUserDetails(id);
		if(null == User) {
			throw new UserNotFoundException("User with id:"+id+" not found");
		}else {
			return User;
		}
	}

}
