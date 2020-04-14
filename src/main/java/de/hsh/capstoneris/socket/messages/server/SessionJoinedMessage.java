package de.hsh.capstoneris.socket.messages.server;

<<<<<<< HEAD:src/main/java/de/hsh/capstoneris/socketMessages/server/SessionJoinedMessage.java
import java.util.ArrayList;
=======
public class SessionJoinedMessage implements ServerMessage {
>>>>>>> master:src/main/java/de/hsh/capstoneris/socket/messages/server/SessionJoinedMessage.java

public class SessionJoinedMessage implements ServerMessage {

    public String hostname;
    // list of members
    public ArrayList <String> chatHistory;

    public SessionJoinedMessage(String hostname, ArrayList<String> chatHistory) {
        this.hostname = hostname;
        this.chatHistory = chatHistory;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
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
