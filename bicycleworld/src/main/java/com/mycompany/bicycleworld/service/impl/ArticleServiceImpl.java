/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bicycleworld.service.impl;

import com.mycompany.bicycleworld.model.Article;
import com.mycompany.bicycleworld.repository.ArticleRepository;
import com.mycompany.bicycleworld.service.ArticleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dmytr
 */
@Service
public class ArticleServiceImpl implements ArticleService {
   
    @Autowired
    private ArticleRepository articleRepo;
    
    public Article readById(long id){
        return articleRepo.findById(id).get();
    }
    
    public List<Article> getAll(){
        return articleRepo.findAll();
    }
}
