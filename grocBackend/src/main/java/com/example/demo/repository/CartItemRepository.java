package com.example.demo.repository;

import com.example.demo.domain.CartItem;
import com.example.demo.domain.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {
    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

//	List<CartItem> findByOrder(Order order);
}