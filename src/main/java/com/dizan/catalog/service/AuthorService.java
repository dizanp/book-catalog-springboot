package com.dizan.catalog.service;

import java.util.List;

import com.dizan.catalog.dto.AuthorCreateRequestDTO;
import com.dizan.catalog.dto.AuthorResponseDTO;
import com.dizan.catalog.dto.AuthorUpdateRequestDTO;

public interface AuthorService {
	
	public AuthorResponseDTO findAuthorById(Long id);
	
	public void createNewAuthor(List<AuthorCreateRequestDTO> dto);
	
	public void updateAuthor(Long authorId, AuthorUpdateRequestDTO dto);
	
	public void deleteAuthor(Long authorId);
}
