package de.hsh.capstoneris.socketio.messages.server;

import de.hsh.capstoneris.rest.json.JsonUser;

public class ChatMessage implements ServerMessage {
    public long timestamp;
    public JsonUser sentBy;
    public String messageContent;
}
