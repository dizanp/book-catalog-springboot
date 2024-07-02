package com.dizan.catalog.service.impl;

import org.springframework.stereotype.Service;

import com.dizan.catalog.domain.Category;
import com.dizan.catalog.dto.CategoryCreateUpdateRequestDTO;
import com.dizan.catalog.repository.CategoryRepository;
import com.dizan.catalog.service.CategoryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{
	
	private final CategoryRepository categoryRepository;
	
	@Override
	public void createAndUpdateCategory(CategoryCreateUpdateRequestDTO dto) {
		Category category =  categoryRepository.findByCode(dto.getCode().toLowerCase()).orElse(new Category());
		if(category.getCode() == null) {
			category.setCode(dto.getCode().toLowerCase()); // new
		}
		category.setName(dto.getName());
		category.setDescription(dto.getDescription());
		
		categoryRepository.save(category);
	}

}
