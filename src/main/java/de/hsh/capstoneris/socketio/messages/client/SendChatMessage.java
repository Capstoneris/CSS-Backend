package de.hsh.capstoneris.socketio.messages.client;

import de.hsh.capstoneris.rest.json.JsonChatMessage;

public class SendChatMessage implements ClientMessage {
    public JsonChatMessage message;

    public SendChatMessage() {
    }

    public SendChatMessage(JsonChatMessage message) {
        this.message = message;
    }

    public JsonChatMessage getMessage() {
        return message;
    }

    public void setMessage(JsonChatMessage message) {
        this.message = message;
    }

    /*TODO send-chat-message
    Zeitstempel
    Nachricht
    → Server merkt sich Nachricht im Chatprotokoll
    → Server schickt chat-message an alle Mitglieder (auch den Host)*/
}
