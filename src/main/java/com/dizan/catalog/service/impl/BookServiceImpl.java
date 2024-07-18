package com.dizan.catalog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import com.dizan.catalog.domain.Author;
import com.dizan.catalog.domain.Book;
import com.dizan.catalog.domain.Category;
import com.dizan.catalog.domain.Publisher;
import com.dizan.catalog.dto.BookCreateRequestDTO;
import com.dizan.catalog.dto.BookDetailDTO;
import com.dizan.catalog.dto.BookUpdateRequestDTO;
import com.dizan.catalog.repository.AuthorRepository;
import com.dizan.catalog.repository.BookRepository;
import com.dizan.catalog.service.AuthorService;
import com.dizan.catalog.service.BookService;
import com.dizan.catalog.service.CategoryService;
import com.dizan.catalog.service.PublisherService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service("bookService")
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;

	private final AuthorService authorService;

	private final CategoryService categoryService;

	private final PublisherService publisherService;

	@Override
	public BookDetailDTO findBookDetailById(Long bookId) {
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new com.dizan.catalog.exception.BadRequestException("book_id.invalid"));
		BookDetailDTO dto = new BookDetailDTO();
		dto.setBookId(book.getId());
//		dto.setAuthorName(book.getAuthor().getName());
		dto.setBookTitle(book.getTitle());
		dto.setBookDescription(book.getDescription());
		return dto;
	}

	@Override
	public List<BookDetailDTO> findBookListDetail() {
		List<Book> books = bookRepository.findAll();
		return books.stream().map((b) -> {
			BookDetailDTO dto = new BookDetailDTO();
//			dto.setAuthorName(b.getAuthor().getName());
			dto.setBookDescription(b.getDescription());
			dto.setBookId(b.getId());
			dto.setBookTitle(b.getTitle());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public void createNewBook(BookCreateRequestDTO dto) {
		List<Author> authors = authorService.findAuthors(dto.getAuthorIdList());
		List<Category> categories = categoryService.findCategories(dto.getCategoryList());
		Publisher publisher = publisherService.findPublisher(dto.getPublisherId());

		Book book = new Book();
		book.setAuthors(authors);
		book.setCategories(categories);
		book.setPublisher(publisher);
		book.setTitle(dto.getBookTitle());
		book.setDescription(dto.getDescription());
		bookRepository.save(book);
	}

	@Override
	public void updateBook(Long bookId, BookUpdateRequestDTO dto) {
		// get book from repository
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new com.dizan.catalog.exception.BadRequestException("book_id.invalid"));
		// update
		book.setTitle(dto.getBookTitle());
		book.setDescription(dto.getDescription());
		// save
		bookRepository.save(book);
	}

	@Override
	public void deleteBook(Long bookId) {
		bookRepository.deleteById(bookId);
	}

//	public BookRepository getBookRepository() {
//		return bookRepository;
//	}
//
//	public void setBookRepository(BookRepository bookRepository) {
//		this.bookRepository = bookRepository;
//	}

}
