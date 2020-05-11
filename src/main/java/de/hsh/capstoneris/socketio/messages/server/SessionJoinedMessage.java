package de.hsh.capstoneris.socketio.messages.server;

import de.hsh.capstoneris.rest.json.JsonChatMessage;
import de.hsh.capstoneris.rest.json.JsonInputfieldState;
import de.hsh.capstoneris.rest.json.JsonUser;

import java.util.ArrayList;

public class SessionJoinedMessage implements ServerMessage {

    public JsonUser host;
    // list of members
    public ArrayList<JsonChatMessage> chatHistory;
    public ArrayList<JsonInputfieldState> state;

    public JsonUser getHost() {
        return host;
    }

    public void setHost(JsonUser host) {
        this.host = host;
    }

    public ArrayList<JsonInputfieldState> getState() {
        return state;
    }

    public void setState(ArrayList<JsonInputfieldState> state) {
        this.state = state;
    }

    public SessionJoinedMessage(JsonUser host, ArrayList<JsonChatMessage> chatHistory, ArrayList<JsonInputfieldState> state) {
        this.host = host;
        this.chatHistory = chatHistory;
        this.state = state;
    }

    public ArrayList<JsonChatMessage> getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(ArrayList<JsonChatMessage> chatHistory) {
        this.chatHistory = chatHistory;
    }
    /*TODO session-joined
    Name des Hosts
    Aktuelle Eingabefelder-Zustände
    Aktuelle Mitgliederliste
    Aktuellen Chatverlauf
    → Client vom Mitglied zeigt Formularinhalte des Hosts, Mitgliederliste und Chat an, Sitzung läuft
    */
}
