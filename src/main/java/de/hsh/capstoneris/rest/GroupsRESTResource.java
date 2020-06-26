package de.hsh.capstoneris.rest;


import de.hsh.capstoneris.data.dto.GroupDTO;
import de.hsh.capstoneris.data.factories.GroupFactory;
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
@Path("groups")
public class GroupsRESTResource {
    GroupFactory groupFactory = new GroupFactory();
    UserFactory userFactory = new UserFactory();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getGroups() {
        ArrayList<GroupDTO> allGroups = groupFactory.getAllGroups();

        return allGroups.toString();
    }

    @Path("my-groups")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMyGroups(@Context HttpHeaders httpHeaders) {
        String token = Authenticator.extractToken(httpHeaders);
        if (token != null) {
            String user = Authenticator.verifyToken(token);

            if (user != null) {
                ArrayList<Long> myGroupIds = userFactory.getUserByName(user).getGroups();
                ArrayList<GroupDTO> myGroups = new ArrayList<>();
                for (long groupId : myGroupIds) {
                    myGroups.add(groupFactory.getGroupById(groupId));
                }
                return Response.ok().entity(myGroups.toString()).build();
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
