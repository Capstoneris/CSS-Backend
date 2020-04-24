package de.hsh.capstoneris.socket.messages.client;

import de.hsh.capstoneris.socket.SocketUser;

import java.util.ArrayList;

public class StartSessionMessage implements ClientMessage {

    public double timeStamp;
    public String startMessageContent;
    public String group;
    // TODO List of Current input field states
    public ArrayList<SocketUser> users;

    public StartSessionMessage(double timeStamp, String startMessageContent, String group) {
        this.timeStamp = timeStamp;
        this.startMessageContent = startMessageContent;
        this.group = group;
    }

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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
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
