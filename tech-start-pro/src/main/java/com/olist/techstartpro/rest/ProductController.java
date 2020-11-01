package com.olist.techstartpro.rest;

import com.olist.techstartpro.domain.Product;
import com.olist.techstartpro.exception.ProductNotFoundException;
import com.olist.techstartpro.service.ProductService;
import com.olist.techstartpro.service.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = null;
        try {
            products = service.getProducts();
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

}
