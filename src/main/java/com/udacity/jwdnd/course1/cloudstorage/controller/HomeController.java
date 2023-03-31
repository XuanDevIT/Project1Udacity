package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String newFiles(Model model) {
        model.addAttribute("file", new File());
        return "home";
    }

    @GetMapping("/signup")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @GetMapping("/result")
    public String result(Model model) {
        return "result";
    }
    @GetMapping({"/","/login"})
    public String login(Model model) {
        return "login";
    }
}
