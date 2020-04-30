package de.hsh.capstoneris.rest.json;

public class SOCKETAuthInformation {
    public String token;

    public SOCKETAuthInformation() {}

    public SOCKETAuthInformation(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
