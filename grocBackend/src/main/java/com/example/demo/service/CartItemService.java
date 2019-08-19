package com.example.demo.service;

import com.example.demo.domain.CartItem;
import com.example.demo.domain.Product;
import com.example.demo.domain.ShoppingCart;
import com.example.demo.domain.User;

import java.util.List;
import java.util.Optional;

public interface CartItemService {

    CartItem addProductToCartItem(Product product, User user, int qty);

    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

//	List<CartItem> findByOrder(Order order);

    CartItem updateCartItem(CartItem cartItem);

    void removeCartItem(CartItem cartItem);

    CartItem findById(Long id);

    CartItem save(CartItem cartItem);

}
