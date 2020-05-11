package de.hsh.capstoneris.socketio.messages.client;

public class SendChatMessage implements ClientMessage {
    public long timeStamp;
    public String messageContent;

    public SendChatMessage(long timeStamp, String messageContent) {
        this.timeStamp = timeStamp;
        this.messageContent = messageContent;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    /*TODO send-chat-message
    Zeitstempel
    Nachricht
    → Server merkt sich Nachricht im Chatprotokoll
    → Server schickt chat-message an alle Mitglieder (auch den Host)*/
}
