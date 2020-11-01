package com.olist.techstartpro.service;

import com.olist.techstartpro.domain.Product;
import com.olist.techstartpro.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    List<Product> getProducts() throws ProductNotFoundException;

}
