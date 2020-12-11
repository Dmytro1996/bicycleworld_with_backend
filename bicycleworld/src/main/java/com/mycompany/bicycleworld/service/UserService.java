/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bicycleworld.service;

import com.mycompany.bicycleworld.model.User;
import java.util.List;

/**
 *
 * @author dmytr
 */
public interface UserService {
    User create(User user);
    List<User> getAll();
    User readById(long id);
}
