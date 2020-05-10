package de.hsh.capstoneris.rest.json;

import de.hsh.capstoneris.data.dao.UsersDAO;
import de.hsh.capstoneris.socketio.User;

import javax.json.bind.annotation.JsonbProperty;

public class JsonUser {
    @JsonbProperty("id")
    public long id;
    @JsonbProperty("username")
    public String username;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public JsonUser(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public JsonUser(User user) {
        UsersDAO usersDAO = new UsersDAO();

        this.id = usersDAO.getUser(user.getUsername());
        this.username = user.getUsername();
    }

    public JsonUser() {
    }
}
