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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author dmytr
 */
@ExtendWith(SpringExtension.class)
public class CommentServiceTests {
    
    private CommentService commentService;
    
    private CommentRepository commentRepo;
    
    private Comment comment;
    
    @BeforeEach
    public void setUp(){
        commentRepo=Mockito.mock(CommentRepository.class);
        commentService=new CommentServiceImpl(commentRepo);
        comment=new Comment();
        comment.setId(1);
        comment.setComment("Some comment");
        Mockito.when(commentRepo.save(comment)).thenReturn(comment);        
    }
    
    @Test
    public void createTest(){
        Comment actualComment=commentService.create(comment);
        assertEquals(comment.getId(),actualComment.getId());
        assertEquals(comment.getComment(),actualComment.getComment());
    }
    
    @Test
    public void createNullTest(){
        Throwable e=assertThrows(NullEntityReferenceException.class,()->commentService.create(null));
        assertEquals("Comment cannot be null",e.getMessage());
    }
}
