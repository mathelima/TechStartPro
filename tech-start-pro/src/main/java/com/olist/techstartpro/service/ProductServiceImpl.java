package com.olist.techstartpro.service;

import com.olist.techstartpro.domain.Product;
import com.olist.techstartpro.exception.ProductNotFoundException;
import com.olist.techstartpro.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository repository;

    @Override
    public List<Product> getProducts() {
        return Optional.of(repository.findAll())
                .orElseThrow(() -> new ProductNotFoundException("No products found"));
    }
}
