package de.hsh.capstoneris.rest;

import de.hsh.capstoneris.Authenticator;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.util.Map;

@Path("auth/logout")
public class LogoutRESTResource {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(@Context HttpHeaders headers) {
        System.out.println("[LOGOUT] Logging out...");
        Map<String, Cookie> cookies = headers.getCookies();

        if (cookies.containsKey("css-jwt")) {
            Cookie cookie = cookies.get("css-jwt");
            String token = cookie.getValue();
            String user = Authenticator.verifyToken(token);
            if (user != null) {
                // Sets the cookie to logged out
                // WARNING: The cookie could retrieved by XSS-attacks
                NewCookie logoutCookie = new NewCookie("css-jwt", null, null, null, null, 0, false, false);
                System.out.println("[LOGOUT] Logged out user " + user);
                return Response.ok().cookie(logoutCookie).build();
            } else {
                System.out.println("[LOGOUT] Logout failed");
                return Response.status(401).build();
            }
        } else {
            System.out.println("[LOGOUT] Logout failed");
            return Response.status(401).build();
        }
    }
}
