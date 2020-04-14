package de.hsh.capstoneris.socket.messages.client;

public class JoinSessionMessage implements ClientMessage {
<<<<<<< HEAD:src/main/java/de/hsh/capstoneris/socketMessages/client/JoinSessionMessage.java

    public String hostName;

    public JoinSessionMessage(String hostName) {
        this.hostName = hostName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

=======
>>>>>>> master:src/main/java/de/hsh/capstoneris/socket/messages/client/JoinSessionMessage.java
    /*TODO join-session
    Name des Hosts
    → Server prüft, ob dieser Host Session gestartet hat
    → Server prüft, ob Benutzer überhaupt zu der Sitzung dieses Hosts eingeladen wurde
    → Server prüft, ob Nutzer bereits Teil einer Sitzung
    → Server merkt sich Nutzerzustand: “teilgenommen an session von host X”
    → Server löscht entsprechendes “eingeladen zu session von host X” aus Nutzerzustand
    → Server schickt session-joined an Mitglied, das gerade gejoined ist
    → Server schickt invitation-list-update an Mitglied, das gerade gejoined ist
    → Server schickt member-list-update an alle Mitglieder (auch den Host)
    * */
}
