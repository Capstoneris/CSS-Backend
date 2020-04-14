package de.hsh.capstoneris.rest;

import de.hsh.capstoneris.Authenticator;
import de.hsh.capstoneris.jsonObjects.LoginInformation;
import de.hsh.capstoneris.jsonObjects.LoginResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

@Path("auth/login")
public class LoginRESTResource {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginInformation loginInformation) {
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
            // Create a cookie with the token as value
            // WARNING: The cookie could retrieved by XSS-attacks
            NewCookie cookie = new NewCookie("css-jwt", token, null, null, null, NewCookie.DEFAULT_MAX_AGE, false, false);
            response = Response.ok().entity(new LoginResponse(0, loginInformation.username)).cookie(cookie).build();
            System.out.println("[LOGIN] Successful, setting cookie for user " + loginInformation.username);
        }

        return response;
    }
}