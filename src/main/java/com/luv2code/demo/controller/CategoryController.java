package com.luv2code.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.demo.entity.Category;
import com.luv2code.demo.service.CategoryService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v4/category")
@AllArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;

	@GetMapping("")
	public Category getCategory(@RequestParam(value = "name") String categoryName) {

		return categoryService.findByName(categoryName);

	}

	@PostMapping("")
	@PreAuthorize("hasAnyRole('MANAGER' , 'ADMIN')")
	public ResponseEntity<?> addCategory(@RequestBody Category category) {

		categoryService.addCategory(category);

		return ResponseEntity.ok("Category added successfully");

	}

	@DeleteMapping("")
	@PreAuthorize("hasRole('MANAGER')")
	public ResponseEntity<?> deleteCategory(@RequestParam(value = "name") String categoryName) {

		categoryService.deleteCategory(categoryName);

		return ResponseEntity.ok("Category deleted successfully");

	}

	@GetMapping("/all")
	public List<Category> getAllCatgeory() {
		return categoryService.getAllCategory();
	}
}
