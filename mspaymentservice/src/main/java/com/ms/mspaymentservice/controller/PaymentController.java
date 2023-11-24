package com.ms.mspaymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.mspaymentservice.model.PaymentRequest;
import com.ms.mspaymentservice.model.PaymentResponse;
import com.ms.mspaymentservice.repos.PaymentRepository;
import com.ms.mspaymentservice.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	@PostMapping
	public ResponseEntity<Long>doPayment(@RequestBody PaymentRequest paymentRequest){
		long paymentId = paymentService.doPayment(paymentRequest);
		return new ResponseEntity<Long>(paymentId,HttpStatus.CREATED);
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(@PathVariable("orderId") long orderId){
		PaymentResponse paymentResponse = paymentService.getPaymentDetailsByOrderId(orderId);
		return new ResponseEntity<PaymentResponse>(paymentResponse, HttpStatus.OK);
		
	}
	
	
	
	
	

	
}
