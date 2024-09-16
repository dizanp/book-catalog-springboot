package com.dizan.catalog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundExeception extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3121235368678164722L;

	public ResourceNotFoundExeception(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
