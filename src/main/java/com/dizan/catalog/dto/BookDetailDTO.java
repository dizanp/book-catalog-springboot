package com.dizan.catalog.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class BookDetailDTO implements Serializable{

	private static final long serialVersionUID = 9108194693690310089L;

	private Long bookId;
	
	private String authorName;
	
	private String bookTitle;
	
	private String bookDescription;
	
}
