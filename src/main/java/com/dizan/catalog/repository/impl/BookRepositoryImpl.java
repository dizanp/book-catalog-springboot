package com.dizan.catalog.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dizan.catalog.domain.Book;
import com.dizan.catalog.repository.BookRepository;

import lombok.Data;

@Data
public class BookRepositoryImpl implements BookRepository{
	
	private Map<Long, Book> bookMap;
	
	@Override
	public Book findBookById(Long id) {
		Book book = bookMap.get(id);
		return book;
	}

	@Override
	public List<Book> findAll() {
		List<Book> bookList = new ArrayList<Book>(bookMap.values());
		return bookList;
	}

	@Override
	public void save(Book book) {
		int size = bookMap.size();
		book.setId((long) size+1);
		bookMap.put(book.getId(), book);
	}

	@Override
	public void update(Book book) {
		bookMap.put(book.getId(), book);
	}

	@Override
	public void delete(Long bookId) {
		bookMap.remove(bookId);
	}


}
