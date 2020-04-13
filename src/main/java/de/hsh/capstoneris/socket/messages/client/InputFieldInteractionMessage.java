package de.hsh.capstoneris.socket.messages.client;

public class InputFieldInteractionMessage implements ClientMessage {

    /*TODO inputfield-interaction
 * (Wird nur bei neu fokussierten/geänderten Feldern geschickt. Nicht für das “verlassen” des vorherigen Feldes)
Feld-ID
Geändert?
Wenn ja: Vorheriger Wert, neuer Wert
Informationen über Selektion (bei Textfeldern auch Cursorposition und Selektierter Bereich)
→ Server prüft, ob vorheriger Wert mit wirklichem Wert übereinstimmt
→ Falls akzeptiert: Server aktualisiert internen Zustand des Feldes
→ Falls akzeptiert: Server entfernt von allen anderen Feldern, die von diesem Mitglied fokussiert sind, den Fokus und schickt dann ggf. Ein inputfield-changed für diese Felder an alle Mitglieder
→ Server schickt IMMER ein inputfield-changed für das geänderte Feld an alle Mitglieder, auch wenn die Änderung nicht akzeptiert wurde, damit alle auf dem aktuellen Stand sind
*/
}
