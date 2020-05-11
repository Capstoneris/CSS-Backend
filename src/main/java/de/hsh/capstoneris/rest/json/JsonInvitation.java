package de.hsh.capstoneris.rest.json;


public class JsonInvitation {
    public JsonUser host;
    public String message;
    public long timestamp;

    public JsonInvitation(JsonUser host, String message, long timestamp) {
        this.host = host;
        this.message = message;
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public JsonUser getHost() {
        return host;
    }

    public void setHost(JsonUser host) {
        this.host = host;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
