package de.hsh.capstoneris.jsonObjects;

import javax.json.bind.annotation.JsonbProperty;

public class LoginResponse {
    @JsonbProperty("id")
    public int id;
    @JsonbProperty("username")
    public String username;

    public LoginResponse(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LoginResponse() {
    }
}
