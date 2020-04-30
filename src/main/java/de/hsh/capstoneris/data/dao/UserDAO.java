package de.hsh.capstoneris.data.dao;

import de.hsh.capstoneris.data.sql.Connection;

import javax.json.bind.annotation.JsonbProperty;


public class UserDAO extends Connection {
    @JsonbProperty("id")
    private long userId;
    @JsonbProperty("name")
    private String userName;

    public UserDAO(long id, String name){
        this.userId = id;
        this.userName = name;
    }
    public UserDAO(){}

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Override
    public String toString(){
        return userId+" "+userName;
    }
}
