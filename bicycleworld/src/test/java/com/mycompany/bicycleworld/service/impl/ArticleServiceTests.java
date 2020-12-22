/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bicycleworld.service.impl;

import com.mycompany.bicycleworld.model.Article;
import com.mycompany.bicycleworld.model.Comment;
import com.mycompany.bicycleworld.repository.ArticleRepository;
import com.mycompany.bicycleworld.service.ArticleService;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author dmytr
 */
@ExtendWith(SpringExtension.class)
public class ArticleServiceTests {   
    
    private ArticleService articleService;
    
    private ArticleRepository articleRepo;
    
    private Article article;
    
    @BeforeEach
    public void setUp(){
        articleRepo=Mockito.mock(ArticleRepository.class);
        articleService=new ArticleServiceImpl(articleRepo);
        article=new Article();
        article.setId(1);
        article.setDirection("C:\\someDir");       
        Mockito.when(articleRepo.findById(1L)).thenReturn(Optional.of(article));
        Mockito.when(articleRepo.findById(2L)).thenThrow(new NoSuchElementException());
        Mockito.when(articleRepo.findAll()).thenReturn(Arrays.asList(article));
    }
    
    @Test
    public void readByIdTest(){
        assertEquals(articleService.readById(1).getId(),article.getId());
        assertEquals(articleService.readById(1).getDirection(),article.getDirection());        
    }
    
    @Test
    public void readByIdInvalidIdTest(){
        Throwable e=assertThrows(EntityNotFoundException.class,()->articleService.readById(2L));
        assertEquals("Article with id=2 not found",e.getMessage());
    }
    
    @Test
    public void getAllTest(){
        assertEquals(Arrays.asList(article).toString(),articleService.getAll().toString());
    }
    
}
