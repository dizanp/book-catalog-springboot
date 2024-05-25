package com.dizan.catalog.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class Book implements Serializable {
    
	private static final long serialVersionUID = 2743201915653707101L;

	private Long id;
	
	private String title;
	
	private String description;
	
	private Author author;
}
