/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bicycleworld.service.impl;

import com.mycompany.bicycleworld.details.UserDetailsImpl;
import com.mycompany.bicycleworld.model.User;
import com.mycompany.bicycleworld.repository.UserRepository;
import com.mycompany.bicycleworld.service.UserService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author dmytr
 */
@Service
public class UserServiceImpl implements UserService,UserDetailsService {
    
    @Autowired
    private UserRepository userRepo;
    Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
    
    public User readById(long id){
        return userRepo.findById(id).get();
    }
    
    public User create(User user){
        return userRepo.save(user);
    }
    
    public List<User> getAll(){
        return userRepo.findAll();
    }
    
    @Override
    public UserDetails loadUserByUsername(String userName){        
        Optional<User> user=userRepo.findAll().stream().filter(u->u.getEmail().equals(userName)).findAny();
        if(user.isPresent()){
            logger.info("User found");
            return new UserDetailsImpl(user.get());
        }
        logger.info("User not found");        
        throw new UsernameNotFoundException("User not found");
    }
}
