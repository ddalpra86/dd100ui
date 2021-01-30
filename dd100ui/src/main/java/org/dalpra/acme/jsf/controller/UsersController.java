package org.dalpra.acme.jsf.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.dalpra.acme.jsf.entities.User;
import org.dalpra.acme.jsf.services.DataService;

@RequestScoped
@Named
public class UsersController {
    
    @Inject
    DataService dataService;
    
    private List<User> allUsers;
    
    @PostConstruct
    public void initialize(){
        this.allUsers = dataService.getAllUsers();
    }

    public List<User> getAllUsers() {
        return allUsers;
    }
}