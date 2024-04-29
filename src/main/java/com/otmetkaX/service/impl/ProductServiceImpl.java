package com.otmetkaX.service.impl;

import com.otmetkaX.exception.CustomException;
import com.otmetkaX.model.Product;
import com.otmetkaX.repository.ProductRepository;
import com.otmetkaX.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final EntityManager entityManager;
    private final ProductRepository repository;
    @Autowired
    public ProductServiceImpl(ProductRepository repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    @Override
    public void save(Product product) throws CustomException {
        repository.save(product);
    }

    @Override
    public List<Product> findTop20ByCountToDay() {
        Query query = entityManager.createQuery("SELECT p FROM Product p WHERE p.countToDay > 0 ORDER BY p.countToDay DESC");
        query.setMaxResults(20);
        return query.getResultList();
    }

    @Override
    public Product findByName(String name) throws CustomException {
        Optional<Product> product = repository.findByName(name);
        if (!product.isPresent()) {
            throw new CustomException("PRODUCT_NOT_FOUND", 404);
        }
        return product.get();
    }
    @Override
    public Product findById(long id) throws CustomException {
        Optional<Product> product = repository.findById(id);
        if (!product.isPresent()) {
            throw new CustomException("PRODUCT_NOT_FOUND", 404);
        }
        return product.get();
    }

    @Override
    public void incrementCountToDay(Product product) throws CustomException {
        long currentCount = product.getCountToDay();
        long count = product.getCount();
        product.setCountToDay(currentCount + 1);
        product.setCount(count + 1);
        save(product);
    }
    @Override
    public void resetCountToDay() {
        repository.resetCountToDay();
    }

}
