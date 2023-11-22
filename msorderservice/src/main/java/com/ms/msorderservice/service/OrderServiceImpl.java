package com.ms.msorderservice.service;

import java.time.Instant;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.msorderservice.entities.Order;
import com.ms.msorderservice.external.client.ProductService;
import com.ms.msorderservice.model.OrderRequest;
import com.ms.msorderservice.model.OrderResponse;
import com.ms.msorderservice.repos.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	private ProductService productService;

	@Override
	public Long addOrder(OrderRequest orderRequest) {
		// save order with status created
		//call product service to reduce quantity
		//payment service call if success- COMPLETE else CANCELLED
		
		Order order = Order.builder()
				.productId(orderRequest.getProductId())
				.quantity(orderRequest.getQuantity())
				.amount(orderRequest.getTotalPrice())
				.orderDate(Instant.now())
				.orderStatus("CREATED")
				.build();
		
		Order savedOrder = orderRepository.save(order);
		
		productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
		log.info("order placed");
		
		return savedOrder.getOrderId();
	}

	@Override
	public OrderResponse getOrderById(long id) {
		return null;
	}

}
