package de.hsh.capstoneris.rest.json;


public class JsonInvite {
    public JsonUser host;
    public String message;
    public long timeStamp;

    public JsonInvite(JsonUser host, String message, long timeStamp) {
        this.host = host;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
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
