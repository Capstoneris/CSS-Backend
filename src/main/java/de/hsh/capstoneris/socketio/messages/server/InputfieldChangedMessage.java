package de.hsh.capstoneris.socketio.messages.server;

import de.hsh.capstoneris.rest.json.JsonInputfieldState;
import de.hsh.capstoneris.rest.json.JsonUser;

public class InputfieldChangedMessage implements ServerMessage {
    public JsonUser user;
    public JsonInputfieldState state;

    public JsonUser getUser() {
        return user;
    }

    public void setUser(JsonUser user) {
        this.user = user;
    }

    public JsonInputfieldState getState() {
        return state;
    }

    public void setState(JsonInputfieldState state) {
        this.state = state;
    }

    public InputfieldChangedMessage(JsonUser user, JsonInputfieldState state) {
        this.user = user;
        this.state = state;
    }
}
