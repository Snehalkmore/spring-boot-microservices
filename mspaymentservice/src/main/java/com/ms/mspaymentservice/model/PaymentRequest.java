package com.ms.mspaymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {
	
	private long orderId;
	private long amount;
	private String transactionReferenceNumber;
	private PaymentMode paymentMode;

}
