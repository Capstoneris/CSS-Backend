package de.hsh.capstoneris.socket.messages.client;

public class LoginMessage implements ClientMessage {
    public String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginMessage() {
    }

    public LoginMessage(String token) {
        this.token = token;
    }
}
