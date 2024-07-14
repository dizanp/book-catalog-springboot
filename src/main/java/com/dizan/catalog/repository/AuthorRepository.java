package com.dizan.catalog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dizan.catalog.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
	
	// method name convention
	// find + keyword
	// sql -> select * from Author a where a.id = :authorId
	public Optional<Author> findById(Long id);
	
	public Optional<Author> findBySecureId(String id);
	
	// where id = :id and deleted = false
	public Optional<Author> findByIdAndDeletedFalse(Long id);
	
	// sql -> select a from Author a  where a.name = :name
	public List<Author> findByNameLike(String name);
}
