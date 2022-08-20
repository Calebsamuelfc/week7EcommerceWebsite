package com.kay.week7ecommerceproject.service.serviceImpl;

import com.kay.week7ecommerceproject.dto.ProductDto;
import com.kay.week7ecommerceproject.model.Product;
import com.kay.week7ecommerceproject.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl {
    private final ProductRepository productRepository;

    public Product createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        return productRepository.save(product);
    }
//    public void updateProduct(ProductDto productDto, Long id){
//        Optional<Product>
//    }
}
