package com.example.demo.repository;

import com.example.demo.domain.CartItem;
import com.example.demo.domain.ProductToCartItem;
import org.springframework.data.repository.CrudRepository;

public interface ProductToCartItemRepository extends CrudRepository<ProductToCartItem, Long> {
    void deleteByCartItem(CartItem cartItem);
}
