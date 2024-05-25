package com.dizan.catalog.service;

import java.util.List;

import com.dizan.catalog.dto.BookCreateDTO;
import com.dizan.catalog.dto.BookDetailDTO;
import com.dizan.catalog.dto.BookUpdateRequestDTO;

public interface BookService {
	public BookDetailDTO findBookDetailById(Long bookId);
	
	public List<BookDetailDTO> findBookListDetail();
	
	public void createNewBook(BookCreateDTO dto);
	
	public void updateBook(Long bookId, BookUpdateRequestDTO dto);
	
	public void deleteBook(Long bookId);
}
