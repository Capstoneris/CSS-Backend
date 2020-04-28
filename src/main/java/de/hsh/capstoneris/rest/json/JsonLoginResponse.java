package de.hsh.capstoneris.rest.json;

import javax.json.bind.annotation.JsonbProperty;

public class JsonLoginResponse {
    @JsonbProperty("user")
    public JsonUser user;
    @JsonbProperty("token")
    public String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JsonUser getUser() {
        return user;
    }

    public void setUser(JsonUser user) {
        this.user = user;
    }

    public JsonLoginResponse(JsonUser user, String token) {
        this.user = user;
        this.token = token;
    }


    public JsonLoginResponse() {
    }
}
