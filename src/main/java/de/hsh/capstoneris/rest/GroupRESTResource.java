package de.hsh.capstoneris.rest;

import de.hsh.capstoneris.bo.GroupBO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("users/group")
public class GroupRESTResource {

    GroupBO groupBo = new GroupBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsersInGroup(){
        System.out.println("Getting request for Users-in-my-Group!");
        return groupBo.getUsersInMyGroups();
    }
}
