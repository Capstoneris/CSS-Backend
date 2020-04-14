package de.hsh.capstoneris.socket.messages.client;

public class JoinSessionMessage implements ClientMessage {
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