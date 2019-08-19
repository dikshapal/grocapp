package com.example.demo.service.impl;


import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        List<Product> productList = (List<Product>) productRepository.findAll();

        List<Product> activeProductList = new ArrayList<>();

        for (Product product : productList) {
            if(product.isActive()) {
                activeProductList.add(product);
            }
        }

        return activeProductList;
    }

    public Product findOne(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> blurrySearch(String keyword) {
        List<Product> productList = productRepository.findByTitleContaining(keyword);

        List<Product> activeProductList = new ArrayList<>();

        for (Product product : productList) {
            if(product.isActive()) {
                activeProductList.add(product);
            }
        }

        return activeProductList;
    }

    public void removeOne(Long id) {
        productRepository.deleteById(id);
    }
}
