package org.dalpra.acme.jsf.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.dalpra.acme.jsf.entities.User;

@ApplicationScoped
public class DataInitializer {
    
    @Inject
    DataService dataService;
    
    public void execute(@Observes @Initialized(ApplicationScoped.class) Object event){
        if(dataService.getAllUsers().isEmpty()){
            User sally = dataService.createUser("Daniele Dal Pra'", "ddalpra", "daniele", "admin");
            User tom = dataService.createUser("Simona Mangiacotti", "smangiacotti", "simona", "user");
            
            dataService.createQuality("Admin", sally);
            dataService.createQuality("User", sally);
            dataService.createQuality("Printer", sally);
            dataService.createQuality("General", sally);
            
            dataService.createQuality("User", tom);
            dataService.createQuality("Printer", tom);
            dataService.createQuality("General", tom);
        }
    }
}