package com.dizan.catalog.dto;


import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class CategoryCreateUpdateRequestDTO implements Serializable{
	
	private static final long serialVersionUID = -7627491377587723651L;

	@NotBlank
	private String code;
	
	@NotBlank
	private String name;
	
	private String description;
}
