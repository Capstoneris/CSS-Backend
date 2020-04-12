package de.hsh.capstoneris.rest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.util.Map;

@Path("auth")
public class AuthRESTResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuth(@Context HttpHeaders headers) {

        Map<String, Cookie> cookies = headers.getCookies();

        if (cookies.containsKey("css-jwt")) {
            Cookie cookie = cookies.get("css-jwt");
            String token = cookie.getValue();
            Algorithm algorithmHS = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithmHS).withIssuer("css-server").build();
            try {
                // Gets the token from the cookie and verifies it
                DecodedJWT jwt = verifier.verify(token);
                System.out.println("[AUTH] Authenticated user " + jwt.getClaim("user").asString());
                return Response.ok().build();
            } catch (JWTVerificationException e) {
                System.out.println("[AUTH] Authentication failed");
                return Response.status(401).build();
            }
        } else {
            System.out.println("[AUTH] Authentication failed");
            return Response.status(401).build();
        }


    }
}
