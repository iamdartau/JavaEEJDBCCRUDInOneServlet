package dao;

import models.User;

import java.sql.*;
import java.util.ArrayList;

public class DbManager {

    String dbUrl = "jdbc:mysql://localhost:3306/JavaEEJDBCCRUD?useUnicode=true&serverTimezone=UTC";
    String dbUser = "root";
    String dbPassword = "";

    private Connection connection;

    public void connect() {
        try {
            //создаём объект каласса class для класса указанного в аргументе
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> userArrayList = new ArrayList<>();
        try {
            //PreparedStatement: предварительно компилирует запросы которые могут содержать входные параметры
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String fullName = resultSet.getString("fullName");

                userArrayList.add(new User(id, login, password, fullName));
            }


            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userArrayList;
    }


    public void addUserToDB(User user) {
        String sqlAddUser = "INSERT INTO JavaEEJDBCCRUD.users (id, login, password, fullName) VALUES (NULL, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlAddUser);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUserFromDB(Integer id) {
        String sqlDeleteDromDb = "DELETE FROM JavaEEJDBCCRUD.users WHERE id = (?) ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlDeleteDromDb);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(Integer id) {
        String sqlGetUserById = "select * from JavaEEJDBCCRUD.users where id = (?)";

        User user = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlGetUserById);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String fullName = resultSet.getString("fullName");

                user = new User(id, login, password, fullName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public void updateUserById(int id, String login, String password, String fullname) {

        String sql = "update JavaEEJDBCCRUD.users set login = ?, password = ?, fullName = ? where id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, fullname);
            statement.setInt(4, id);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

}
