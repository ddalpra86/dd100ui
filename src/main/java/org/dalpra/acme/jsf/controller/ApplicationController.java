package org.dalpra.acme.jsf.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.acme.security.jwt.TokenUtils;
import org.dalpra.acme.system.client.SystemClient;
import org.dalpra.acme.utils.SessionUtils;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.smallrye.jwt.build.Jwt;

@ApplicationScoped
@Named("applicationBean")
public class ApplicationController {
    @Inject
    @RestClient
    private SystemClient defaultRestClient;

    public String getJwt() {
    	String token = "";
		try {
			
	    	String claimsJson = Jwt.issuer("https://example.com/issuer") 
					.upn("jdoe@quarkus.io") 
					.groups(new HashSet<>(Arrays.asList("User", "Admin"))) 
					.claim(Claims.birthdate.name(), "1986-08-06") 
					.sign();
			HashMap<String, Long> timeClaims = new HashMap<>();
			long duration = Long.parseLong("8");
			long exp = TokenUtils.currentTimeInSecs() + duration;
			timeClaims.put(Claims.exp.name(), exp);
			token = TokenUtils.generateTokenString(claimsJson, timeClaims);
			
			System.out.println(token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //String jwtTokenString = SessionUtils.getJwtToken();
        String authHeader = "Bearer " + token;
        return authHeader;
    }
  
    public String getOs() {
        String authHeader = getJwt();
        String os;
        try {
            os = defaultRestClient.getOS(authHeader);
        } catch(Exception e) {
            return "You are not authorized to access this system property";
        }
        return os;
    }

    public String getUsername() {
        String authHeader = getJwt();
        return defaultRestClient.getUsername(authHeader);
    }

    public String getJwtRoles() {
        String authHeader = getJwt();
        return defaultRestClient.getJwtRoles(authHeader);
    }

}
