package com.olist.techstartpro.repository;

import com.olist.techstartpro.domain.Product;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> findProductByFields(Product product);
}
