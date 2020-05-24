package de.hsh.capstoneris.rest;


import de.hsh.capstoneris.data.dto.UserDTO;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("users")
public class UsersRESTResource {

    UserDTO userDTO = new UserDTO();

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "JSON" media type.
     *
     * @return String that will be returned as a JSON response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers() {

        return userDTO.getAllUsers().toString();

    }

    @Path("in-my-group")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsersInGroup() {
        return userDTO.getAllUsersInMyGroups().toString();

    }
}
