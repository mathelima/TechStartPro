package com.olist.techstartpro.repository;

import com.olist.techstartpro.domain.Category;
import com.olist.techstartpro.domain.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findProductByFields(Product product) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> productDB = query.from(Product.class);
        Path<String> namePath = productDB.get("name");
        Path<String> descriptionPath = productDB.get("description");
        Path<BigDecimal> valuePath = productDB.get("value");
        Path<Long> categoryPath = productDB.get("categoryId");

        List<Predicate> predicates = new ArrayList<>();
        if (!product.getName().isEmpty() && product.getName() != null){
            predicates.add(cb.like(namePath, product.getName()));
        }
        if (!product.getDescription().isEmpty() && product.getDescription() != null){
            predicates.add(cb.like(descriptionPath, product.getDescription()));
        }
        if (product.getValue() != null) {
            predicates.add(cb.equal(valuePath, product.getValue()));
        }
        /*if (!product.getCategory().isEmpty()){
            //predicates.add(cb.equal(categoryPath, product.getCategory()));
            for (Category category : product.getCategory()) {
                predicates.add(cb.equal(categoryIdPath, category.getId()));
            }
        }*/
        query.select(productDB)
                .where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query)
                .getResultList();
    }
}
