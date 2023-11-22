package com.ms.msorderservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.msorderservice.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
