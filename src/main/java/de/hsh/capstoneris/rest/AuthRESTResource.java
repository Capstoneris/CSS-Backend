package de.hsh.capstoneris.rest;

import de.hsh.capstoneris.Authenticator;
import de.hsh.capstoneris.jsonObjects.AuthInformation;
import de.hsh.capstoneris.jsonObjects.LoginResponse;
import de.hsh.capstoneris.jsonObjects.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.Map;

@Path("auth")
public class AuthRESTResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuth(@Context HttpHeaders httpHeaders) {
        String token = null;

        token = Authenticator.extractToken(httpHeaders);

        if (token != null) {
            String user = Authenticator.verifyToken(token);

            if (user != null) {
                System.out.println("[AUTH] Authenticated user " + user);
                return Response.ok().entity(new LoginResponse(new User(0, user), token)).build();
            } else {
                System.out.println("[AUTH] Authentication failed");
                return Response.status(401).build();
            }
        } else {
            System.out.println("[AUTH] Authentication failed");
            return Response.status(401).build();
        }


    }
}
