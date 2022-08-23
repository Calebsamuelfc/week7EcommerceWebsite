package com.kay.week7ecommerceproject.service;

import com.kay.week7ecommerceproject.exception.CustomAppException;
import com.kay.week7ecommerceproject.model.AppUser;
import com.kay.week7ecommerceproject.model.Cart;
import com.kay.week7ecommerceproject.model.Product;

import java.util.List;

public interface CartService {
    Cart createCart (AppUser appUser);

    void addToCart(Long id) throws CustomAppException;

    List<Product> viewProductInCart();
}
