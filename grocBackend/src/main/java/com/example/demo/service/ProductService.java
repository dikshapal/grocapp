package com.example.demo.service;

import com.example.demo.domain.Product;

import java.util.List;


public interface ProductService {

    List<Product> findAll();

    Product findOne(Long id);

    Product save(Product product);

    List<Product> blurrySearch(String title);

    void removeOne(Long id);
}
