package com.meru.product.catalogue.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.meru.product.catalogue.entity.Product;
import com.meru.product.catalogue.entity.ProductCategory;
import com.meru.product.catalogue.exception.ProductCategoryNotFoundException;
import com.meru.product.catalogue.exception.ProductNotFoundException;
import com.meru.product.catalogue.repo.ProductCategoryRepository;
import com.meru.product.catalogue.repo.ProductRepository;


@Component
public class ProductCategoryDAOImpl implements ProductCategoryDAO{
	
	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public List<Product> getProductByCategory(String productCategory) {
		List<Optional<ProductCategory>> productCategoryOptional	= productCategoryRepository.findByProductCategoryName(productCategory);
		if(!CollectionUtils.isEmpty(productCategoryOptional)) {
			List<Product> productList = new ArrayList<Product>();
			for(Optional<ProductCategory> prodCatOptnl : productCategoryOptional) {
				ProductCategory prodCategory = prodCatOptnl.get();
				productList.addAll(prodCategory.getProducts());
			}
			return productList;			
		}else {
			throw new ProductCategoryNotFoundException("Product with category:"+productCategory+" not available");
		}
		
		
//		EntityManager 		    entityManager      = entityManagerFactory.createEntityManager();
//		CriteriaBuilder         criteriaBuilder    = entityManager.getCriteriaBuilder();
//		CriteriaQuery<Product>  query      		   = criteriaBuilder.createQuery(Product.class);
//		Root<Product>           productRoot        = query.from(Product.class);
//		Join<Product, ProductCategory> join       = productRoot.join("productCategory",JoinType.LEFT);
//		query.distinct(true);
//		query.from(Product.class);
//		query.select(productRoot);
//		 
//		ParameterExpression<String> param = criteriaBuilder.parameter(String.class);
//		query.where(criteriaBuilder.equal(join.get("productCategoryName"), param));
//		 
//		TypedQuery<Product> typedQuery = entityManager.createQuery(query);
//		typedQuery.setParameter(param, productCategory);
//		List<Product> customerList 		= typedQuery.getResultList();
//		if(!CollectionUtils.isEmpty(customerList)) {
//			return customerList;
//		}
//		return null;

	}
	
	

}
