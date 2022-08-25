package com.kay.week7ecommerceproject.controller;

import com.kay.week7ecommerceproject.dto.AppUserDto;
import com.kay.week7ecommerceproject.dto.LoginDto;
import com.kay.week7ecommerceproject.exception.CustomAppException;
import com.kay.week7ecommerceproject.model.AppUser;
import com.kay.week7ecommerceproject.model.Product;
import com.kay.week7ecommerceproject.service.CartService;
import com.kay.week7ecommerceproject.service.ProductService;
import com.kay.week7ecommerceproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Controller
public class AppUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    ProductService productService;

    @GetMapping("/signup")
    public String  signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public ModelAndView createAccount(@ModelAttribute AppUserDto user) {
        AppUser appUser = userService.createUser(user);

        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", appUser);
        if(Objects.nonNull(appUser)) {
            return mav;
        } else {
            return new ModelAndView("signup");
        }
    }
    @GetMapping({"/login", "/"})
    public ModelAndView getLoginPage(){
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new AppUser());
        return mav;
    }
    @GetMapping("/home")
    public String  home(Model model) {
        List<Product> productList = productService.displayAllProducts();
        model.addAttribute("productList",productList);
        return "home";
    }
    @GetMapping("/admin_home")
    public String adminHome(Model model) {
        List<Product> productList = productService.displayAllProducts();
        model.addAttribute("productList",productList);
        return "admin_home";
    }
    @PostMapping({"/login", "/"})
    public ModelAndView userLogin (@ModelAttribute("user") LoginDto loginDto){
        return userService.login(loginDto);
    }
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
    @GetMapping("/add_to_cart/{id}")
    public String addProductToCart(@PathVariable String id) throws CustomAppException {
        cartService.addToCart(Long.parseLong(id));
        return "redirect:/home";
    }
    @GetMapping("/remove_from_cart/{id}")
    public String removeFromCart(@PathVariable String id) throws CustomAppException {
        cartService.removeFromCart(Long.parseLong(id));
        return "redirect:/home";
    }
    @GetMapping("/view_cart")
    public String viewUserCart (Model model){
        List<Product> productList = cartService.viewProductInCart();
        model.addAttribute("productList",productList);
        return "view_cart";
    }

}
