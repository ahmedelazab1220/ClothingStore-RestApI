package com.luv2code.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.luv2code.demo.entity.Category;
import com.luv2code.demo.exc.custom.NotFoundException;
import com.luv2code.demo.exc.custom.NotFoundTypeException;
import com.luv2code.demo.repo.CategoryRepository;
import com.luv2code.demo.service.CategoryService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	@Override
	public Category findByName(String name) {

		Optional<Category> category = categoryRepository.findByName(name);

		if (category.isEmpty()) {
			throw new NotFoundException(NotFoundTypeException.Category + " Not Found!");
		}

		return category.get();
	}

	@Override
	public void addCategory(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public void deleteCategory(String categoryName) {

		Optional<Category> result = categoryRepository.findByName(categoryName);

		if (result.isEmpty()) {
			throw new NotFoundException(NotFoundTypeException.Category + " Not Found!");
		}

		categoryRepository.delete(result.get());
	}

	@Override
	public List<Category> getAllCategory() {

		return categoryRepository.findAll();
	}

}
