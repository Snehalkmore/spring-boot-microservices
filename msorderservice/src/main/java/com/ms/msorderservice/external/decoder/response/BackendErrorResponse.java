package com.ms.msorderservice.external.decoder.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BackendErrorResponse {

	private String errorMessage;
	private String errorCOde;
	

}
