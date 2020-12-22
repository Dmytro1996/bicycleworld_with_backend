/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bicycleworld.model;

import java.util.Arrays;
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
public class ArticleTests {
   
    
    @Test
    public void createValidArticle(){
        Article article=new Article();
        article.setDirection("C:\\someDir");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Article>> violations = validator.validate(article);
        assertEquals(0, violations.size());
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidDirections")
    public void createInvalidArticle(String input, String errorValue){
        Article article=new Article();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Article>> violations = validator.validate(article);
        assertEquals(1, violations.size());
    }
    
    private static Stream<Arguments> provideInvalidDirections(){
        return Stream.of(Arguments.of("",""),
                Arguments.of("   ","   "),
                Arguments.of(null,null));
    }
    
    public void gettersSettersTest(){
        Article article=new Article();
        article.setId(1);
        article.setDirection("C:\\someDir");
        article.setText("someText");
        Comment comment=new Comment();
        comment.setId(1);
        comment.setComment("Some comment");
        article.setComments(Arrays.asList(comment));
        assertEquals(1,article.getId());
        assertEquals("C:\\someDir",article.getDirection());
        assertEquals("someText",article.getDirection());
        assertEquals(1,article.getComments().get(0).getId());
        assertEquals("someComment",article.getComments().get(0).getComment());
    }
}
