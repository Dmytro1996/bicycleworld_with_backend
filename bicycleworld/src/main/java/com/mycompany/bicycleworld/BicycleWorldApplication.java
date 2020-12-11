/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bicycleworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 *
 * @author dmytr
 */
@SpringBootApplication
public class BicycleWorldApplication extends SpringBootServletInitializer {
    
    public static void main(String[] args){
        SpringApplication.run(BicycleWorldApplication.class, args);
    }
}
