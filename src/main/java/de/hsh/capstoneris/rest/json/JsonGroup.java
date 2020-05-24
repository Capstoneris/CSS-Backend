package de.hsh.capstoneris.rest.json;

import javax.json.bind.annotation.JsonbProperty;

public class JsonGroup {
    @JsonbProperty("id")
    public long id;
    @JsonbProperty("title")
    public String title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public JsonGroup(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public JsonGroup() {
    }
}
