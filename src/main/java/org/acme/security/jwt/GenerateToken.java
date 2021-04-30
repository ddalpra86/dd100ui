package org.acme.security.jwt;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.jwt.build.Jwt;

/**
 * A simple utility class to generate and print a JWT token string to stdout. Can be run with:
 * mvn exec:java -Dexec.mainClass=org.acme.security.jwt.GenerateToken -Dexec.classpathScope=test
 */
public class GenerateToken {
    /**
     *
     * @param args - [0]: optional name of classpath resource for json document of claims to add; defaults to "/JwtClaims.json"
     *             [1]: optional time in seconds for expiration of generated token; defaults to 300
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
    	
    	String claimsJson = Jwt.issuer("https://example.com/issuer") 
                  .upn("jdoe@quarkus.io") 
                  .groups(new HashSet<>(Arrays.asList("User", "Admin"))) 
                  .claim(Claims.birthdate.name(), "2001-07-13") 
                .sign();
        HashMap<String, Long> timeClaims = new HashMap<>();
        
           
        if (args.length > 1) {
            long duration = Long.parseLong(args[1]);
            long exp = TokenUtils.currentTimeInSecs() + duration;
            timeClaims.put(Claims.exp.name(), exp);
        }
        String token = TokenUtils.generateTokenString(claimsJson, timeClaims);
        System.out.println(token);
    }
}