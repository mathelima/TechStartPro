package com.olist.techstartpro.service;

import com.olist.techstartpro.domain.Category;
import com.olist.techstartpro.exception.CategoryNotFoundException;
import com.olist.techstartpro.exception.DatabaseException;
import com.olist.techstartpro.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository repository;
    @Override
    public Category getCategory(Long id) throws CategoryNotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException("Category not found"));
    }
}
