package de.hsh.capstoneris;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import de.hsh.capstoneris.data.sql.Connection;
import de.hsh.capstoneris.socketio.Manager;
import de.hsh.capstoneris.socketio.SocketMessageTypes;
import de.hsh.capstoneris.socketio.listeners.*;
import de.hsh.capstoneris.socketio.messages.client.*;
import de.hsh.capstoneris.util.CORSResponseFilter;
import de.hsh.capstoneris.util.ConsoleColors;
import de.hsh.capstoneris.util.Logger;
import de.hsh.capstoneris.util.Service;
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

        final Manager manager = new Manager();

        socketIOServer.addConnectListener(new SocketConnectListener());
        socketIOServer.addDisconnectListener(new SocketDisconnectListener(manager, socketIOServer));

        socketIOServer.addEventListener(SocketMessageTypes.CLIENT_CHAT_MESSAGE, SendChatMessage.class, new SendChatMessageListener(manager, socketIOServer));
        socketIOServer.addEventListener(SocketMessageTypes.INPUTFIELD_INTERACTION, InputFieldInteractionMessage.class, new InputFieldInteractionMessageListener(manager, socketIOServer));
        socketIOServer.addEventListener(SocketMessageTypes.KICK_MEMBER, KickMemberMessage.class, new KickMemberMessageListener(manager, socketIOServer));
        socketIOServer.addEventListener(SocketMessageTypes.LEAVE_SESSION, LeaveSessionMessage.class, new LeaveSessionMessageListener(manager, socketIOServer));
        socketIOServer.addEventListener(SocketMessageTypes.LOGIN, LoginMessage.class, new LoginMessageListener(manager, socketIOServer));
        socketIOServer.addEventListener(SocketMessageTypes.START_SESSION, StartSessionMessage.class, new StartSessionMessageListener(manager, socketIOServer));
        socketIOServer.addEventListener(SocketMessageTypes.JOIN_SESSION, JoinSessionMessage.class, new JoinSessionMessageListener(manager, socketIOServer));
        return socketIOServer;
    }

    public static void testDBConnection() {
        Connection conn = new Connection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.setupPreparedStatement("SELECT * FROM css.users");
            if (stmt.execute()) {
                Logger.log(Service.DB, "Database connection test successful!", ConsoleColors.GREEN);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            Logger.enabled = false;
        }
        final HttpServer server = startServer();
        Logger.log(Service.REST, "REST Server started!", ConsoleColors.GREEN);
        final SocketIOServer socketIOServer = createSocketIOServer();
        socketIOServer.start();
        Logger.log(Service.SOCKET, "SocketIO Server started!", ConsoleColors.GREEN);
        testDBConnection();
        Logger.log(Service.SYSTEM, "All components started! Hit CTRL+C to stop it...", ConsoleColors.GREEN);

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

