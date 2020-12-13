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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    
   @Autowired
   private CommentService commentService;
   @Autowired
   private UserService userService;
   @Autowired
   private ArticleService articleService;
   
   @PostMapping("/create/{articleId}")
   public String create(Model model,@PathVariable("articleId")long articleId,
           @ModelAttribute("newComment")Comment comment, BindingResult result){
       if(result.hasErrors()){           
           model.addAttribute("emptyCommentError", "Comment cannot be empty");
           return "index";
       }
       comment.setArticle(articleService.readById(articleId));
       UserDetailsImpl userDetails=(UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       comment.setUser(userService.readById(userDetails.getId()));
       commentService.create(comment);
       return "redirect:/index";
   }
}
