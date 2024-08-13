package com.dio.exception;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handlerBusinessError(IllegalArgumentException businessError){
		return new ResponseEntity<String>(businessError.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handlerNotFoundError(NoSuchElementException notFoundError){
		return new ResponseEntity<String>("Resource ID not found",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<String> handlerUnexpectError(Throwable businessError){
		var message = "Resource handlerUnexpectError. see the logs";
		logger.error(message,businessError);
		return new ResponseEntity<String>(message,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
