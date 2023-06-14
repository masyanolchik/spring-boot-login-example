package com.bezkoder.spring.login.repository;

import com.bezkoder.spring.login.models.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}
