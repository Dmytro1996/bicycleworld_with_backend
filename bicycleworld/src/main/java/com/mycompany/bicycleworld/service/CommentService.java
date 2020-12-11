/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bicycleworld.service;

import com.mycompany.bicycleworld.model.Comment;
import java.util.List;

/**
 *
 * @author dmytr
 */
public interface CommentService {
    //List<Comment> getAll();
    Comment create(Comment comment);
}
