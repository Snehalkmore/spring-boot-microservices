package com.ms.msorderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.msorderservice.model.OrderRequest;
import com.ms.msorderservice.model.OrderResponse;
import com.ms.msorderservice.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("/placeOrder")
	public ResponseEntity<Long>placeOrder(@RequestBody OrderRequest orderRequest){
		Long orderId = orderService.addOrder(orderRequest);
		return new ResponseEntity(orderId, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderResponse>getOrderById(@PathVariable("id")long id){
		
		OrderResponse orderResposne = orderService.getOrderById(id);
		return new ResponseEntity<OrderResponse>(orderResposne, HttpStatus.OK);
	}

}
