package com.kay.week7ecommerceproject.repository;

import com.kay.week7ecommerceproject.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
