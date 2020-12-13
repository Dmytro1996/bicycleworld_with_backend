/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bicycleworld.service.impl;

import com.mycompany.bicycleworld.exception.NullEntityReferenceException;
import com.mycompany.bicycleworld.model.Comment;
import com.mycompany.bicycleworld.repository.CommentRepository;
import com.mycompany.bicycleworld.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dmytr
 */
@Service
public class CommentServiceImpl implements CommentService {
    
    @Autowired
    private CommentRepository commentRepo;
    
    public Comment create(Comment comment){
        if(comment!=null){
            return commentRepo.save(comment);
        }
        throw new NullEntityReferenceException("Comment cannot be null");
    }    
}
