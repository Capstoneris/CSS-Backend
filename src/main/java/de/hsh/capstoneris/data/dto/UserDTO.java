package de.hsh.capstoneris.data.dto;


import java.util.ArrayList;

public class UserDTO {
    private long id = -1;
    private String name = null;
    //private String password=null;
    ArrayList<Long> groups = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
     */

    public ArrayList<Long> getGroups() {
        return groups;
    }

    public void addGroup(Long groupId) {
        this.groups.add(groupId);
    }

    public void removeGroup(Long groupId){
        this.groups.remove(groupId);
    }
}

