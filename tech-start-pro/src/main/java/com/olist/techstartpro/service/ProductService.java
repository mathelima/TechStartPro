package com.olist.techstartpro.service;

import com.olist.techstartpro.domain.Product;
import com.olist.techstartpro.exception.DatabaseException;
import com.olist.techstartpro.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {

    List<Product> getProducts() throws ProductNotFoundException;

    Product createProduct(Product product) throws DatabaseException;

    Product updateProduct(Long id, Product product) throws DatabaseException;

    void deleteProduct(Long id) throws DatabaseException;
}
