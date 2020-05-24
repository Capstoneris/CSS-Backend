package de.hsh.capstoneris.data.dto;


import java.util.ArrayList;

public class GroupDTO {
    private long id;
    private String title;
    private ArrayList<UserDTO> users = new ArrayList<>();

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

    public ArrayList<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UserDTO> users) {
        this.users = users;
    }

    public void addUser(UserDTO user){
        this.users.add(user);
    }

    public void removeUser(UserDTO user){
        this.users.remove(user);
    }
}

