package com.ms.msproductservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ms.msproductservice.model.ErrorResponse;

@ControllerAdvice
public class RestEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ProductServiceCustomException.class)
	public ResponseEntity<ErrorResponse> handleProductServiceException(ProductServiceCustomException exception){
		return new ResponseEntity<ErrorResponse>(new ErrorResponse()
				.builder()
				.errorMessage(exception.getMessage())
				.errorCOde(exception.getErrorCode()).build(),HttpStatus.NOT_FOUND);
	}

}
