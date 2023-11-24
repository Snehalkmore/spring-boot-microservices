package com.ms.msorderservice.external.client.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductResponse {

	private Long productId;

	private String productName;

	private Long price;

	private Long quantity;
}
