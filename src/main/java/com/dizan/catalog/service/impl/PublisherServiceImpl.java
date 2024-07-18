package com.dizan.catalog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dizan.catalog.domain.Publisher;
import com.dizan.catalog.dto.PublisherCreateRequestDTO;
import com.dizan.catalog.dto.PublisherListResponseDTO;
import com.dizan.catalog.dto.PublisherUpdateRequestDTO;
import com.dizan.catalog.dto.ResultPageResponseDTO;
import com.dizan.catalog.exception.BadRequestException;
import com.dizan.catalog.repository.PublisherRepository;
import com.dizan.catalog.service.PublisherService;
import com.dizan.catalog.util.PaginationUtil;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

	private final PublisherRepository publisherRepository;

	@Override
	public void createPublisher(PublisherCreateRequestDTO dto) {
		Publisher publisher = new Publisher();
		publisher.setName(dto.getPublisherName());
		publisher.setCompanyName(dto.getCompanyName());
		publisher.setAddress(dto.getAddress());

		publisherRepository.save(publisher);
	}

	@Override
	public void updatePublisher(String publisherId, PublisherUpdateRequestDTO dto) {

		Publisher publisher = publisherRepository.findBySecureId(publisherId)
				.orElseThrow(() -> new BadRequestException("invalid.publisher_id"));

		publisher.setName(dto.getPublisherName() == null || dto.getPublisherName().isBlank() ? publisher.getName()
				: dto.getPublisherName());
		publisher.setCompanyName(
				dto.getCompanyName() == null || dto.getCompanyName().isBlank() ? publisher.getCompanyName()
						: dto.getCompanyName());
		publisher.setAddress(dto.getAddress());

		publisherRepository.save(publisher);
	}

	@Override
	public ResultPageResponseDTO<PublisherListResponseDTO> findPublisherList(Integer pages, Integer limit,
			String sortBy, String direction, String publisherName) {
		publisherName = StringUtils.isBlank(publisherName) ? "%" : publisherName + "%";
		Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
		Pageable pageable = PageRequest.of(pages, limit, sort);
		Page<Publisher> pageResult = publisherRepository.findByNameLikeIgnoreCase(publisherName, pageable);
		List<PublisherListResponseDTO> dtos = pageResult.stream().map((p) -> {
			PublisherListResponseDTO dto = new PublisherListResponseDTO();
			dto.setPublisherId(p.getSecureId());
			dto.setPublisherName(p.getName());
			dto.setCompanyName(p.getCompanyName());
			return dto;
		}).collect(Collectors.toList());
		return PaginationUtil.createResultPageDTO(dtos, pageResult.getTotalElements(), pageResult.getTotalPages());
	}

	@Override
	public Publisher findPublisher(String publisherId) {
		return publisherRepository.findBySecureId(publisherId)
				.orElseThrow(() -> new BadRequestException("invalid.publisher_id"));
	}

}
