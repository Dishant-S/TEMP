package com.meru.promotions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meru.promotions.entity.Promotions;

public interface PromotionsRepository extends JpaRepository<Promotions, Long>{

}
