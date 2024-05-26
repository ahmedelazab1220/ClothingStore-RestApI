package com.luv2code.demo.service;

import java.util.List;

import com.luv2code.demo.entity.Category;

public interface CategoryService {

	Category findByName(String categoryName);

	void addCategory(Category category);

	void deleteCategory(String categoryName);

	List<Category> getAllCategory();

}
