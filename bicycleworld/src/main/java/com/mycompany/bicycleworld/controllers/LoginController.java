/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bicycleworld.controllers;

import com.mycompany.bicycleworld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author dmytr
 */
@Controller
public class LoginController {  
    
    
    @PostMapping("/login")
    public String login(Model model){        
        return "redirect:/index";
    }
    
    @PostMapping("/logout")
    public String logout(Model model){
        SecurityContextHolder.clearContext();
        return "redirect:/index";
    }
}
