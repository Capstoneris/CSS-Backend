package de.hsh.capstoneris.socketio.messages.client;

import de.hsh.capstoneris.rest.json.JsonUser;

public class KickMemberMessage implements ClientMessage {
    public JsonUser member;

    public KickMemberMessage() {
    }

    public JsonUser getMember() {
        return member;
    }

    public void setMember(JsonUser member) {
        this.member = member;
    }
}
