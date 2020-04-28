package de.hsh.capstoneris.socketio.messages.client;

import de.hsh.capstoneris.rest.json.JsonUser;

public class StartSessionMessage implements ClientMessage {

    // public double timeStamp;
    // public String startMessageContent;
    public String groupName;
    // TODO List of Current states
    public JsonUser[] users;
    // TODO List of Current input field states


    public StartSessionMessage(String groupName, JsonUser[] users) {
        this.groupName = groupName;
        this.users = users;

    }

    public StartSessionMessage() {
    }

    /*
    public double getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(double timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getStartMessageContent() {
        return startMessageContent;
    }

    public void setStartMessageContent(String startMessageContent) {
        this.startMessageContent = startMessageContent;
    }
     */

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
