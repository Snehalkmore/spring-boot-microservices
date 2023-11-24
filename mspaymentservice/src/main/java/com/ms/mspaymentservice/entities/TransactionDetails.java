package com.ms.mspaymentservice.entities;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long paymentId;
	
	@Column(name="ORDER_ID")
	private long orderId;
	
	@Column(name="MODE")
	private String paymentMode;
	
	@Column(name="TX_REF_NO")
	private String transactionReferenceNumber;
	
	@Column(name="PAYMENT_DATE")
	private Instant paymentDate;
	
	@Column(name="PAYMENT_STATUS")
	private String paymentStatus;
	
	@Column(name="AMOUNT")
	private long amount;
	
	
	

}
