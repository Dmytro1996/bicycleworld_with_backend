/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bicycleworld.controllers;

import com.mycompany.bicycleworld.details.UserDetailsImpl;
import com.mycompany.bicycleworld.model.Comment;
import com.mycompany.bicycleworld.service.ArticleService;
import com.mycompany.bicycleworld.service.CommentService;
import com.mycompany.bicycleworld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author dmytr
 */
@Controller
@RequestMapping("/comments")
public class CommentController {
    
   private CommentService commentService;   
   private UserService userService;   
   private ArticleService articleService;
    
    @Autowired
    public CommentController(CommentService commentService, UserService userService, ArticleService articleService) {
        this.commentService = commentService;
        this.userService = userService;
        this.articleService = articleService;
    }
   
   
   
   @PostMapping("/create/{articleId}")
   public String create(Model model,@PathVariable("articleId")long articleId,
           @Validated @ModelAttribute("newComment")Comment comment, 
           @AuthenticationPrincipal OAuth2User principal, BindingResult result){
       if(result.hasErrors()){         
           return "redirect:/index";
       }
       long id;
       comment.setArticle(articleService.readById(articleId));
       if(principal!=null){
           id=userService.getAll().stream().filter(u->
                   u.getEmail().equals(principal.getAttributes().get("email")
                           .toString())).map(u->u.getId()).findFirst().orElse(-1L);
       } else{
           id=((UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
       }
       comment.setUser(userService.readById(id));
       commentService.create(comment);
       return "redirect:/index";
   }
}
