package com.ms.msorderservice.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException{
	
	private String errorCode;
	
	public CustomException(String errorMessage, String errorCode) {
		super(errorMessage);
		this.errorCode=errorCode;
		
		
	}

}
