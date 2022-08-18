package com.kay.week7ecommerceproject.service.serviceImpl;

import com.kay.week7ecommerceproject.dto.AppUserDto;
import com.kay.week7ecommerceproject.model.AppUser;
import com.kay.week7ecommerceproject.repository.AppUserRepository;
import com.kay.week7ecommerceproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private HttpSession httpSession;


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
//                        mav.addObject("user", 'admin');
//            mav.addObject("email", user.getEmail());
            return new ModelAndView("admin_home");
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
