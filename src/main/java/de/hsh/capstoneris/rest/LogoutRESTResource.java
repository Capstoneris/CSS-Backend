package de.hsh.capstoneris.rest;

import de.hsh.capstoneris.Authenticator;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.util.Map;

@Path("auth/logout")
public class LogoutRESTResource {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(@Context HttpHeaders headers) {
        System.out.println("[LOGOUT] Logging out...");
        String token = Authenticator.extractToken(headers);

        if (token != null) {
            String user = Authenticator.verifyToken(token);
            if (user != null) {
                System.out.println("[LOGOUT] Logged out user " + user);
                return Response.ok().build();
            } else {
                System.out.println("[LOGOUT] Logout failed");
                return Response.status(401).build();
            }
        } else {
            System.out.println("[LOGOUT] Logout failed");
            return Response.status(401).build();
        }
    }
}
