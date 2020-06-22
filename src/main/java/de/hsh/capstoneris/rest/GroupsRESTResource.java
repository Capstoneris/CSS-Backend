package de.hsh.capstoneris.rest;


import de.hsh.capstoneris.data.dto.GroupDTO;
import de.hsh.capstoneris.data.factories.GroupFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("groups")
public class GroupsRESTResource {
    GroupFactory factory = new GroupFactory();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getGroups() {
        ArrayList<GroupDTO> allGroups = factory.getAllGroups();

        return allGroups.toString();
    }
}
