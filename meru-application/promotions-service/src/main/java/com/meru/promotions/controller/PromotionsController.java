package com.meru.promotions.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.meru.promotions.entity.Promotions;
import com.meru.promotions.service.PromotionsService;

@RestController
@RefreshScope
public class PromotionsController {
	
	@Autowired
	private PromotionsService promotionsService;
	
	@PostMapping(value = "/createPromotion")
    public ResponseEntity<Promotions> addUser(@RequestBody Promotions promotions){
		Promotions createdPromotion    = promotionsService.createOffer(promotions);
		URI location	= ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(createdPromotion.getId())
						.toUri();
		
		return ResponseEntity.created(location).build();
    }

}
