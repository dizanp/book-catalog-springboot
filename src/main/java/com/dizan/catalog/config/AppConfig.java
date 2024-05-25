package com.dizan.catalog.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.dizan.catalog.domain.Author;
import com.dizan.catalog.domain.Book;
import com.dizan.catalog.repository.BookRepository;
import com.dizan.catalog.repository.impl.BookRepositoryImpl;


@Configuration
public class AppConfig {

	@Bean
	public Author author() {
		return new Author(1L, "John Tor", null, false);
	}
	
	@Bean
	public Book book1(Author author) {
		Book book = new Book();
		book.setId(1L);
		book.setTitle("Menjadi mesin");
		book.setDescription("Fokus untuk menjadi mesin");
		book.setAuthor(author);
		return book;
	}
	
	@Bean
	public Book book2(Author author) {
		Book book = new Book();
		book.setId(2L);
		book.setTitle("Menjadi mesin giling");
		book.setDescription("Fokus untuk menjadi mesin giling");
		book.setAuthor(author);
		return book;
	}
	
	@Bean
	public BookRepository bookRepository(Book book1, Book book2) {
		Map<Long, Book> bookMap = new HashMap<Long, Book>();
		bookMap.put(1L, book1);
		bookMap.put(2L, book2);
		
		BookRepositoryImpl bookRepository = new BookRepositoryImpl();
		bookRepository.setBookMap(bookMap);
		
		return bookRepository;
	}
	
//	@Bean
//	public BookService bookService(BookRepository bookRepository) {
//		return new BookServiceImpl(bookRepository);
//	}
	
}
