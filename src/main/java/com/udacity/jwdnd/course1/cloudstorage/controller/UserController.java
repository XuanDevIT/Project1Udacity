package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        //return "user-list";
        return "home";
    }

    @GetMapping("/getUserById/{id}")
    public String getUserById(@PathVariable("id") Integer id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        //return "user-detail";
        return "home";
    }

    @PostMapping("/add-users")
    public String createUser(@ModelAttribute("user") User user, Model model) {
        userService.save(user);
        return "login";
    }

    @PutMapping("/edit-users/{id}")
    public String editUser(@PathVariable("id") Integer id,@RequestBody User user , Model model) {
        userService.update(id, user);
        //model.addAttribute("user");
        return "result";
    }

}
