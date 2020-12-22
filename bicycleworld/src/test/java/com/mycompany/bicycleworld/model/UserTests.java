/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bicycleworld.model;

import java.util.Set;
import java.util.stream.Stream;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author dmytr
 */
@SpringBootTest
public class UserTests {
    
    @Test
    public void createValidUserTest(){
        User user=new User();
        user.setFullName("Dmytro");
        user.setEmail("dmytro@mail.com");
        user.setPassword("Aa12345");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(0, violations.size());
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidFullNames")
    public void createUserWithInvalidFullNameTest(String input, String errorValue){
        User user=new User();
        user.setFullName(input);
        user.setEmail("dmytro@mail.com");
        user.setPassword("Aa12345");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }
    
    private static Stream<Arguments> provideInvalidFullNames(){
        return Stream.of(Arguments.of("dmytro","dmytro"),
                Arguments.of("   ","   "),
                Arguments.of(null,null),
                Arguments.of("12345","12345"));
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidEmails")
    public void createUserWithInvalidEmailTest(String input, String errorValue){
        User user=new User();
        user.setFullName("Dmytro");
        user.setEmail(input);
        user.setPassword("Aa12345");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }
    
    private static Stream<Arguments> provideInvalidEmails(){
        return Stream.of(Arguments.of("dmytro@mail","dmytro@mail"),
                Arguments.of("abcde","abcde"),
                Arguments.of(null,null),
                Arguments.of("dmytromail.com","dmytromail.com"));
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidPasswords")
    public void createUserWithInvalidPasswordTest(String input, String errorValue){
        User user=new User();
        user.setFullName("Dmytro");
        user.setEmail("dmytro@mail.com");
        user.setPassword(input);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }
    
    private static Stream<Arguments> provideInvalidPasswords(){
        return Stream.of(Arguments.of("",""),
                Arguments.of("   ","   "),
                Arguments.of(null,null));
    }
}
