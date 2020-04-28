package de.hsh.capstoneris.rest;

import de.hsh.capstoneris.util.Authenticator;
import de.hsh.capstoneris.rest.json.JsonLoginResponse;
import de.hsh.capstoneris.rest.json.JsonUser;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

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
                return Response.ok().entity(new JsonLoginResponse(new JsonUser(0, user), token)).build();
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
