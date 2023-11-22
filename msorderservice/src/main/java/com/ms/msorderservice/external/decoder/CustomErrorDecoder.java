package com.ms.msorderservice.external.decoder;

import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.msorderservice.exception.CustomException;
import com.ms.msorderservice.external.decoder.response.BackendErrorResponse;
import com.netflix.servo.jmx.ObjectNameMapper;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {

		ObjectMapper objectMapper = new ObjectMapper();

		log.info("uri : {}", response.request().url());
		log.info("headers : {}", response.request().headers());

		try {
			BackendErrorResponse backendErrorResponse = objectMapper.readValue(response.body().asInputStream(),
					BackendErrorResponse.class);
			return new CustomException(backendErrorResponse.getErrorMessage(), backendErrorResponse.getErrorCOde());
		} catch (StreamReadException e) {
			throw new CustomException("Internal Server error","INTERNAL_SERVER_ERROR");
		} catch (DatabindException e) {
			throw new CustomException("Internal Server error","INTERNAL_SERVER_ERROR");
		} catch (IOException e) {
			throw new CustomException("Internal Server error","INTERNAL_SERVER_ERROR");
			
		}

		
	}

}
