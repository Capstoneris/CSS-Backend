package de.hsh.capstoneris.socket.messages.client;

public class LeaveSessionMessage implements ClientMessage {
      /*
    TODO leave-session
Wenn Host leaved:
→ Server setzt Nutzerzustände aller Mitglieder zurück
→ Server sendet session-leaved an alle Mitglieder
Wenn anderes Mitglied leaved:
→ Server entfernt von allen anderen Feldern, die von diesem Mitglied fokussiert sind, den Fokus und schickt dann ggf. Ein inputfield-changed für diese Felder an alle Mitglieder (außer dieses)
→ Server setzt Nutzerzustand zurück
→ Server sendet session-leaved an Mitglied
→ Server sendet member-list-update an alle Mitglieder (auch den Host)

       */
}
