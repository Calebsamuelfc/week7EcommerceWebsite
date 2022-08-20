package com.kay.week7ecommerceproject.controller;

import com.kay.week7ecommerceproject.dto.AppUserDto;
import com.kay.week7ecommerceproject.model.AppUser;
import com.kay.week7ecommerceproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    private HttpSession httpSession;

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
//        List<User> listOfUsers = userService.getAllUsers();
//        AppUser user =
//        model.addAttribute("listOfUsers", listOfUsers);
        System.out.println(model.getAttribute("user"));
        return "home";
    }
    @PostMapping({"/login", "/"})
    public ModelAndView userLogin (@ModelAttribute("user") AppUser appUser){
        return userService.login(appUser);
    }
}