package de.hsh.capstoneris.bo;
import de.hsh.capstoneris.dao.GroupDAO;

public class GroupBO {

    GroupDAO groupDAO = new GroupDAO();

    public String getUsersInMyGroups() { return groupDAO.getUsersInMyGroup();}
}
