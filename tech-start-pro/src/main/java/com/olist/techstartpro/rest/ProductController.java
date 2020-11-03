package com.olist.techstartpro.rest;

import com.olist.techstartpro.domain.Category;
import com.olist.techstartpro.domain.Product;
import com.olist.techstartpro.domain.ProductDTO;
import com.olist.techstartpro.exception.DatabaseException;
import com.olist.techstartpro.exception.ProductNotFoundException;
import com.olist.techstartpro.repository.CategoryRepository;
import com.olist.techstartpro.service.CategoryService;
import com.olist.techstartpro.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = null;
        try {
            products = productService.getProducts();
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<Product>> getProductByField (@Valid @RequestBody ProductDTO product){
        List<Product> products = null;
        Product persistentProduct = new Product();
        persistentProduct = transformProductDTOIntoProduct(product);
        try {
            products = productService.getProductByField(persistentProduct);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductDTO product) {
        Product savedProduct = null;
        Product persistentProduct = new Product();
        persistentProduct = transformProductDTOIntoProduct(product);
        try{
            savedProduct = productService.createProduct(persistentProduct);
        }catch(DatabaseException e){
            return new ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Product>(savedProduct, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> updatePerson(@PathVariable("id") Long id, @Valid @RequestBody ProductDTO product){
        Product updatedProduct = null;
        Product persistentProduct = new Product();
        persistentProduct = transformProductDTOIntoProduct(product);
        try{
            updatedProduct = productService.updateProduct(id, persistentProduct);
        }catch (DatabaseException e) {
            return new ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(DatabaseException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private Product transformProductDTOIntoProduct (ProductDTO productDTO){
        Product product = new Product();
        List<Category> categories = new ArrayList<Category>();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setValue(productDTO.getValue());
        for(Long categoryId : productDTO.getCategory()){
            categories.add(categoryService.getCategory(categoryId)); }
        product.setCategory(categories);
        return product;
    }

}
