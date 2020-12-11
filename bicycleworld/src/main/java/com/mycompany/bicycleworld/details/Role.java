/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bicycleworld.details;

import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author dmytr
 */
public class Role implements GrantedAuthority {
    
    private String authority;

    public Role(String authority) {
        this.authority=authority;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
        
}
