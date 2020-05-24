package de.hsh.capstoneris.data.legacy.bo;

import de.hsh.capstoneris.data.legacy.dao.UsersDAO;

public class UsersBO {

    private long id;
    private String name;


    UsersDAO usersDAO = new UsersDAO();
    public UsersBO() {}

    public String getUsers(){
        return usersDAO.getUsers();
    }

    public UsersBO(long id) {
        this.id = id;
        this.name = usersDAO.getUser(id);
    }
    public UsersBO(String name) {
        this.name = name;
        this.id = usersDAO.getUser(name);
    }

}
