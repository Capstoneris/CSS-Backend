package de.hsh.capstoneris.socketMessages.client;

public class InputFieldInteractionMessage implements ClientMessage {

    public int fieldID;
    public boolean changed;
    public String oldValue;
    public String newValue;
    public int cursorStart; // normal cursor pos & text selection start pos
    public int cursorEnd; //text selection end pos

    public InputFieldInteractionMessage(int fieldID, boolean changed, String oldValue, String newValue, int cursorStart, int cursorEnd) {
        this.fieldID = fieldID;
        this.changed = changed;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.cursorStart = cursorStart;
        this.cursorEnd = cursorEnd;
    }

    public int getFieldID() {
        return fieldID;
    }

    public void setFieldID(int fieldID) {
        this.fieldID = fieldID;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public int getCursorStart() {
        return cursorStart;
    }

    public void setCursorStart(int cursorStart) {
        this.cursorStart = cursorStart;
    }

    public int getCursorEnd() {
        return cursorEnd;
    }

    public void setCursorEnd(int cursorEnd) {
        this.cursorEnd = cursorEnd;
    }



    /*TODO inputfield-interaction

      (Wird nur bei neu fokussierten/geänderten Feldern geschickt. Nicht für das “verlassen” des vorherigen Feldes)
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
