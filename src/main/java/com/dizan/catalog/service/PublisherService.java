package com.dizan.catalog.service;

import com.dizan.catalog.domain.Publisher;
import com.dizan.catalog.dto.PublisherCreateRequestDTO;
import com.dizan.catalog.dto.PublisherListResponseDTO;
import com.dizan.catalog.dto.PublisherResponseDTO;
import com.dizan.catalog.dto.PublisherUpdateRequestDTO;
import com.dizan.catalog.dto.ResultPageResponseDTO;

public interface PublisherService {
	
	public void createPublisher(PublisherCreateRequestDTO dto);
	
	public Publisher findPublisher(String publisherId);
	
	public void updatePublisher(String publisherId, PublisherUpdateRequestDTO dto);
	
	public ResultPageResponseDTO<PublisherListResponseDTO> findPublisherList(Integer pages, Integer Limit, 
			String sortBy, String direction, String publisherName);
	
	public PublisherResponseDTO constructDTO(Publisher publisher);
}
