package org.acme;


import java.util.Arrays;
import java.util.HashSet;

import io.smallrye.jwt.build.Jwt;

public class TokenGenerator {

    public static String generate(String username, String roles) {
        String token =
           Jwt.upn(username) 
             .groups(new HashSet<>(Arrays.asList(roles.split(","))))
           .sign();
        return token;
    }
}
