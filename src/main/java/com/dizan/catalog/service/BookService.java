package com.dizan.catalog.service;

import java.util.List;

import com.dizan.catalog.dto.BookCreateRequestDTO;
import com.dizan.catalog.dto.BookDetailResponseDTO;
import com.dizan.catalog.dto.BookUpdateRequestDTO;

public interface BookService {
	public BookDetailResponseDTO findBookDetailById(String bookId);
	
	public List<BookDetailResponseDTO> findBookListDetail();
	
	public void createNewBook(BookCreateRequestDTO dto);
	
	public void updateBook(Long bookId, BookUpdateRequestDTO dto);
	
	public void deleteBook(Long bookId);
}
