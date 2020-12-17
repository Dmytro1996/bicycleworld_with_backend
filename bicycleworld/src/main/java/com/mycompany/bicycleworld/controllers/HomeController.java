/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bicycleworld.controllers;

import com.mycompany.bicycleworld.model.Article;
import com.mycompany.bicycleworld.model.Comment;
import com.mycompany.bicycleworld.service.ArticleService;
import com.mycompany.bicycleworld.service.HomeService;
import com.mycompany.bicycleworld.service.UserService;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author dmytr
 */
@Controller
public class HomeController {
    Logger logger=LoggerFactory.getLogger(HomeController.class);
    
    private HomeService homeService;    
    private UserService userService;    
    private ArticleService articleService;
    private static int currentPage=1;

    @Autowired    
    public HomeController(HomeService homeService, UserService userService, ArticleService articleService) {
        this.homeService = homeService;
        this.userService = userService;
        this.articleService = articleService;
    }
    
    
    
    @RequestMapping({"/","/index"})
    public String home(Model model){
        logger.info("Inside controller");      
        List<Article> articles=articleService.getAll().stream().limit(10).collect(Collectors.toList());              
        for(Article a:articles){            
            a.setText(homeService.getArticle(new File(a.getDirection())));
        }
        model.addAttribute("hasPreviousArticles",currentPage>1);
        model.addAttribute("hasNextArticles",currentPage*10<articleService.getAll().size());
        model.addAttribute("articles", articles);      
        model.addAttribute("newComment",new Comment());
        return "index";
    }

    @PostMapping("/page/{numOfPages}")
    public String changePage(Model model,@PathVariable("numOfPages")int numOfPages){
        currentPage+=numOfPages;
        logger.info("currentPage:"+currentPage);
        List<Article> articles=articleService.getAll().stream().skip((currentPage-1)*10)
                .limit(10).collect(Collectors.toList());              
        for(Article a:articles){            
            a.setText(homeService.getArticle(new File(a.getDirection())));
        }        
        model.addAttribute("hasPreviousArticles",currentPage>1);
        model.addAttribute("hasNextArticles",currentPage*10<articleService.getAll().size());
        model.addAttribute("articles", articles);      
        model.addAttribute("newComment",new Comment());
        return "index";
    }    
    
}
