/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bicycleworld.service.impl;

import com.mycompany.bicycleworld.exception.NullEntityReferenceException;
import com.mycompany.bicycleworld.service.HomeService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author dmytr
 */
@ExtendWith(SpringExtension.class)
public class HomeServiceTests {
    
    @TestConfiguration
    static class HomeServiceTestsConfiguration{
        
        @Bean
        public HomeService homeService(){
            return new HomeServiceImpl();
        }
    }
    
    private HomeService homeService;
    private String article="";

    @Autowired
    public HomeServiceTests(HomeService homeService){
        this.homeService=homeService;
    }

    @BeforeEach
    public void setUp() throws IOException{
         BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(
                 new File("src\\main\\resources\\articles\\Article about choosing a bicycle.txt")),
                 StandardCharsets.UTF_8)); 
         String line=null;
            while((line=br.readLine())!=null){
                article+=line;
            }         
    }

    @Test
    public void getArticleTest(){
        assertEquals(article, homeService.getArticle(new File(
                "src\\main\\resources\\articles\\Article about choosing a bicycle.txt")));
    }

    @Test
    public void getArticleInvalidDirectionTest(){
        Throwable e=assertThrows(NullEntityReferenceException.class,()->homeService.getArticle(new File("//someDir")));
        assertEquals("Error while loading an article",e.getMessage());
    }    
}
