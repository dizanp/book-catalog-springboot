package com.dizan.catalog.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dizan.catalog.domain.Category;
import java.util.List;


public interface CategoryRepository extends JpaRepository<Category, String>{
	
	public Optional<Category> findByCode(String code);
	
	public Page<Category> findByNameLikeIgnoreCase(String categoryName, Pageable pageable);
}
