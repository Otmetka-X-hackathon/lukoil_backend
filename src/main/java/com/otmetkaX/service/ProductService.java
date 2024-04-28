package com.otmetkaX.service;

import com.otmetkaX.exception.CustomException;
import com.otmetkaX.model.Product;

import java.util.List;

public interface ProductService {
        void save(Product product) throws CustomException;
        List<Product> findTop20ByCountToDay();
        Product findByName(String name)throws CustomException;

        Product findById(long id)throws CustomException;
        void incrementCountToDay(Product product) throws CustomException;
        void resetCountToDay();
}
