package com.luv2code.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.luv2code.demo.dto.mapper.SystemMapper;
import com.luv2code.demo.dto.request.ProductDtoRequest;
import com.luv2code.demo.dto.response.ProductDtoResponse;
import com.luv2code.demo.entity.Product;
import com.luv2code.demo.exc.custom.NotFoundException;
import com.luv2code.demo.exc.custom.NotFoundTypeException;
import com.luv2code.demo.repo.CategoryRepository;
import com.luv2code.demo.repo.ProductRepository;
import com.luv2code.demo.service.ProductService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final SystemMapper mapper;

	@Override
	public List<ProductDtoResponse> findAllByCategory(String categoryName) {

		List<ProductDtoResponse> products = productRepository.findAllByCategoryName(categoryName).stream()
				.map(mapper::productToProductDtoResponse).toList();

		return products;
	}

	@Override
	public ProductDtoResponse findByName(String productName) {

		Optional<ProductDtoResponse> product = productRepository.findByProductName(productName)
				.map(mapper::productToProductDtoResponse);

		if (product.isEmpty()) {
			throw new NotFoundException(NotFoundTypeException.Product + " Not Found!");
		}

		return product.get();
	}

	@Override
	public void addProduct(ProductDtoRequest productDtoRequest) {
		Product product = mapper.productDtoRequestToProduct(productDtoRequest);

		product.setCategory(categoryRepository.findByName(productDtoRequest.getCategory()).get());

		productRepository.save(product);
	}

	@Override
	public void deleteProductById(long theId) {

		Optional<Product> product = productRepository.findById(theId);

		if (product.isEmpty()) {
			throw new NotFoundException(NotFoundTypeException.Product + " Not Found!");
		}

		productRepository.delete(product.get());

	}

	@Override
	public ProductDtoResponse updateProductById(long theId, Double price) {

		Optional<Product> product = productRepository.findById(theId);

		if (product.isEmpty()) {
			throw new NotFoundException(NotFoundTypeException.Product + " Not Found!");
		}

		product.get().setPrice(price);

		return mapper.productToProductDtoResponse(productRepository.save(product.get()));
	}

}
