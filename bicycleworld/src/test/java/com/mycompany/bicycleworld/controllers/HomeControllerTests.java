/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bicycleworld.controllers;

import com.mycompany.bicycleworld.model.Article;
import com.mycompany.bicycleworld.service.ArticleService;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

/**
 *
 * @author dmytr
 */
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class HomeControllerTests {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ArticleService articleService;
    
    @Autowired
    private WebApplicationContext context;
    
    @BeforeEach
    public void setUp(){       
        mockMvc=MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }
    
    //@WithMockUser("spring")
    @Test
    public void homeTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("newComment"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("hasPreviousArticles"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("hasNextArticles"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("articles"));
        /*mockMvc.perform(MockMvcRequestBuilders.post("/index").with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("newComment"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("hasPreviousArticles"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("hasNextArticles"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("articles"));*/
        List<Article> actualArticles=(List<Article>)mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andReturn().getModelAndView().getModel().get("articles");
        assertEquals(articleService.getAll().stream().map(a->a.getDirection()).collect(Collectors.joining(", ")),
                actualArticles.stream().map(a->a.getDirection()).collect(Collectors.joining(", ")));
    }
    
    //@WithMockUser("spring")
    @Test
    public void changePageTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/page/1").with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("newComment"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("hasPreviousArticles"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("hasNextArticles"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("articles"));
    }
}
