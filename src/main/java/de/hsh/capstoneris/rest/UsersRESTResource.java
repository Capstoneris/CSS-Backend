package de.hsh.capstoneris.rest;

import de.hsh.capstoneris.bo.GroupBO;
import de.hsh.capstoneris.bo.UsersBO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("users")
public class UsersRESTResource {

    UsersBO usersBO = new UsersBO();
    GroupBO groupBo = new GroupBO();

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers() {
        System.out.println("Getting request for Users!");
        return usersBO.getUser();
    }

    @Path("in-my-group")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsersInGroup(){
        System.out.println("Getting request for Users-in-my-Group!");
        return groupBo.getUsersInMyGroups();
    }



}
