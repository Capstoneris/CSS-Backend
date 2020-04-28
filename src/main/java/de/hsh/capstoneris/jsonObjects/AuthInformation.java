package de.hsh.capstoneris.jsonObjects;

public class AuthInformation {
    public String token;

    public AuthInformation() {}

    public AuthInformation(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
