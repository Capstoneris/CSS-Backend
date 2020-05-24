package de.hsh.capstoneris.rest;

import de.hsh.capstoneris.rest.json.JsonLoginResponse;
import de.hsh.capstoneris.rest.json.JsonUser;
import de.hsh.capstoneris.util.Authenticator;
import de.hsh.capstoneris.util.ConsoleColors;
import de.hsh.capstoneris.util.Logger;
import de.hsh.capstoneris.util.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
                Logger.log(Service.REST, "Authenticated user: " + user, ConsoleColors.GREEN);
                return Response.ok().entity(new JsonLoginResponse(new JsonUser(0, user), token)).build();
            } else {
                Logger.log(Service.REST, "Authentication failed! Could not verify the provided token.", ConsoleColors.RED);
                return Response.status(401).build();
            }
        } else {
            Logger.log(Service.REST, "Authentication failed! Token not found in Header.", ConsoleColors.RED);
            return Response.status(401).build();
        }
    }
}
