package de.hsh.capstoneris.socketio.messages.client;

public class InputFieldInteractionMessage implements ClientMessage {

    public String fieldID;
    public boolean changed;
    public String oldValue;
    public String newValue;
    public int cursorStart; // normal cursor pos & text selection start pos
    public int cursorEnd; //text selection end pos

    public InputFieldInteractionMessage(String fieldID, boolean changed, String oldValue, String newValue, int cursorStart, int cursorEnd) {
        this.fieldID = fieldID;
        this.changed = changed;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.cursorStart = cursorStart;
        this.cursorEnd = cursorEnd;
    }

    public String getFieldID() {
        return fieldID;
    }

    public void setFieldID(String fieldID) {
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


}
