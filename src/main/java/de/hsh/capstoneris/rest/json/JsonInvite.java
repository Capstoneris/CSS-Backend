package de.hsh.capstoneris.rest.json;


public class JsonInvite {
    public JsonUser host;
    public String message;

    public JsonInvite(JsonUser host, String message) {
        this.host = host;
        this.message = message;
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
