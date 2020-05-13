package de.hsh.capstoneris.data.factories;

import de.hsh.capstoneris.data.dto.GroupDTO;
import de.hsh.capstoneris.data.dto.UserDTO;
import de.hsh.capstoneris.data.sql.Connection;
import de.hsh.capstoneris.socketio.Group;

import java.sql.Array;
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

            while(resultSet.next()) {

                if(!(resultUser.getGroups().contains(resultSet.getLong("usergroup")))) {
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
    public UserDTO getUserByName(String name){
        String sql = "SELECT css.users.id as userid, css.users_in_groups.group as usergroup FROM css.users join css.users_in_groups on (css.users.id=css.users_in_groups.user) WHERE css.users.name like '"+name+"';";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        UserDTO resultUser = new UserDTO();


        try {
            preparedStatement = setupPreparedStatement(sql);
            resultSet = preparedStatement.executeQuery();

            resultUser.setId(resultSet.getLong("userid"));
            resultUser.setName(name);

            while(resultSet.next()) {

                if(!(resultUser.getGroups().contains(resultSet.getLong("usergroup")))) {
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

    public ArrayList<GroupDTO> getUsersInMyGroups(UserDTO user) {
        ArrayList<GroupDTO> allUsersFromMyGroups = new ArrayList<>();
        GroupFactory groupFactory = new GroupFactory();

        for (Long group : user.getGroups()){
            allUsersFromMyGroups.add(groupFactory.getGroupById(group));
        }

        return allUsersFromMyGroups;
    }

    public ArrayList<GroupDTO> getUsersInGroups(ArrayList<Long> groups) {
        ArrayList<GroupDTO> allUsersFromAllGroups = new ArrayList<>();
        GroupFactory groupFactory = new GroupFactory();

        for (Long group : groups){
            allUsersFromAllGroups.add(groupFactory.getGroupById(group));
        }

        return allUsersFromAllGroups;
    }


    /*
    public void deleteUser(UserDTO user){
        String sql = "delete FROM css.users_in_groups where css.users_in_groups.user="+user.getId()+";";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = setupPreparedStatement(sql);
            preparedStatement.executeUpdate();

            sql = "delete from css.groups where id="+user.getId()+";";
            preparedStatement = setupPreparedStatement(sql);
            preparedStatement.executeUpdate();


        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeConnections(resultSet, preparedStatement);
        }
        */

    }


