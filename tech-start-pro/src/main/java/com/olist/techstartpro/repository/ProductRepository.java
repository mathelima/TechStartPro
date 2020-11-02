package com.olist.techstartpro.repository;

import com.olist.techstartpro.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameAndDescriptionAndValueAndCategoryId(String name, String description, BigDecimal value, Long categoryId);
    List<Product> findByNameOrDescriptionOrValueOrCategoryId(String name, String description, BigDecimal value, Long categoryId);
}
