/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bicycleworld.controllers;

import com.mycompany.bicycleworld.model.User;
import com.mycompany.bicycleworld.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author dmytr
 */
@Controller
@RequestMapping("/users")
public class UserController {
    
    Logger logger=LoggerFactory.getLogger(UserController.class);
    private UserService userService;    
    private BCryptPasswordEncoder passEncoder;
    
    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder passEncoder) {
        this.userService = userService;
        this.passEncoder = passEncoder;
    }   
        
    @GetMapping("/create")
    public String create(Model model){
        logger.info("Inside GET create");
        model.addAttribute("user", new User());
        return "registration";
    }
    
    @PostMapping("/create")
    public String create(Model model,@Validated @ModelAttribute("user")User user, BindingResult result){
        logger.info("Inside POST create");
        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "registration";
        }        
        user.setPassword(passEncoder.encode(user.getPassword()));
        userService.create(user);
        return "redirect:/index";
    }
}
