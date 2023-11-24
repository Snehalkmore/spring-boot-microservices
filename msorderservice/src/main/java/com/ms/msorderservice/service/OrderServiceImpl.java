package com.ms.msorderservice.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ms.msorderservice.entities.Order;
import com.ms.msorderservice.exception.CustomException;
import com.ms.msorderservice.external.client.PaymentService;
import com.ms.msorderservice.external.client.ProductService;
import com.ms.msorderservice.external.client.request.PaymentRequest;
import com.ms.msorderservice.external.client.response.PaymentResponse;
import com.ms.msorderservice.external.client.response.ProductResponse;
import com.ms.msorderservice.model.OrderRequest;
import com.ms.msorderservice.model.OrderResponse;
import com.ms.msorderservice.model.PaymentDetails;
import com.ms.msorderservice.model.ProductDetails;
import com.ms.msorderservice.repos.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Long addOrder(OrderRequest orderRequest) {
		// save order with status created
		// call product service to reduce quantity
		// payment service call if success- COMPLETE else CANCELLED

		productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());

		Order order = Order.builder().productId(orderRequest.getProductId()).quantity(orderRequest.getQuantity())
				.amount(orderRequest.getTotalPrice()).orderDate(Instant.now()).orderStatus("CREATED").build();

		Order savedOrder = orderRepository.save(order);

		log.info("order placed");

		log.info("calling payment service...");
		PaymentRequest paymentRequest = new PaymentRequest().builder().orderId(savedOrder.getOrderId())
				.amount(orderRequest.getTotalPrice()).paymentMode(orderRequest.getPaymentMode()).build();

		String orderStatus = null;
		try {
			paymentService.doPayment(paymentRequest);
			log.info("payment is done successfully....");
			orderStatus = "PLACED";
		} catch (Exception e) {
			log.info("Error occured while payment.changing order status to payment failed. ");
			orderStatus = "PAYMENT_FAILED";
		}
		savedOrder.setOrderStatus(orderStatus);
		orderRepository.save(savedOrder);

		return savedOrder.getOrderId();

	}

	@Override
	public OrderResponse getOrderById(long id) {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new CustomException("Order not found for ID :" + id, "ORDER_NOT_FOUND"));

		log.info("calling product service to fetch product details");
		ProductResponse productResposne = restTemplate
				.getForObject("http://PRODUCT-SERVICE/product/" + order.getProductId(), ProductResponse.class);

		ProductDetails productDetails = new ProductDetails().builder().productId(productResposne.getProductId())
				.price(productResposne.getPrice()).productName(productResposne.getProductName())
				.quantity(productResposne.getQuantity()).build();

		log.info("calling payment service to fetch payment details");
		PaymentResponse paymentResponse = restTemplate
				.getForObject("http://PAYMENT-SERVICE/payment/" + order.getOrderId(), PaymentResponse.class);

		PaymentDetails paymentDetails = new PaymentDetails().builder().amount(paymentResponse.getAmount())
				.orderId(paymentResponse.getOrderId()).paymentDate(paymentResponse.getPaymentDate())
				.paymentId(paymentResponse.getPaymentId()).paymentMode(paymentResponse.getPaymentMode())
				.paymentStatus(paymentResponse.getPaymentStatus())
				.transactionReferenceNumber(paymentResponse.getTransactionReferenceNumber()).build();

		OrderResponse orderResponse = new OrderResponse().builder().amount(order.getAmount())
				.orderDate(order.getOrderDate()).orderId(order.getOrderId()).orderStatus(order.getOrderStatus())
				.productDetails(productDetails).paymentDetails(paymentDetails).build();

		return orderResponse;
	}

}
