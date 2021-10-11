package org.acme;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("")
public class AuthResource {


    @POST
    @PermitAll
    @Path("/register")
    public Response register(User user) {

        UserManager.addUser(user);
        return Response.ok(null).build();
    }
 
    @POST
    @RolesAllowed("user")
    @Path("/login")
    public String login(@Context SecurityContext securityContext){
        return UserManager.generateJWT(securityContext.getUserPrincipal().getName());  
    }

    @POST
    @RolesAllowed("admin")
    @Path("/admin")
    public Response admin(@Context SecurityContext securityContext){
        return Response.ok(null).build();
        // Example admin endpoint
    }
}