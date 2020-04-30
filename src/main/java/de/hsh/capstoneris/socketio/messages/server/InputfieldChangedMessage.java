package de.hsh.capstoneris.socketio.messages.server;

public class InputfieldChangedMessage implements ServerMessage {
    public String name;
    public int fieldID;
    public int currentValue;

    public InputfieldChangedMessage(String name, int fieldID, int currentValue) {
        this.name = name;
        this.fieldID = fieldID;
        this.currentValue = currentValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFieldID() {
        return fieldID;
    }

    public void setFieldID(int fieldID) {
        this.fieldID = fieldID;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }
    /*TODO inputfield-changed
    Name des Mitglieds das die Änderung ausgelöst hat
    Feld-ID
    Aktueller Wert
    Alle Selektionen dieses Feldes
    → Client überschreibt Wert des Feldes
    → Client zeigt Selektionen an
    */
}
