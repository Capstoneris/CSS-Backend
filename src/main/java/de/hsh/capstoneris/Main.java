package de.hsh.capstoneris;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import de.hsh.capstoneris.socket.Socket;
import de.hsh.capstoneris.socket.listeners.*;
import de.hsh.capstoneris.socket.messages.ChatMessage;
import de.hsh.capstoneris.socket.messages.client.*;
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
        final ResourceConfig rc = new ResourceConfig().register(CORSResponseFilter.class).register(JacksonJsonProvider.class).packages("de.hsh.capstoneris.rest");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);

        return server;
    }

    public static SocketIOServer createSocketIOServer() {
        Configuration config = new Configuration();
        config.setHostname("0.0.0.0");
        config.setPort(8082);

        final SocketIOServer socketIOServer = new SocketIOServer(config);

        socketIOServer.addConnectListener(new SocketConnectListener());
        socketIOServer.addDisconnectListener(new SocketDisconnectListener());
        socketIOServer.addEventListener(Socket.CLIENT_CHAT_MESSAGE, ChatMessage.class, new ChatMessageListener());
        socketIOServer.addEventListener(Socket.INPUTFIELD_INTERACTION, InputFieldInteractionMessage.class, new InputFieldInteractionMessageListener());
        socketIOServer.addEventListener(Socket.KICK_MEMBER, KickMemberMessage.class, new KickMemberMessageListener());
        socketIOServer.addEventListener(Socket.LEAVE_SESSION, LeaveSessionMessage.class, new LeaveSessionMessageListener());
        socketIOServer.addEventListener(Socket.LOGIN, LoginMessage.class, new LoginMessageListener());
        socketIOServer.addEventListener(Socket.START_SESSION, StartSessionMessage.class, new StartSessionMessageListener());
        return socketIOServer;
    }

    public static void testDBConnection() {
        Connection conn = new Connection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.setupPreparedStatement("SELECT * FROM css.users");
            if (stmt.execute()) {
                System.out.println("Connected to Database");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final HttpServer server = startServer();
        final SocketIOServer socketIOServer = createSocketIOServer();
        socketIOServer.start();

        System.out.println(String.format("CSS-Backend started!\nHit CTRL+C to stop it...", BASE_URI));


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

