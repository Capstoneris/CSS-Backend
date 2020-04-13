package de.hsh.capstoneris;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import de.hsh.capstoneris.sql.Connection;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Main class.
 */
public class Main {

    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://0.0.0.0:8081/api/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     *
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in org.example.rest package
        final ResourceConfig rc = new ResourceConfig().register(CORSResponseFilter.class).packages("de.hsh.capstoneris.rest");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);

        return server;
    }

    public static void testDBConnection() {
        Connection conn = new Connection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.setupPreparedStatement("SELECT * FROM css.users");
            if (stmt.execute()) {
                System.out.println("Connected to Database localhost:5432/css-db");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final HttpServer server = startServer();
        System.out.println(String.format("CSS-Backend started!\nHit CTRL+C to stop it...", BASE_URI));

        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(8082);

        final SocketIOServer socketIOServer = new SocketIOServer(config);
        socketIOServer.start();

        testDBConnection();

        try {
            // Warning: Server will automatically shut down in 292.471.208 years!
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        server.shutdown();
        socketIOServer.stop();

    }


}

