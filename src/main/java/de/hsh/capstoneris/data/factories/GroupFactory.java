package de.hsh.capstoneris.data.factories;

import de.hsh.capstoneris.data.dto.GroupDTO;
import de.hsh.capstoneris.data.dto.UserDTO;
import de.hsh.capstoneris.data.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GroupFactory extends Connection {

    public GroupDTO getGroupById(long id) {

        String sql = "select distinct css.users.id as userId, css.users.name as userName from css.groups join css.users_in_groups on (css.groups.id=css.users_in_groups.group) join css.users on (css.users_in_groups.user=css.users.id) where css.groups.id=" + id + ";";

        GroupDTO resultGroup = new GroupDTO();
        resultGroup.setId(id);
        resultGroup.setTitle(this.getTitleFromId(id));

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = setupPreparedStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UserDTO userDummy = new UserDTO();

                userDummy.setId(resultSet.getLong("userid"));
                userDummy.setName(resultSet.getString("username"));

                if (!resultGroup.getUsers().contains(userDummy)) {
                    resultGroup.getUsers().add(userDummy);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeConnections(resultSet, preparedStatement);
        }
        return resultGroup;

    }

    public GroupDTO getGroupByTitle(String title) {
        String sql = "select distinct css.users.id as userId, css.users.name as userName from css.groups join css.users_in_groups on (css.groups.id=css.users_in_groups.group) join css.users on (css.users_in_groups.user=css.users.id) where UPPER(css.groups.title) like Upper('" + title + "');";

        GroupDTO resultGroup = new GroupDTO();
        resultGroup.setTitle(title);
        resultGroup.setId(this.getIdFromTitle(title));

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = setupPreparedStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UserDTO userDummy = new UserDTO();

                userDummy.setId(resultSet.getLong("userid"));
                userDummy.setName(resultSet.getString("username"));

                if (!resultGroup.getUsers().contains(userDummy)) {
                    resultGroup.getUsers().add(userDummy);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeConnections(resultSet, preparedStatement);
        }
        return resultGroup;
    }

    private String getTitleFromId(long id) {
        String sql = "select title from css.groups where id=" + id + ";";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String title = null;

        try {
            preparedStatement = setupPreparedStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                title = resultSet.getString("title");
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeConnections(resultSet, preparedStatement);
        }
        return title;
    }

    private long getIdFromTitle(String title) {
        String sql = "select id from css.groups where UPPER(title) like Upper('" + title + "');";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        long resultId = -1;

        try {
            preparedStatement = setupPreparedStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                resultId = resultSet.getLong("id");
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeConnections(resultSet, preparedStatement);
        }
        return resultId;
    }

    public ArrayList<GroupDTO> getAllGroups() {
        String sql = "select id, title from css.groups";

        ArrayList<GroupDTO> resultGroup = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = setupPreparedStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                GroupDTO group = new GroupDTO();

                group.setId(resultSet.getLong("id"));
                group.setTitle(resultSet.getString("title"));
                resultGroup.add(group);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeConnections(resultSet, preparedStatement);
        }
        return resultGroup;
    }
}
