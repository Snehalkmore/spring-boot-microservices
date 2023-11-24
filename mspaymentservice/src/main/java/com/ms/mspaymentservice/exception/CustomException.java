package com.ms.mspaymentservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomException extends RuntimeException {

	private String errorMessage;
	private String errorCode;


}
