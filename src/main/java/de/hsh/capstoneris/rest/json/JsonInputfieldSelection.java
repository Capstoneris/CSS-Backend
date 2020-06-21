package de.hsh.capstoneris.rest.json;

import java.util.Objects;

public class JsonInputfieldSelection {
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

    public JsonInputfieldSelection() {
    }

    public JsonInputfieldSelection(JsonUser user, int start, int end) {
        this.user = user;
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonInputfieldSelection that = (JsonInputfieldSelection) o;
        return Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }
}
