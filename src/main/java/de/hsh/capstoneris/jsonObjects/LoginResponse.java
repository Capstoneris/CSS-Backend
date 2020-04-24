package de.hsh.capstoneris.jsonObjects;

import javax.json.bind.annotation.JsonbProperty;

public class LoginResponse {
    @JsonbProperty("user")
    public User user;
    @JsonbProperty("token")
    public String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LoginResponse(User user, String token) {
        this.user = user;
        this.token = token;
    }


    public LoginResponse() {
    }
}
