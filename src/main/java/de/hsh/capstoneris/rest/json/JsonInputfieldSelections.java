package de.hsh.capstoneris.rest.json;

public class JsonInputfieldSelections {
    public JsonUser user;
    public int start;
    public int end;

    public JsonUser getUser() {
        return user;
    }

    public void setUser(JsonUser user) {
        this.user = user;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public JsonInputfieldSelections() {
    }

    public JsonInputfieldSelections(JsonUser user, int start, int end) {
        this.user = user;
        this.start = start;
        this.end = end;
    }
}
