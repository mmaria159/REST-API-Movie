package com.endava.movie.api.controller;

import com.endava.movie.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index.html");
    }

    @GetMapping("/add")
    public ModelAndView addMovie() {
        return new ModelAndView("addMovie.html");
    }

    @GetMapping("/update")
    public ModelAndView updateUser() {
        return new ModelAndView("updateMovie.html");
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login.html");
    }

    @GetMapping("/registration")
    public ModelAndView registration(Model model) {
        model.addAttribute("user", new User());
        return new ModelAndView("registration.html");
    }
}
