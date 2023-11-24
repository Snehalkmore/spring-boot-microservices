package com.ms.mspaymentservice.model;

import java.time.Instant;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
	
	private long paymentId;
	
	private long orderId;
	
	private String paymentMode;
	
	private String transactionReferenceNumber;
	
	private Instant paymentDate;
	
	private String paymentStatus;
	
	private long amount;

}
