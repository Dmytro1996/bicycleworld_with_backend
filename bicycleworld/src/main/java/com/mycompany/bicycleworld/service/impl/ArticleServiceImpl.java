/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bicycleworld.service.impl;

import com.mycompany.bicycleworld.model.Article;
import com.mycompany.bicycleworld.repository.ArticleRepository;
import com.mycompany.bicycleworld.service.ArticleService;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dmytr
 */
@Service
public class ArticleServiceImpl implements ArticleService {   
    
    private ArticleRepository articleRepo;   
    
    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepo) {
        this.articleRepo = articleRepo;
    }
        
    public Article readById(long id){
        try{
            return articleRepo.findById(id).get();
        } catch(NoSuchElementException e){
            throw new EntityNotFoundException("Article with id="+id+" not found");
        }
    }
    
    public List<Article> getAll(){        
        return articleRepo.findAll().size()>0?articleRepo.findAll():new ArrayList<Article>();
    }
}
