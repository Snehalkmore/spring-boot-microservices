package com.ms.msorderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductDetails {

	private Long productId;

	private String productName;

	private Long price;

	private Long quantity;
}
