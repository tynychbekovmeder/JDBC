package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {


    public UserDaoJdbcImpl() {

    }

    @Override
    public void createUsersTable() throws SQLException {
        String SQL = "CREATE TABLE userTable(" +
                "  id serial primary key,"
                + "name VARCHAR(150) NOT NULL,"
                + "lastName varchar(150) not null,"
                + "age int not null);";
        try (Connection connection = Util.connection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(SQL);
            System.out.println("Successfully created");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() throws SQLException {

        String SQl = "drop table userTable";
        try (Connection connection = Util.connection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQl);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        String sql = "insert into userTable(name,lastName, age) values (?,?,?)";

        try (Connection connection = Util.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "delete from userTable where id=?";
        try (Connection connection = Util.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("successfully deleted by id");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User>list= new ArrayList<>();
        String sql = "select * from userTable";
        try (Connection connection = Util.connection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next()){
                User user = new User();
                user.setId((long) resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge((byte) resultSet.getInt("age"));
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "delete from userTable";
        try (Connection connection = Util.connection();
        Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("successfully deleted!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}