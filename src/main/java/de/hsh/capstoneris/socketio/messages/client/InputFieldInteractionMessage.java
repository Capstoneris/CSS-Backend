package de.hsh.capstoneris.socketio.messages.client;

public class InputFieldInteractionMessage implements ClientMessage {

    public String fieldId;
    public boolean changed;
    public String oldValue;
    public String newValue;
    public int selectionStart; // normal cursor pos & text selection start pos
    public int selectionEnd; //text selection end pos

    public InputFieldInteractionMessage(String fieldId, boolean changed, String oldValue, String newValue, int selectionStart, int selectionEnd) {
        this.fieldId = fieldId;
        this.changed = changed;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.selectionStart = selectionStart;
        this.selectionEnd = selectionEnd;
    }

    public InputFieldInteractionMessage() {
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
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

    public int getSelectionStart() {
        return selectionStart;
    }

    public void setSelectionStart(int selectionStart) {
        this.selectionStart = selectionStart;
    }

    public int getSelectionEnd() {
        return selectionEnd;
    }

    public void setSelectionEnd(int selectionEnd) {
        this.selectionEnd = selectionEnd;
    }


}
