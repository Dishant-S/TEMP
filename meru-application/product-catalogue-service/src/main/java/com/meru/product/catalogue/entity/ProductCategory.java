package com.meru.product.catalogue.entity;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "product_category")
public class ProductCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_category_name", nullable = false)
    private String productCategoryName;

    @OneToMany(mappedBy = "productCategory",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> products;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductCategoryName() {
		return productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public ProductCategory(Long id, String productCategoryName, List<Product> products) {
		super();
		this.id = id;
		this.productCategoryName = productCategoryName;
		this.products = products;
	}

	public ProductCategory() {
		super();
	}

	public ProductCategory(List<Product> products) {
		super();
		this.products = products;
	}

	public ProductCategory(String productCategoryName, List<Product> products) {
		super();
		this.productCategoryName = productCategoryName;
		this.products = products;
	}
		
	
	
}
