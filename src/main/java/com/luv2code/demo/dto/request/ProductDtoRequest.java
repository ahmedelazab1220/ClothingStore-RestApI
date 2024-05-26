package com.luv2code.demo.dto.request;

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
public class ProductDtoRequest {

	private String productName;

	private String description;

	private Double price;

	private Integer stockQuantity;

	private String category;

}
