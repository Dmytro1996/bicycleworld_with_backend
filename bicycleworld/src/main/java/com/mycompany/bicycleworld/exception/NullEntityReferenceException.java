/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bicycleworld.exception;

/**
 *
 * @author dmytr
 */
public class NullEntityReferenceException extends RuntimeException {
    
    public NullEntityReferenceException(String message){
        super(message);
    }
}
