package de.hsh.capstoneris.rest.json;

public class JsonChatMessage {
    public long timestamp;
    public JsonUser sentBy;
    public String message;

    public JsonChatMessage() {
    }

    public JsonChatMessage(long timestamp, JsonUser sentBy, String message) {
        this.timestamp = timestamp;
        this.sentBy = sentBy;
        this.message = message;
    }

    public JsonUser getSentBy() {
        return sentBy;
    }

    public void setSentBy(JsonUser sentBy) {
        this.sentBy = sentBy;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
