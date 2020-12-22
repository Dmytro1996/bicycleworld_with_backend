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
public class CommentTests {
    
    @Test
    public void createValidCommentTest(){
        Comment comment=new Comment();
        comment.setComment("Some comment");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Comment>> violations = validator.validate(comment);
        assertEquals(0, violations.size());
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidComments")
    public void createInvalidCommentTest(String input, String errorValue){
        Comment comment=new Comment();
        comment.setComment(input);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Comment>> violations = validator.validate(comment);
        assertEquals(1, violations.size());
    }
    
    private static Stream<Arguments> provideInvalidComments(){
        return Stream.of(Arguments.of("",""),
                Arguments.of("   ","   "),
                Arguments.of(null,null));
    }
    
    @Test
    public void getterSetterTest(){
        Comment comment=new Comment();
        comment.setId(1);
        comment.setComment("Some comment");
        Article article=new Article();
        article.setId(1);
        article.setDirection("C://someDir");
        comment.setArticle(article);
        User user=new User();
        user.setId(1);
        user.setFullName("Dmytro");
        user.setEmail("dmytro@mail.com");
        user.setPassword("Aa12345");
        comment.setUser(user);
        assertEquals(1,comment.getId());
        assertEquals("Some comment", comment.getComment());
        assertEquals(article.getId(),comment.getArticle().getId());
        assertEquals(article.getDirection(),comment.getArticle().getDirection());
        assertEquals(user.getId(),comment.getUser().getId());
        assertEquals(user.getFullName(),comment.getUser().getFullName());
        assertEquals(user.getEmail(),comment.getUser().getEmail());
        assertEquals(user.getPassword(),comment.getUser().getPassword());
    }
}
