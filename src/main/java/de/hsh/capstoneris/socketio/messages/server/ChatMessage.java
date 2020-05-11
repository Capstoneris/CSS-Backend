package de.hsh.capstoneris.socketio.messages.server;

import de.hsh.capstoneris.rest.json.JsonChatMessage;

public class ChatMessage implements ServerMessage {
    public JsonChatMessage message;

    public JsonChatMessage getMessage() {
        return message;
    }

    public void setMessage(JsonChatMessage message) {
        this.message = message;
    }

    public ChatMessage(JsonChatMessage message) {
        this.message = message;
    }
}
