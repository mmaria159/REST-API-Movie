package com.endava.movie.api.controller;

import com.endava.movie.dao.UserDao;
import com.endava.movie.model.Role;
import com.endava.movie.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserDao userDao;

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("user") User user, Model model) {
        if(!user.getUsername().isEmpty() && !user.getEmail().isEmpty() && !user.getPassword().isEmpty()){
            user.setEnabled(true);
            user.setRoles(Collections.singleton(Role.USER));
            userDao.save(user);
            return "redirect:/login";
        } else {
            return "redirect:/registration";
        }


    }
}
