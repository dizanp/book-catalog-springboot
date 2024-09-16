package com.dizan.catalog.dto;

import java.io.Serializable;

import com.dizan.catalog.annotation.LogThisArg;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

//@LogThisArg
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PublisherCreateRequestDTO implements Serializable{
	
	private static final long serialVersionUID = -9023668436231681421L;

	@NotBlank
	private String publisherName;
	
	@NotBlank
	private String companyName;
	
	private String address;
}
