package de.hsh.capstoneris.data.dto;

import java.util.ArrayList;

public class SessionDTO {
    private long id = -1;
    private String host;
    private String location;
    private String group;
    private long time;
    private ArrayList<MessageDTO> messages = new ArrayList<>();

    public long getId() {
        return id;
    }

    public ArrayList<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<MessageDTO> messages) {
        this.messages = messages;
    }

    public void addMessages(MessageDTO message){
        this.messages.add(message);
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
