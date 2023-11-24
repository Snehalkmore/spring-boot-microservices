package com.ms.mspaymentservice.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.mspaymentservice.entities.TransactionDetails;
import com.ms.mspaymentservice.exception.CustomException;
import com.ms.mspaymentservice.model.PaymentRequest;
import com.ms.mspaymentservice.model.PaymentResponse;
import com.ms.mspaymentservice.repos.PaymentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentRepository paymentRepository;
	
	@Override
	public long doPayment(PaymentRequest paymentRequest) {
		log.info("logging payment info...");
		
		TransactionDetails transactionDetails = new TransactionDetails().builder()
				.amount(paymentRequest.getAmount())
				.paymentDate(Instant.now())
				.orderId(paymentRequest.getOrderId())
				.paymentStatus("SUCCESS")
				.paymentMode(paymentRequest.getPaymentMode().name()).build();
		
		transactionDetails = paymentRepository.save(transactionDetails);
		
		log.info("transaction completed successfully");
		return transactionDetails.getPaymentId();
	}

	@Override
	public PaymentResponse getPaymentDetailsByOrderId(long orderId) {
		
		TransactionDetails payment = paymentRepository.findByOrderId(orderId);
				
		
		PaymentResponse paymentResponse = new PaymentResponse().builder()
				.paymentId(payment.getPaymentId()).amount(payment.getAmount())
				.orderId(payment.getOrderId())
				.paymentDate(payment.getPaymentDate())
				.paymentMode(payment.getPaymentMode())
				.paymentStatus(payment.getPaymentStatus())
				.build();
		return paymentResponse;
	}

}
