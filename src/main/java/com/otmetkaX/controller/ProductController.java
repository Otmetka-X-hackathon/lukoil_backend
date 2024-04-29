package com.otmetkaX.controller;

import com.otmetkaX.exception.CustomException;
import com.otmetkaX.model.Product;
import com.otmetkaX.response.ResponseMessage;
import com.otmetkaX.response.ResponseMessageObject;
import com.otmetkaX.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/lukoil")
public class ProductController {
    private final ProductService service;
    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }
    @GetMapping("top20/product")
    public CompletableFuture<ResponseEntity<ResponseMessage>> getTop20Products() throws CustomException {
        return CompletableFuture.supplyAsync(() -> {
            List<Product> productList = service.findTop20ByCountToDay();
            ResponseMessage response = new ResponseMessageObject("Successes", null, 200, productList);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        });
    }
}
