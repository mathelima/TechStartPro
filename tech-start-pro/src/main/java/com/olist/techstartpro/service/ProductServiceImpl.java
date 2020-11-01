package com.olist.techstartpro.service;

import com.olist.techstartpro.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements  ProductService{

    @Autowired
    ProductRepository repository;
}
