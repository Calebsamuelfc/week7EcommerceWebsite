package com.kay.week7ecommerceproject.service.serviceImpl;

import com.kay.week7ecommerceproject.dto.AppUserDto;
import com.kay.week7ecommerceproject.model.AppUser;
import com.kay.week7ecommerceproject.model.Product;
import com.kay.week7ecommerceproject.repository.AppUserRepository;
import com.kay.week7ecommerceproject.service.ProductService;
import com.kay.week7ecommerceproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AppUserRepository appUserRepository;
    private final HttpSession httpSession;
    private final ProductService productService;


    @Override
    public AppUser createUser(AppUserDto userDto) {
        Optional<AppUser> user = appUserRepository.findAppUserByEmail(userDto.getEmail());
        if(user.isPresent()){
            //Todo handle exception properly
            throw new RuntimeException();
        }
        AppUser appUser = AppUser.builder()
                .fullName(userDto.getFullName())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .build();
        return appUserRepository.save(appUser);
    }

    @Override
    public ModelAndView login(AppUser appUser) {
        if (appUser.getEmail().equals("admin@gmail.com") && appUser.getPassword().equals("1234")){
            ModelAndView mav = new ModelAndView("admin_home");
            List<Product> productList = productService.displayAllProducts();
            mav.addObject("productList",productList);
            return mav;
        }
        Optional<AppUser> user = appUserRepository.findAppUserByEmailAndPassword(appUser.getEmail(), appUser.getPassword());
        if(user.isPresent()){
            httpSession.setAttribute("loggedInUser", user);
            ModelAndView mav = new ModelAndView("home");
            mav.addObject("user", user);
            return mav;
        }else {
            return new ModelAndView("login");
        }








    }
}
