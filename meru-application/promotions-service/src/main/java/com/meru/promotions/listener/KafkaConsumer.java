package com.meru.promotions.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.meru.promotions.dao.PromotionsDAO;
import com.meru.promotions.entity.Promotions;
import com.meru.promotions.entity.PromotionsSO;
import com.meru.promotions.service.PromotionsService;

@Service
public class KafkaConsumer {
	
	@Autowired
	private PromotionsService promotionsService; 
	
    @KafkaListener(topics = "CREATE_PROMOTION", groupId  = "group_json",
            containerFactory = "promotionsListenerContainerFactory")
    public void consumeJson(PromotionsSO promotionsSO) {
    	Promotions promotions = new Promotions();
		promotions.setEmail(promotionsSO.getEmail());
		promotions.setFirstName(promotionsSO.getFirstName());
		promotions.setLastName(promotionsSO.getLastName());
		promotions.setUserId(promotionsSO.getUserId());
		promotions.setOffer(promotionsSO.getOffer());
		promotions.setPhoneNumber(promotionsSO.getPhoneNumber());
    	promotionsService.createOffer(promotions);
    	System.out.println("Consumed" + promotionsSO.toString());
    	//promotionsDAO.createOffer(promotionsSO);
    }
}
