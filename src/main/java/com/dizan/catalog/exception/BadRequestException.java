package com.dizan.catalog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3077787660462773821L;

	public BadRequestException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}