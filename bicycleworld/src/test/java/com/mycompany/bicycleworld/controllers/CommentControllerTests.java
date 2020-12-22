/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bicycleworld.controllers;

import com.mycompany.bicycleworld.model.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

/**
 *
 * @author dmytr
 */
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class CommentControllerTests {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext context;
    
    @BeforeEach
    public void setUp(){
        mockMvc=MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }
    
    //@WithMockUser("spring")
    @Test
    public void createTest() throws Exception{
        Comment comment=new Comment();
        comment.setComment("Good article");
        mockMvc.perform(MockMvcRequestBuilders.post("/comments/create/1")
                .param("comment", "Good article").with(csrf()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
    
    @WithMockUser("spring")
    @Test
    public void createWithInvalidInputTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/comments/create/1")
                .param("comment", "    ").with(csrf())).andExpect(
                        MockMvcResultMatchers.status().is3xxRedirection());
    }
}
