package com.ms.msorderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ms.msorderservice.model.ErrorResponse;

@ControllerAdvice
public class OrderServiceNotFoundExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResponse> handleorderserviceerrorResponse(CustomException exception){
		return new ResponseEntity<ErrorResponse>(
				new ErrorResponse(exception.getMessage(),exception.getErrorCode())
				, HttpStatus.NOT_FOUND);
	}

}
