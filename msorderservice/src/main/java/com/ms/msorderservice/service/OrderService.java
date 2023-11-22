package com.ms.msorderservice.service;

import com.ms.msorderservice.model.OrderRequest;
import com.ms.msorderservice.model.OrderResponse;

public interface OrderService {

	Long addOrder(OrderRequest orderRequest);

	OrderResponse getOrderById(long id);

}
