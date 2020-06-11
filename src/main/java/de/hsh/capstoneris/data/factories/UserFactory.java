package de.hsh.capstoneris.data.factories;

import de.hsh.capstoneris.data.dto.GroupDTO;
import de.hsh.capstoneris.data.dto.UserDTO;
import de.hsh.capstoneris.data.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserFactory extends Connection {

    public UserDTO getUser(long id) {
        String sql = "SELECT css.users.name as username, css.users_in_groups.group as usergroup FROM css.users join css.users_in_groups on (css.users.id=css.users_in_groups.user) WHERE css.users.id=" + id + ";";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        UserDTO resultUser = new UserDTO();


        try {
            preparedStatement = setupPreparedStatement(sql);
            resultSet = preparedStatement.executeQuery();

            resultUser.setId(id);
            resultUser.setName(resultSet.getString("username"));

            while (resultSet.next()) {

                if (!(resultUser.getGroups().contains(resultSet.getLong("usergroup")))) {
                    resultUser.getGroups().add(resultSet.getLong("usergroup"));
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeConnections(resultSet, preparedStatement);
        }
        return resultUser;
    }

    public UserDTO getUserByName(String name) {
        String sql = "SELECT css.users.id as userid, css.users_in_groups.group as usergroup FROM css.users join css.users_in_groups on (css.users.id=css.users_in_groups.user) WHERE css.users.name like '" + name + "';";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        UserDTO resultUser = new UserDTO();


        try {
            preparedStatement = setupPreparedStatement(sql);
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                resultUser.setId(resultSet.getLong("userid"));
                resultUser.setName(name);
                if (!(resultUser.getGroups().contains(resultSet.getLong("usergroup")))) {
                    resultUser.getGroups().add(resultSet.getLong("usergroup"));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnections(resultSet, preparedStatement);
        }
        return resultUser;
    }

    public ArrayList<UserDTO> getUsersInMyGroups(UserDTO user) {
        ArrayList<UserDTO> allUsersFromMyGroups = new ArrayList<>();
        GroupDTO dummyGroup;
        GroupFactory groupFactory = new GroupFactory();

        for (Long group : user.getGroups()) {
            dummyGroup = groupFactory.getGroupById(group);
            for (UserDTO userInThisGroup : dummyGroup.getUsers()) {
                if (!(allUsersFromMyGroups.contains(userInThisGroup))) {
                    allUsersFromMyGroups.add(userInThisGroup);
                }
            }
        }
        return allUsersFromMyGroups;
    }

    public ArrayList<GroupDTO> getUsersInGroups(ArrayList<Long> groups) {
        ArrayList<GroupDTO> allUsersFromAllMyGroups = new ArrayList<>();
        GroupFactory groupFactory = new GroupFactory();

        for (long group : groups) {
            allUsersFromAllMyGroups.add(groupFactory.getGroupById(group));
        }

        return allUsersFromAllMyGroups;
    }

    public ArrayList<UserDTO> getAllUsers() {
        String sql = "select id,name from css.users";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<UserDTO> allUsers = new ArrayList<>();
        ArrayList<Long> userIds = new ArrayList<>();


        try {
            preparedStatement = setupPreparedStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UserDTO nextUser = new UserDTO();
                nextUser.setId(resultSet.getLong("id"));
                nextUser.setName(resultSet.getString("name"));
                allUsers.add(nextUser);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnections(resultSet, preparedStatement);
        }
        return allUsers;
    }


}


