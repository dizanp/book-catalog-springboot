package com.dizan.catalog.service;

import java.util.List;

import com.dizan.catalog.domain.Category;
import com.dizan.catalog.dto.CategoryCreateUpdateRequestDTO;
import com.dizan.catalog.dto.CategoryListResponseDTO;
import com.dizan.catalog.dto.ResultPageResponseDTO;

public interface CategoryService {
	
	public void createAndUpdateCategory(CategoryCreateUpdateRequestDTO dto);
	
	public ResultPageResponseDTO<CategoryListResponseDTO> findCategoryList(Integer pages,
			Integer limit, String sortBy, String direction, String categoryName);
	
	public List<Category> findCategories(List<String> categoryCodeList);
	
	public List<CategoryListResponseDTO> constructDTO(List<Category> categories);
}
