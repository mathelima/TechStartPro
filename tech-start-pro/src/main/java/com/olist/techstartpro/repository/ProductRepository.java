package com.olist.techstartpro.repository;

import com.olist.techstartpro.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom{
}
