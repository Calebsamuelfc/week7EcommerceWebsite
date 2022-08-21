package com.kay.week7ecommerceproject.service;

import com.kay.week7ecommerceproject.dto.ProductDto;
import com.kay.week7ecommerceproject.exception.CustomAppException;
import com.kay.week7ecommerceproject.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductDto productDto);

    void updateProduct(ProductDto productDto, Long id);

    void deleteProduct(Long id);

    List<Product> displayAllProducts();

    Product viewProduct(Long id) throws CustomAppException;
}
