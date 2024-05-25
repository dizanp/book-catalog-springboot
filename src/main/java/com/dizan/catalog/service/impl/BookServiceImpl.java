package com.dizan.catalog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dizan.catalog.domain.Author;
import com.dizan.catalog.domain.Book;
import com.dizan.catalog.dto.BookCreateDTO;
import com.dizan.catalog.dto.BookDetailDTO;
import com.dizan.catalog.dto.BookUpdateRequestDTO;
import com.dizan.catalog.repository.BookRepository;
import com.dizan.catalog.service.BookService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service("bookService")
public class BookServiceImpl implements BookService{
	
	private final BookRepository bookRepository;

	@Override
	public BookDetailDTO findBookDetailById(Long bookId) {
		Book book = bookRepository.findBookById(bookId);
		BookDetailDTO dto = new BookDetailDTO();
		dto.setBookId(book.getId());
		dto.setAuthorName(book.getAuthor().getName());
		dto.setBookTitle(book.getTitle());
		dto.setBookDescription(book.getDescription());
		return dto;
	}

	@Override
	public List<BookDetailDTO> findBookListDetail() {
		List<Book> books = bookRepository.findAll();
		return books.stream().map((b) -> {
			BookDetailDTO dto = new BookDetailDTO();
			dto.setAuthorName(b.getAuthor().getName());
			dto.setBookDescription(b.getDescription());
			dto.setBookId(b.getId());
			dto.setBookTitle(b.getTitle());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public void createNewBook(BookCreateDTO dto) {
		Author author = new Author();
		author.setName(dto.getAuthorName());
		
		Book book = new Book();
		book.setAuthor(author);
		book.setTitle(dto.getBookTitle());
		book.setDescription(dto.getDescription());
		bookRepository.save(book);
	}

	@Override
	public void updateBook(Long bookId, BookUpdateRequestDTO dto) {
		// get book from repository
		Book book = bookRepository.findBookById(bookId);
		// update
		book.setTitle(dto.getBookTitle());
		book.setDescription(dto.getDescription());
		// save
		bookRepository.update(book);
	}

	@Override
	public void deleteBook(Long bookId) {
		bookRepository.delete(bookId);
	}



//	public BookRepository getBookRepository() {
//		return bookRepository;
//	}
//
//	public void setBookRepository(BookRepository bookRepository) {
//		this.bookRepository = bookRepository;
//	}
	
	
	
}
