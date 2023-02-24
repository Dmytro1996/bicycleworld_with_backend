/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bicycleworld;

import com.mycompany.bicycleworld.details.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author dmytr
 */
@Component
public class WebAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserDetailsService userDetailsService;    
    
    @Autowired
    private BCryptPasswordEncoder passEncoder; 
    Logger logger=LoggerFactory.getLogger(WebAuthenticationProvider.class);
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{        
        String username=authentication.getName();        
        String password=authentication.getCredentials().toString();
        logger.info(username);
        UserDetails userDetails=userDetailsService.loadUserByUsername(username);
        logger.info("User is null: "+(userDetails==null));
        logger.info("Password matches: "+passEncoder.matches(password,userDetails.getPassword()));
        if(userDetails!=null && passEncoder.matches(password,userDetails.getPassword())){  
            logger.info("UsernamePasswordAuthenticationToken will be returned.");
            return new UsernamePasswordAuthenticationToken(userDetails,
                    userDetails.getPassword(),userDetails.getAuthorities());
        }
        return null;
    }
    
    @Override
    public boolean supports(Class<?> authentication){
        return true;
    }
}
