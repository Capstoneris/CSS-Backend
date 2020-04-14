package de.hsh.capstoneris.bo;

import de.hsh.capstoneris.dao.UsersDAO;

public class UsersBO {



    UsersDAO usersDAO = new UsersDAO();

    public String getUser(){
        return usersDAO.getUsers();
    }

}
