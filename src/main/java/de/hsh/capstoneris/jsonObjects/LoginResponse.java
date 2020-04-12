package de.hsh.capstoneris.jsonObjects;

import javax.json.bind.annotation.JsonbProperty;

public class LoginResponse {
    @JsonbProperty("status")
    public int status;

    @JsonbProperty("token")
    public String token;

    public LoginResponse() {
    }

    public LoginResponse(int status, String token) {
        this.status = status;
        this.token = token;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
