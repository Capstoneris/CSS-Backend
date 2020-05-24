package de.hsh.capstoneris.data.legacy.bo;
import de.hsh.capstoneris.data.legacy.dao.GroupDAO;

public class GroupBO {

    GroupDAO groupDAO = new GroupDAO();

    public String getUsersInMyGroups() { return groupDAO.getUsersInMyGroup();}
}
