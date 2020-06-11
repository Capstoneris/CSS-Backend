package de.hsh.capstoneris.rest;


import de.hsh.capstoneris.data.dto.UserDTO;
import de.hsh.capstoneris.data.factories.UserFactory;
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
import java.util.ArrayList;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("users")
public class UsersRESTResource {
    UserFactory factory = new UserFactory();


    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "JSON" media type.
     *
     * @return String that will be returned as a JSON response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers() {
        ArrayList<UserDTO> allUsers = factory.getAllUsers();


        return allUsers.toString();
    }

    @Path("in-my-groups")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersInMyGroups(@Context HttpHeaders httpHeaders) {
        String token = Authenticator.extractToken(httpHeaders);
        if (token != null) {
            String user = Authenticator.verifyToken(token);

            if (user != null) {
                ArrayList<UserDTO> usersInMyGroup = factory.getUsersInMyGroups(factory.getUserByName(user));
                return Response.ok().entity(usersInMyGroup.toString()).build();
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
