package com.luv2code.demo.dto.response;

import com.luv2code.demo.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDtoResponse {

	private String productName;

	private String description;

	private Double price;

	private Integer stockQuantity;

	private Category category;

}
