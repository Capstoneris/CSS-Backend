package de.hsh.capstoneris.socketio.messages.client;

import de.hsh.capstoneris.rest.json.JsonGroup;
import de.hsh.capstoneris.rest.json.JsonInputfieldState;
import de.hsh.capstoneris.rest.json.JsonUser;

public class StartSessionMessage implements ClientMessage {
    public String message;
    public JsonGroup group;
    public JsonUser[] users;
    public long timestamp;
    public JsonInputfieldState[] inputfieldStates;

    // TODO List of Current input field states


    public StartSessionMessage(JsonGroup group, JsonUser[] users, String message, long timestamp, JsonInputfieldState[] inputfieldStates) {
        this.group = group;
        this.inputfieldStates = inputfieldStates;
        this.users = users;
        this.message = message;

    }

    public StartSessionMessage() {
    }

    public JsonInputfieldState[] getInputfieldStates() {
        return inputfieldStates;
    }

    public void setInputfieldStates(JsonInputfieldState[] inputfieldStates) {
        this.inputfieldStates = inputfieldStates;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public JsonGroup getGroup() {
        return group;
    }

    public void setGroup(JsonGroup group) {
        this.group = group;
    }

    public JsonUser[] getUsers() {
        return users;
    }


    public void setUsers(JsonUser[] users) {
        this.users = users;
    }
    /*
    TODO
    ** start-session
    Zeitstempel
    Nachricht
    Gruppe
    Aktuelle Eingabefelder-Zustände (Input-ID, Content, Cursor...)
    Namen der Mitglieder die eingeladen werden sollen (müssen online sein)
    → Server prüft, ob Host bereits Sitzung gestartet hat
    → Server prüft Mitgliederliste (existieren? Alle in der angegebenen Gruppe?)
    → Server merkt sich Formularinhalte
    → Server merkt sich Nutzerzustände: Host ist “host”, andere Mitglieder sind “eingeladen zu session von host X” (mehrere möglich!)
    → Server schickt session-started Nachricht an Host
    → Server schickt invitation-list-update Nachricht an andere Mitglieder (sofern online/verbunden und nicht bereits in einer Session.)
    */

}
