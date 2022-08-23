package com.kay.week7ecommerceproject.service;

import com.kay.week7ecommerceproject.dto.AppUserDto;
import com.kay.week7ecommerceproject.dto.LoginDto;
import com.kay.week7ecommerceproject.model.AppUser;
import org.springframework.web.servlet.ModelAndView;

public interface UserService {
    AppUser createUser(AppUserDto userDto);
    ModelAndView login (LoginDto appUser);
}
