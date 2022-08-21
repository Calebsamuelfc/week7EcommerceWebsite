package com.kay.week7ecommerceproject.service.serviceImpl;

import com.kay.week7ecommerceproject.dto.ProductDto;
import com.kay.week7ecommerceproject.exception.CustomAppException;
import com.kay.week7ecommerceproject.model.Product;
import com.kay.week7ecommerceproject.repository.ProductRepository;
import com.kay.week7ecommerceproject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        return productRepository.save(product);
    }
    @Override
    public void updateProduct(ProductDto productDto, Long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            Product dbProduct = product.get();
            dbProduct.setProductName(productDto.getProductName());
            dbProduct.setCategory(productDto.getCategory());
            dbProduct.setPrice(productDto.getPrice());
            productRepository.save(dbProduct);
        }else{
            System.out.println("product not found");
            //todo use custom exception
        }
    }
    @Override
    public void deleteProduct(Long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            Product dbProduct = product.get();
            productRepository.delete(dbProduct);
        }else{
            System.out.println("product not found");
            //todo use custom exception
        }
    }
    @Override
    public List<Product> displayAllProducts(){
        return productRepository.findAll();
    }
    @Override
    public Product viewProduct(Long id) throws CustomAppException {
        return productRepository.findById(id)
                .orElseThrow(()-> new CustomAppException("Product doesn't exit."));
    }

}
