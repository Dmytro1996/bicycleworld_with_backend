/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bicycleworld.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author dmytr
 */
@Entity
@Table(name="Articles")
public class Article {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
   @Column
   private String direction;
   private String text;   
   @OneToMany(mappedBy="article")
   private List<Comment> comments;

    public Article() {
    }

    public long getId() {
        return id;
    }

    public String getDirection() {
        return direction;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getText() {
        return text;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setText(String text) {
        this.text = text;
    }
   
}
