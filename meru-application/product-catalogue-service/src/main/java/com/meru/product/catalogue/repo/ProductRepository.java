package com.meru.product.catalogue.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meru.product.catalogue.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	
}
