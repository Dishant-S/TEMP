package com.meru.promotions.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.meru.promotions.entity.Promotions;
import com.meru.promotions.entity.PromotionsSO;
import com.meru.promotions.repository.PromotionsRepository;

@Component
@ComponentScan("com.meru.promotions.repository")
public class PromotionsDAOImpl implements PromotionsDAO{
	
	@Autowired
	private PromotionsRepository promotionsRepository ;

	@Override
	public Promotions createOffer(Promotions promotions) {
		Promotions savedOffer = promotionsRepository.save(promotions);
		promotionsRepository.flush();
		return savedOffer;
	}

}
