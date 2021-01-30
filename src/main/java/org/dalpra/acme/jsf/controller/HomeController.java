package org.dalpra.acme.jsf.controller;

import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.dalpra.acme.jsf.entities.Quality;
import org.dalpra.acme.jsf.entities.User;
import org.dalpra.acme.jsf.services.DataService;

@RequestScoped
@Named
public class HomeController {
    
    @Inject
    DataService dataService;
    
    @Inject
    SecurityContext securityContext;
    
    @Inject
    FacesContext facesContext;
    
    private Optional<User> currentUser;
    private List<Quality> currentQualities;
    
    @PostConstruct
    public void initialize(){
        String username = securityContext.getCallerPrincipal().getName();
        this.currentUser = dataService.getUser(username);
        this.currentUser.ifPresent(user -> {
            this.currentQualities  = dataService.getQualities(user);
        });
    }
    
    public User getCurrentUser(){
        return currentUser.orElse(null);
    }

    public List<Quality> getCurrentQualities() {
        return currentQualities;
    }
    
    public boolean isAllowedToSeeUsers(){
    	 return currentUser.get().getGroup().equals("admin");
    }
    
    public String logout() throws ServletException{
        ExternalContext ec = facesContext.getExternalContext();
        ((HttpServletRequest)ec.getRequest()).logout();
        return "/login.xhtml?faces-redirect=true";
    }
}