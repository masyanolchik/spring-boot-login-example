package com.bezkoder.spring.login.repository;

import com.bezkoder.spring.login.models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
}
