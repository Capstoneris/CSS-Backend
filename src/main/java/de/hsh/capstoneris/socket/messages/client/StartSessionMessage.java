package de.hsh.capstoneris.socket.messages.client;

public class StartSessionMessage implements ClientMessage {
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
