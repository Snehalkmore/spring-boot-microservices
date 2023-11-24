package com.ms.msorderservice.model;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PaymentDetails {

	private long paymentId;
	
	private long orderId;
	
	private String paymentMode;
	
	private String transactionReferenceNumber;
	
	private Instant paymentDate;
	
	private String paymentStatus;
	
	private long amount;

}
