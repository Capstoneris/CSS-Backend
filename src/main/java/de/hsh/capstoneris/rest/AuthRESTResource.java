package de.hsh.capstoneris.rest;

import de.hsh.capstoneris.Authenticator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.util.Map;

@Path("auth")
public class AuthRESTResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuth(@Context HttpHeaders headers) {

        Map<String, Cookie> cookies = headers.getCookies();

        if (cookies.containsKey("css-jwt")) {
            Cookie cookie = cookies.get("css-jwt");
            String token = cookie.getValue();
            String user = Authenticator.verifyToken(token);
            if (user != null) {
                System.out.println("[AUTH] Authenticated user " + user);
                return Response.ok().build();
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
