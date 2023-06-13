package com.bezkoder.spring.login.repository;

import com.bezkoder.spring.login.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
