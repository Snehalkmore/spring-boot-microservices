package com.ms.mspaymentservice.service;

import com.ms.mspaymentservice.model.PaymentRequest;
import com.ms.mspaymentservice.model.PaymentResponse;

public interface PaymentService {

	long doPayment(PaymentRequest paymentRequest);

	PaymentResponse getPaymentDetailsByOrderId(long orderId);

}
