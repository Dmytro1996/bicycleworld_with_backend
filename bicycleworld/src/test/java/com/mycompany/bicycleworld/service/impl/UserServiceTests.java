/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bicycleworld.service.impl;

import com.mycompany.bicycleworld.exception.NullEntityReferenceException;
import com.mycompany.bicycleworld.model.User;
import com.mycompany.bicycleworld.repository.UserRepository;
import com.mycompany.bicycleworld.service.UserService;
import java.util.Arrays;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author dmytr
 */
@ExtendWith(SpringExtension.class)
public class UserServiceTests {
    
    private UserService userService;
    private UserRepository userRepo;
    private User user;
    
    @BeforeEach
    public void setUp(){
        userRepo=Mockito.mock(UserRepository.class);
        userService=new UserServiceImpl(userRepo);
        user=new User();
        user.setId(1);
        user.setFullName("Dmytro Danylov");
        user.setEmail("dmytro@gmail.com");
        user.setPassword("Aa12345");
        Mockito.when(userRepo.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(userRepo.findById(2L)).thenReturn(Optional.ofNullable(null));
        Mockito.when(userRepo.save(user)).thenReturn(user);
        Mockito.when(userRepo.findAll()).thenReturn(Arrays.asList(user));
    }
    
    @Test
    public void createTest(){
        assertEquals(user.getId(),userService.create(user).getId());
        assertEquals(user.getFullName(),userService.create(user).getFullName());
        assertEquals(user.getEmail(),userService.create(user).getEmail());
        assertEquals(user.getPassword(),userService.create(user).getPassword());
    }
    
    @Test
    public void createNullTest(){
        Throwable e=assertThrows(NullEntityReferenceException.class,()->userService.create(null));
        assertEquals("User cannot be null",e.getMessage());
    }
    
    @Test
    public void readByIdTest(){
        assertEquals(user.getId(),userService.readById(1).getId());
        assertEquals(user.getFullName(),userService.readById(1).getFullName());
        assertEquals(user.getEmail(),userService.readById(1).getEmail());
        assertEquals(user.getPassword(),userService.readById(1).getPassword());
    }
    
    @Test
    public void readByIdInvalidIdTest(){
        Throwable e=assertThrows(EntityNotFoundException.class,()->userService.readById(2L));
        assertEquals("User with id=2 not found",e.getMessage());
    }
    
    @Test
    public void getAllTest(){
        assertEquals(Arrays.asList(user).toString(),userService.getAll().toString());
    }
    
}
