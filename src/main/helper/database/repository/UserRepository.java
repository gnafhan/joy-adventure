package main.helper.database.repository;

import main.entity.User;
import main.helper.database.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepository implements UserInterface{

    @Override
    public void insertUser(String username, String password, int highScore, int coinCount) {
        try {
            Connection connection = ConnectionUtil.getDataSource().getConnection();
            String query = "INSERT INTO user (username, password, high_score, coin_count) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, highScore);
            preparedStatement.setInt(4, coinCount);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean selectUser(String username, String password) {
        try {
            Connection connection = ConnectionUtil.getDataSource().getConnection();
            String query = "SELECT * FROM user WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeQuery();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateCoinCount(int id, int coinCount) {
        try {
            Connection connection = ConnectionUtil.getDataSource().getConnection();
            String query = "UPDATE user SET coin_count = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, coinCount);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateHighScore(int id, int highScore) {
        try {
            Connection connection = ConnectionUtil.getDataSource().getConnection();
            String query = "UPDATE user SET high_score = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, highScore);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User selectUser(int id) {
        try {
            Connection connection = ConnectionUtil.getDataSource().getConnection();
            String query = "SELECT * FROM user WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            connection.close();
            return resultSetToUser(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<User> selectAllUsers() {
        try {
            Connection connection = ConnectionUtil.getDataSource().getConnection();
            String query = "SELECT * FROM user";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            connection.close();
            return resultSetToUserList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<User> resultSetToUserList(ResultSet resultSet) {
        try {
            ArrayList<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(resultSetToUser(resultSet));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private User resultSetToUser(ResultSet resultSet) {
        try {
            return new User(resultSet.getString("username"), resultSet.getString("password"), resultSet.getInt("id"), resultSet.getInt("high_score"), resultSet.getInt("coin_count"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
