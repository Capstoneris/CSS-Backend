package de.hsh.capstoneris.rest;

import de.hsh.capstoneris.util.Authenticator;
import de.hsh.capstoneris.util.ConsoleColors;
import de.hsh.capstoneris.util.Logger;
import de.hsh.capstoneris.util.Service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("auth/logout")
public class LogoutRESTResource {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(@Context HttpHeaders headers) {
        Logger.log(Service.REST, "Logging out...");
        String token = Authenticator.extractToken(headers);

        if (token != null) {
            String user = Authenticator.verifyToken(token);
            if (user != null) {
                Logger.log(Service.REST, "Logged out user " + user, ConsoleColors.GREEN);
                return Response.ok().build();
            } else {
                Logger.log(Service.REST, "Logout failed, token was invalid!", ConsoleColors.RED);
                return Response.status(401).build();
            }
        } else {
            Logger.log(Service.REST, "Logout failed, token not found!", ConsoleColors.RED);
            return Response.status(401).build();
        }
    }
}
