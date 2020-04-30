package de.hsh.capstoneris.rest.json;

public class SOCKETInvitation {
    public int id;
    public JsonLoginResponse host;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JsonLoginResponse getHost() {
        return host;
    }

    public void setHost(JsonLoginResponse host) {
        this.host = host;
    }

    public SOCKETInvitation(int id, JsonLoginResponse host) {
        this.id = id;
        this.host = host;
    }
}
