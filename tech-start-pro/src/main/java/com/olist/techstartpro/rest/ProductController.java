package com.olist.techstartpro.rest;

import com.olist.techstartpro.domain.Product;
import com.olist.techstartpro.exception.DatabaseException;
import com.olist.techstartpro.exception.ProductNotFoundException;
import com.olist.techstartpro.service.ProductService;
import com.olist.techstartpro.service.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping(value = "/search")
    public ResponseEntity<List<Product>> getProductByField (@Valid @RequestBody Product product){
        List<Product> products = null;
        try {
            products = service.getProductByField(product);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product savedProduct = null;
        try{
            savedProduct = service.createProduct(product);
        }catch(DatabaseException e){
            return new ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Product>(savedProduct, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> updatePerson(@PathVariable("id") Long id, @Valid @RequestBody Product product){
        Product updatedProduct = null;
        try{
            updatedProduct = service.updateProduct(id, product);
        }catch (DatabaseException e) {
            return new ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        try {
            service.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(DatabaseException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
