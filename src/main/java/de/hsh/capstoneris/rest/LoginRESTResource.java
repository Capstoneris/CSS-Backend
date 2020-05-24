package de.hsh.capstoneris.rest;

import de.hsh.capstoneris.rest.json.JsonLoginInformation;
import de.hsh.capstoneris.rest.json.JsonLoginResponse;
import de.hsh.capstoneris.rest.json.JsonUser;
import de.hsh.capstoneris.util.Authenticator;
import de.hsh.capstoneris.util.ConsoleColors;
import de.hsh.capstoneris.util.Logger;
import de.hsh.capstoneris.util.Service;

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
        String token = Authenticator.generateToken(loginInformation.username);
        Response response = null;

        if (token.equals("401")) {
            Logger.log(Service.REST, "Login failed, user " + loginInformation.username + " not found!", ConsoleColors.RED);
            response = Response.status(401).build();
        } else if (token.equals("500")) {
            Logger.log(Service.REST, "Login failed, internal error", ConsoleColors.RED);
            response = Response.status(500).build();
        } else {
            response = Response.ok().entity(new JsonLoginResponse(new JsonUser(0, loginInformation.username), token)).build();
            Logger.log(Service.REST, "Login successful, sending token to user " + loginInformation.username, ConsoleColors.GREEN);
        }

        return response;
    }
}