package de.hsh.capstoneris.rest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import de.hsh.capstoneris.jsonObjects.LoginInformation;
import de.hsh.capstoneris.sql.Connection;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Path("auth/login")
public class LoginRESTResource {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginInformation loginInformation) {
        System.out.println("[LOGIN] Logging in user with " + loginInformation);
        Algorithm algorithmHS = Algorithm.HMAC256("secret");
        Response response = null;

        try {
            Connection conn = new Connection();

            // Dummy login checks if user exists in the database
            PreparedStatement stmt = conn.setupPreparedStatement("SELECT name FROM css.users WHERE name = ?");
            stmt.setString(1, loginInformation.username);
            stmt.execute();
            ResultSet result = stmt.getResultSet();

            // If an entry with the username exists...
            if (result.next()) {
                // Create a token
                String token = JWT.create().withIssuer("css-server").withClaim("user", loginInformation.username).sign(algorithmHS);

                // Create a cookie with the token as value
                NewCookie cookie = new NewCookie("css-jwt", token, null, null, null, NewCookie.DEFAULT_MAX_AGE, false, true);

                response = Response.ok().cookie(cookie).build();
                System.out.println("[LOGIN] Successful, setting cookie for user " + loginInformation.username);
            } else {
                System.out.println("[LOGIN] Failed, user " + loginInformation.username + " not found");
                response = Response.status(401).build();
            }
        } catch (SQLException | IOException | JWTCreationException e) {
            e.printStackTrace();
            System.out.println("[LOGIN] Failed, internal error");
            response = Response.status(500).build();
        }
        return response;
    }
}