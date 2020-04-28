package de.hsh.capstoneris.rest.json;

import javax.json.bind.annotation.JsonbProperty;

public class JsonLoginInformation {
    @JsonbProperty("username")
    public String username;
    @JsonbProperty("password")
    public String password;

    public JsonLoginInformation(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public JsonLoginInformation() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginInformation{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
