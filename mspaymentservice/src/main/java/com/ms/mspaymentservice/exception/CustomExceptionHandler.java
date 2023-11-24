package com.ms.mspaymentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ms.mspaymentservice.model.ErrorResponse;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
		return new ResponseEntity<ErrorResponse>(
				new ErrorResponse().builder().errorMessage(ex.getErrorMessage()).errorCode(ex.getErrorCode()).build(),
				HttpStatus.NOT_FOUND);

	}

}
