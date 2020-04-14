package de.hsh.capstoneris.socketMessages.client;

public class ChatMessage implements ClientMessage{

    public double timeStamp;
    public String messageContent;

    public ChatMessage(double timeStamp, String messageContent) {
        this.timeStamp = timeStamp;
        this.messageContent = messageContent;
    }

    public double getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(double timeStamp) {
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
