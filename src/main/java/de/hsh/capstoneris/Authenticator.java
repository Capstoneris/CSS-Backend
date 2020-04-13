package de.hsh.capstoneris;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import de.hsh.capstoneris.sql.Connection;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authenticator {
    public static final String SECRET = "secret";
    private static Algorithm algorithmHS = Algorithm.HMAC256(SECRET);

    public static String generateToken(String username) {

        Response response = null;

        try {
            Connection conn = new Connection();

            // Dummy login checks if user exists in the database
            PreparedStatement stmt = conn.setupPreparedStatement("SELECT name FROM css.users WHERE name = ?");
            stmt.setString(1, username);
            stmt.execute();
            ResultSet result = stmt.getResultSet();


            // If an entry with the username exists...
            if (result.next()) {
                // Create a token
                stmt.close();
                String token = JWT.create().withIssuer("css-server").withClaim("user", username).sign(algorithmHS);
                return token;
            } else {
                return "401";
            }
        } catch (SQLException | IOException | JWTCreationException e) {
            e.printStackTrace();
            return "500";
        }
    }

    public static String verifyToken(String token) {
        JWTVerifier verifier = JWT.require(algorithmHS).withIssuer("css-server").build();
        try {
            // Gets the token from the cookie and verifies it
            DecodedJWT jwt = verifier.verify(token);

            return jwt.getClaim("user").asString();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

}
