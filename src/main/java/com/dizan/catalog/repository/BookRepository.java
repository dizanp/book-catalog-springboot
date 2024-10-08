package com.dizan.catalog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dizan.catalog.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
	public Optional<Book> findById(Long id);
	
	public Optional<Book> findBySecureId(String id);
//	
//	public List<Book> findAll();
//	
//	public void save(Book book);
//	
//	public void update(Book book);
//	
	public void deleteById(Long bookId);
}
