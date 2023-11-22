package com.ms.msproductservice.service;

import com.ms.msproductservice.model.ProductRequest;
import com.ms.msproductservice.model.ProductResponse;

public interface ProductService {

	long addProduct(ProductRequest productRequest);

	ProductResponse getProduct(long productId);

	void reduceQuantity(long productId, long quantity);

}
