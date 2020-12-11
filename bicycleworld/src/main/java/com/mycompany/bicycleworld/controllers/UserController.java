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
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private BCryptPasswordEncoder passEncoder;
    
    @GetMapping("/create")
    public String create(Model model){
        logger.info("Inside GET create");
        model.addAttribute("user", new User());
        return "registration";
    }
    
    @PostMapping("/create")
    public String create(Model model,@ModelAttribute("user")User user){
        logger.info("Inside POST create");
        if(user!=null){
            user.setPassword(passEncoder.encode(user.getPassword()));
            userService.create(user);}
        return "redirect:/index";
    }
}
