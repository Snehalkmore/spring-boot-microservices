package com.ms.msorderservice.model;

import com.ms.msorderservice.entities.PaymentMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
	
	private long productId;
	private long quantity;
	private long totalPrice;
	private PaymentMode paymentMode;
	

}
