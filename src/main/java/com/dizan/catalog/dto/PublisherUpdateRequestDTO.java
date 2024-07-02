package com.dizan.catalog.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PublisherUpdateRequestDTO implements Serializable{
	
	private static final long serialVersionUID = 2521964149546323247L;

	private String publisherName;
	
	private String companyName;
	
	private String address;
}
