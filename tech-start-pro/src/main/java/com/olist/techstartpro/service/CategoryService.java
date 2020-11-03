package com.olist.techstartpro.service;

import com.olist.techstartpro.domain.Category;
import com.olist.techstartpro.exception.CategoryNotFoundException;

public interface CategoryService {
    Category getCategory(Long id) throws CategoryNotFoundException;
}
