package de.hsh.capstoneris.socketio.messages.server;

import de.hsh.capstoneris.rest.json.JsonUser;

import java.util.ArrayList;

public class SessionJoinedMessage implements ServerMessage {

    public JsonUser host;
    // list of members
    public ArrayList<String> chatHistory;

    public SessionJoinedMessage(JsonUser host, ArrayList<String> chatHistory) {
        this.host = host;
        this.chatHistory = chatHistory;
    }

    public JsonUser getHostname() {
        return host;
    }

    public void setHostname(JsonUser host) {
        this.host = host;
    }

    public ArrayList<String> getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(ArrayList<String> chatHistory) {
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
