package com.bezkoder.spring.login.repository;

import com.bezkoder.spring.login.models.Promo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoRepository extends JpaRepository<Promo, Long> {
}
