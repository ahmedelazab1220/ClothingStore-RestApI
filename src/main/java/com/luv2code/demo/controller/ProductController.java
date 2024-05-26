package com.luv2code.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.demo.dto.request.ProductDtoRequest;
import com.luv2code.demo.dto.response.ProductDtoResponse;
import com.luv2code.demo.service.ProductService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v3/products")
@AllArgsConstructor
public class ProductController {

	private final ProductService productService;

	@GetMapping("")
	public ProductDtoResponse getProduct(@RequestParam(value = "name") String productName) {

		return productService.findByName(productName);

	}

	@GetMapping("/category")
	public List<ProductDtoResponse> getAllProductByCategory(@RequestParam(value = "name") String categoryName) {

		return productService.findAllByCategory(categoryName);

	}

	@PostMapping("")
	@PreAuthorize("hasAnyRole('ADMIN' , 'MANAGER')")
	public ResponseEntity<?> addProduct(@RequestBody ProductDtoRequest productDtoRequest) {

		productService.addProduct(productDtoRequest);

		return ResponseEntity.ok("Product added successfully");
	}

	@DeleteMapping("/{theId}")
	@PreAuthorize("hasRole('MANAGER')")
	public ResponseEntity<?> deleteProduct(@PathVariable(name = "theId") long theId) {

		productService.deleteProductById(theId);

		return ResponseEntity.ok("Product deleted successfully");

	}

	@PutMapping("/{theId}")
	@PreAuthorize("hasRole('MANAGER')")
	public ProductDtoResponse updateProduct(@PathVariable(name = "theId") long theId,
			@RequestParam(value = "price") Double price) {

		return productService.updateProductById(theId, price);

	}

}
