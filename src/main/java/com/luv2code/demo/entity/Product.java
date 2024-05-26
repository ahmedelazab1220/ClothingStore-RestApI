package com.luv2code.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;

	@Column(name = "product_name", nullable = false)
	private String productName;

	@Column(name = "description")
	private String description;

	@Column(name = "price", nullable = false)
	private Double price;

	@Column(name = "stock_quantity", nullable = false)
	private Integer stockQuantity;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

}
