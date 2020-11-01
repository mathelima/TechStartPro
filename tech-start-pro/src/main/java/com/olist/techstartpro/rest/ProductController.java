package com.olist.techstartpro.rest;

import com.olist.techstartpro.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService service;
}
