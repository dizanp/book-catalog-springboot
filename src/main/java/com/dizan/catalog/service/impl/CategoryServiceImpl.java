package com.dizan.catalog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dizan.catalog.domain.Category;
import com.dizan.catalog.dto.CategoryCreateUpdateRequestDTO;
import com.dizan.catalog.dto.CategoryListResponseDTO;
import com.dizan.catalog.dto.ResultPageResponseDTO;
import com.dizan.catalog.exception.BadRequestException;
import com.dizan.catalog.repository.CategoryRepository;
import com.dizan.catalog.service.CategoryService;
import com.dizan.catalog.util.PaginationUtil;

import io.micrometer.common.util.StringUtils;
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

	@Override
	public ResultPageResponseDTO<CategoryListResponseDTO> findCategoryList(Integer pages, Integer limit, String sortBy,
			String direction, String categoryName) {
		categoryName = StringUtils.isEmpty(categoryName) ? "%" :categoryName+"%";
		Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
		Pageable pageable = PageRequest.of(pages, limit, sort);
		categoryRepository.findByNameLikeIgnoreCase(categoryName, pageable);
		Page<Category> pageResult = categoryRepository.findByNameLikeIgnoreCase(categoryName, pageable);
		List<CategoryListResponseDTO> dtos = pageResult.stream().map((c) -> {
			CategoryListResponseDTO dto = new CategoryListResponseDTO();
			dto.setCode(c.getCode());
			dto.setName(c.getName());
			dto.setDescription(c.getDescription());
			return dto;
		}).collect(Collectors.toList());
		return PaginationUtil.createResultPageDTO(dtos, pageResult.getTotalElements(), pageResult.getTotalPages());
	}

	@Override
	public List<Category> findCategories(List<String> categoryCodeList) {
		List<Category> categories = categoryRepository.findByCodeIn(categoryCodeList);
		if(categories.isEmpty()) throw new BadRequestException("category cant empty");
		return categories;
	}

	@Override
	public List<CategoryListResponseDTO> constructDTO(List<Category> categories) {
		return categories.stream().map((c) -> {
			CategoryListResponseDTO dto = new CategoryListResponseDTO();
			dto.setCode(c.getCode());
			dto.setName(c.getName());
			dto.setDescription(c.getDescription());
			return dto;
		}).collect(Collectors.toList());
	}

}
