package de.hsh.capstoneris.socketio.messages.server;

import de.hsh.capstoneris.socketio.User;

import java.util.List;

public class InputfieldChangedMessage implements ServerMessage {
    public String name;
    public String fieldID;
    public String currentValue;
    public List<User> selections;

    public InputfieldChangedMessage(String name, String fieldID, String currentValue, List<User> selections) {
        this.selections = selections;
        this.name = name;
        this.fieldID = fieldID;
        this.currentValue = currentValue;
    }

    public List<User> getSelections() {
        return selections;
    }

    public void setSelections(List<User> selections) {
        this.selections = selections;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFieldID() {
        return fieldID;
    }

    public void setFieldID(String fieldID) {
        this.fieldID = fieldID;
    }

    public String getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(String currentValue) {
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
