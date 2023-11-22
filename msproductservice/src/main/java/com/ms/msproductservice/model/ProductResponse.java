package com.ms.msproductservice.model;

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
