package com.endava.movie.api.controller;

import org.springframework.stereotype.Controller;
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
}
