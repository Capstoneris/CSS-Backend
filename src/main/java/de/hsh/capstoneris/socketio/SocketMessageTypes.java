package de.hsh.capstoneris.socketio;

public class SocketMessageTypes {
    public static String CONNECTION_ROOM = "lobby";
    public static String LOGGED_IN_ROOM = "logged-in";

    // Server-Message-Types
    public static String ERROR_MESSAGE = "error";
    public static String HELLO = "hello";
    public static String SESSION_STARTED = "session-started";
    public static String INVITATION_LIST_UPDATE = "invitation-list-update";
    public static String SESSION_JOINED = "session-joined";
    public static String MEMBER_LIST_UPDATE = "member-list-update";
    public static String SERVER_CHAT_MESSAGE = "chat-message";
    public static String INPUTFIELD_CHANGED = "inputfield-changed";
    public static String SESSION_LEFT = "session-left";

    // Client-Message-Types
    public static String CLIENT_CHAT_MESSAGE = "send-chat-message";
    public static String INPUTFIELD_INTERACTION = "inputfield-interaction";
    public static String KICK_MEMBER = "kick-member";
    public static String LEAVE_SESSION = "leave-session";
    public static String LOGIN = "login";
    public static String START_SESSION = "start-session";

}
