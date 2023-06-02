package com.kavya.exception;

import java.util.Date;
//import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;



@ControllerAdvice    //for global exception handler
public class GlobalExceptionHandler {
	
	
	
	
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception exception,
			WebRequest request)
	{
		ErrorDetails details = new ErrorDetails(new Date(),exception.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(details,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
//	@ExceptionHandler(NoSuchElementFoundException.class)
//	  @ResponseStatus(HttpStatus.NOT_FOUND)
//	  public ResponseEntity<ErrorDetails> handleItemNotFoundException(
//	      NoSuchElementFoundException exception, 
//	      WebRequest request
//	  ){
//		ErrorDetails details = new ErrorDetails(new Date(),exception.getMessage(), request.getDescription(false));
//	  
//	    return new ResponseEntity<ErrorDetails>(details, HttpStatus.NOT_FOUND);
//	  }
//	
	
	
	
	@ExceptionHandler(DonorNotFoundException.class)
	public ResponseEntity<?> handleDonorNotFoundException(DonorNotFoundException donorNotFoundException,
			WebRequest request)
	{
		ErrorDetails details = new ErrorDetails(new Date(),donorNotFoundException.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(details,HttpStatus.NOT_FOUND);
		
	}
	
//	@ExceptionHandler(DonorListNotFoundException.class)
//	public ResponseEntity<List<Donor>> handleDonorListNotFoundException(DonorListNotFoundException donorListNotFoundException,
//			WebRequest request)
//	{
//		ErrorDetails details = new ErrorDetails(new Date(),donorListNotFoundException.getMessage(), request.getDescription(false));
//		
//		return new ResponseEntity<List<Donor>>(HttpStatus.NOT_FOUND);
//		
//	}
	
	
	
	
	
	

}
