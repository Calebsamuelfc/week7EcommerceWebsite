package com.kay.week7ecommerceproject.service.serviceImpl;

import com.kay.week7ecommerceproject.exception.CustomAppException;
import com.kay.week7ecommerceproject.model.AppUser;
import com.kay.week7ecommerceproject.model.Cart;
import com.kay.week7ecommerceproject.model.Product;
import com.kay.week7ecommerceproject.repository.CartRepository;
import com.kay.week7ecommerceproject.repository.ProductRepository;
import com.kay.week7ecommerceproject.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final HttpSession httpSession;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    @Override
    public Cart createCart(AppUser appUser) {
        Cart cart = Cart.builder()
                .user(appUser)
                .build();
        return cartRepository.save(cart);
    }

    @Override
    public  void addToCart(Long id) throws CustomAppException {
        AppUser appUser = (AppUser) httpSession.getAttribute("loggedInUser");
        Product product = productRepository.findById(id).orElseThrow(()-> new CustomAppException("Product doesn't exit."));
        Optional<Cart> cart = cartRepository.findByUserId(appUser.getId());
        if(cart.isPresent()){
            List<Product> productList = cart.get().getProduct();
            if(!productList.contains(product))productList.add(product);
            cart.get().setProduct(productList);
            cartRepository.save(cart.get());
        }else{
            Cart newCart = createCart(appUser);
            List<Product> newProductList =  new ArrayList<>();
            newProductList.add(product);
            newCart.setProduct(newProductList);
            cartRepository.save(newCart);
        }
    }
    @Override
    public List<Product> viewProductInCart (){
        AppUser appUser = (AppUser) httpSession.getAttribute("loggedInUser");
        Optional<Cart> cart = cartRepository.findByUserId(appUser.getId());
        if (cart.isPresent()){
            return cart.get().getProduct();
        }else{
            return createCart(appUser).getProduct();
        }

    }
}
