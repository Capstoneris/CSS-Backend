package de.hsh.capstoneris.rest;

import de.hsh.capstoneris.util.Authenticator;
import de.hsh.capstoneris.rest.json.JsonLoginInformation;
import de.hsh.capstoneris.rest.json.JsonLoginResponse;
import de.hsh.capstoneris.rest.json.JsonUser;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("auth/login")
public class LoginRESTResource {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(JsonLoginInformation loginInformation) {
        System.out.println("[LOGIN] Logging in user with " + loginInformation);
        String token = Authenticator.generateToken(loginInformation.username);
        Response response = null;

        if (token.equals("401")) {
            System.out.println("[LOGIN] Failed, user " + loginInformation.username + " not found");
            response = Response.status(401).build();
        } else if (token.equals("500")) {
            System.out.println("[LOGIN] Failed, internal error");
            response = Response.status(500).build();
        } else {
            response = Response.ok().entity(new JsonLoginResponse(new JsonUser(0, loginInformation.username), token)).build();
            System.out.println("[LOGIN] Successful, sending token for user " + loginInformation.username);
        }

        return response;
    }
}