package de.hsh.capstoneris.socketio;

public class SocketMessageTypes {
    public static final String CONNECTION_ROOM = "lobby";
    public static final String LOGGED_IN_ROOM = "logged-in";

    // Server-Message-Types
    public static final String ERROR_MESSAGE = "error";
    public static final String HELLO = "hello";
    public static final String SESSION_STARTED = "session-started";
    public static final String INVITATION_LIST_UPDATE = "invitation-list-update";
    public static final String SESSION_JOINED = "session-joined";
    public static final String MEMBER_LIST_UPDATE = "member-list-update";
    public static final String SERVER_CHAT_MESSAGE = "chat-message";
    public static final String INPUTFIELD_CHANGED = "inputfield-changed";
    public static final String SESSION_LEFT = "session-left";

    // Client-Message-Types
    public static final String CLIENT_CHAT_MESSAGE = "send-chat-message";
    public static final String INPUTFIELD_INTERACTION = "inputfield-interaction";
    public static final String KICK_MEMBER = "kick-member";
    public static final String LEAVE_SESSION = "leave-session";
    public static final String LOGIN = "login";
    public static final String START_SESSION = "start-session";
    public static final String JOIN_SESSION = "join-session";

}
