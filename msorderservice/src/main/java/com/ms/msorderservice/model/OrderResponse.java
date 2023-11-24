package com.ms.msorderservice.model;

import java.time.Instant;

import com.ms.msorderservice.external.client.response.ProductResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
	
	private long orderId;
	private Instant orderDate;
	private String orderStatus;
	private long amount;
	private ProductDetails productDetails;
	private PaymentDetails paymentDetails;
	

}
