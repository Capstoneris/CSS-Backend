package de.hsh.capstoneris.data.bo;
import de.hsh.capstoneris.data.dao.GroupDAO;

public class GroupBO {

    GroupDAO groupDAO = new GroupDAO();

    public String getUsersInMyGroups() { return groupDAO.getUsersInMyGroup();}
}
