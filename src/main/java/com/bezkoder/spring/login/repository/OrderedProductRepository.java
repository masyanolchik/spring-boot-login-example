package com.bezkoder.spring.login.repository;

import com.bezkoder.spring.login.models.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedProductRepository extends JpaRepository<OrderedProduct, Long> {
}
