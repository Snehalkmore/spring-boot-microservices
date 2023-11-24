package com.ms.mspaymentservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.mspaymentservice.entities.TransactionDetails;

@Repository
public interface PaymentRepository extends JpaRepository<TransactionDetails, Long> {
	
	public TransactionDetails findByOrderId(long orderId);

}
