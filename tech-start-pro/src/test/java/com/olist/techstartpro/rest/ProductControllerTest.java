package com.olist.techstartpro.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olist.techstartpro.IntegrationTestBase;
import com.olist.techstartpro.domain.Category;
import com.olist.techstartpro.domain.Product;
import com.olist.techstartpro.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest extends IntegrationTestBase {

    @Autowired
    private ProductController productController;
    @Autowired
    private ProductRepository productRepository;
    private List<Long> categoriesId;
    private Product product;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        this.categoriesId = createCategoriesId();
        this.product = createProduct();
        this.objectMapper = new ObjectMapper();
        productRepository.save(product);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    void successfullyPostProduct() throws Exception {
        String content = objectMapper.writeValueAsString(product);

        mockMvc.perform(
                post("/product")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(product.getName())))
                .andExpect(jsonPath("$.description", is(product.getDescription())))
                .andExpect(jsonPath("$.value", is(product.getValue().doubleValue())));
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    void successfullyDeleteProduct() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders.delete("/product/{id}", 1) )
                .andExpect(status().isOk());
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    void successfullyUpdateProduct() throws Exception {
        String content = objectMapper.writeValueAsString(product);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/product/{id}", 1)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(product.getName())))
                .andExpect(jsonPath("$.description", is(product.getDescription())))
                .andExpect(jsonPath("$.value", is(product.getValue().doubleValue())));
    }

    @Test
    void failureDeleteProduct() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders.delete("/product/{id}", 100) )
                .andExpect(status().isInternalServerError());
    }

    private List<Long> createCategoriesId(){
        List<Long> categoriesIdList = new ArrayList<>();
        categoriesIdList.add((long) 1);
        categoriesIdList.add((long) 2);
        return categoriesIdList;
    }

    private Product createProduct(){
        return Product.builder().name("sofá").description("branco").value(BigDecimal.valueOf(700.00)).categoryId(categoriesId).build();
    }

    @AfterEach
    @Transactional
    public void tearDown(){
        productRepository.deleteAll();
    }
}
