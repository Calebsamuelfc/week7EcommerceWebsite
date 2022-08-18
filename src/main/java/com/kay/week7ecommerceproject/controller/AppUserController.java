package com.kay.week7ecommerceproject.controller;

import com.kay.week7ecommerceproject.dto.AppUserDto;
import com.kay.week7ecommerceproject.model.AppUser;
import com.kay.week7ecommerceproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
public class AppUserController {
    @Autowired
    private UserService userService;

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
    @PostMapping({"/login", "/"})
    public ModelAndView userLogin (AppUser appUser){
        return userService.login(appUser);
    }
}
