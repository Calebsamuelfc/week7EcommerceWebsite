package com.kay.week7ecommerceproject.controller;

import com.kay.week7ecommerceproject.dto.AppUserDto;
import com.kay.week7ecommerceproject.dto.ProductDto;
import com.kay.week7ecommerceproject.exception.CustomAppException;
import com.kay.week7ecommerceproject.model.AppUser;
import com.kay.week7ecommerceproject.model.Product;
import com.kay.week7ecommerceproject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final ProductService productService;
    @GetMapping({"/create_product"})
    public String getCreateProductPage(Model model){
//        model.addAttribute("product", new ProductDto());
        return "create_product";
    }
    @PostMapping("/create_product")
    public String createProduct(@ModelAttribute ProductDto productDto) {
        Product product = productService.createProduct(productDto);
        if(Objects.nonNull(product)) {
            return "redirect:/admin_home";
        } else {
            return "redirect:/create_product";
        }
    }
    @GetMapping({"/edit_product/{id}"})
    public String getEditProductPage(@PathVariable String id, Model model) throws CustomAppException {
        Product product = productService.viewProduct(Long.parseLong(id));
        model.addAttribute("product", product);
        return "edit_product";
    }
    @PostMapping("/edit_product/{id}")
    public String editProduct(@ModelAttribute ProductDto productDto, @PathVariable String id) {
        productService.updateProduct(productDto, Long.parseLong(id));
        return "redirect:/admin_home";
    }
    @GetMapping("/delete_product/{id}")
    public String deleteProduct(@PathVariable String id) {
        productService.deleteProduct(Long.parseLong(id));
        return "redirect:/admin_home";
    }

}
