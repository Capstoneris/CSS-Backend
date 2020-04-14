package de.hsh.capstoneris.jsonObjects;

public class Invitation {
    public int id;
    public LoginResponse host;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LoginResponse getHost() {
        return host;
    }

    public void setHost(LoginResponse host) {
        this.host = host;
    }

    public Invitation(int id, LoginResponse host) {
        this.id = id;
        this.host = host;
    }
}
