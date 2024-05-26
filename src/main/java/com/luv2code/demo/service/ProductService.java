package com.luv2code.demo.service;

import java.util.List;

import com.luv2code.demo.dto.request.ProductDtoRequest;
import com.luv2code.demo.dto.response.ProductDtoResponse;

public interface ProductService {

	List<ProductDtoResponse> findAllByCategory(String categoryName);

	ProductDtoResponse findByName(String productName);

	void addProduct(ProductDtoRequest productDtoRequest);

	void deleteProductById(long theId);

	ProductDtoResponse updateProductById(long theId, Double price);

}
