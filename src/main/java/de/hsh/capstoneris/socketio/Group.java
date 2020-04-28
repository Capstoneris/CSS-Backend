package de.hsh.capstoneris.socketio;

public class Group {

    private final int id;
    private final String name;

    public Group(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

}
