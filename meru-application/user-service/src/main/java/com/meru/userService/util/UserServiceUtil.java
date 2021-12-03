package com.meru.userService.util;

import org.springframework.stereotype.Component;

import com.meru.userService.entity.User;
import com.meru.userService.entity.PromotionsSO;

@Component
public class UserServiceUtil {
	
	public PromotionsSO createPromotionForUser(User user) {
		PromotionsSO promotionsSO = new PromotionsSO();
		promotionsSO.setUserId(user.getId());
		promotionsSO.setFirstName(user.getFirstName());
		promotionsSO.setLastName(user.getLastName());
		promotionsSO.setEmail(user.getEmail());
		promotionsSO.setPhoneNumber(user.getPhoneNumber());
		promotionsSO.setOffer("50 % OFF");
		return promotionsSO;
	}

}
