package com.kavya.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoSuchElementFoundException extends ResponseStatusException {

	
	public NoSuchElementFoundException(HttpStatus message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
