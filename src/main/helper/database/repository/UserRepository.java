package main.helper.database.repository;

import main.entity.LeaderboardScore;
import main.entity.User;
import main.helper.database.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepository implements UserInterface{

    @Override
    public void insertUser(String username, int highScore, int coinCount, int speed, int magnet) {
        try {
            Connection connection = ConnectionUtil.getDataSource().getConnection();
            String query = "INSERT INTO user (username, high_score, coin_count, speed, magnet) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, highScore);
            preparedStatement.setInt(3, coinCount);
            preparedStatement.setInt(4, speed);
            preparedStatement.setInt(5, magnet);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
    public User selectUser(String username) {
        try {
            Connection connection = ConnectionUtil.getDataSource().getConnection();
            String query = "SELECT * FROM user WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSetToUser(resultSet, connection);

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
            return resultSetToUserList(resultSet, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateSpeed(int id, int speed) {
        try {
            Connection connection = ConnectionUtil.getDataSource().getConnection();
            String query = "UPDATE user SET speed = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, speed);
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
    public ArrayList<LeaderboardScore> getLeaderboard() {
        try {
            Connection connection = ConnectionUtil.getDataSource().getConnection();
            String query = "SELECT username, coin_count FROM user ORDER BY coin_count DESC LIMIT 3";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSetToLeaderboardScoreList(resultSet, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateMagnet(int id, int magnet) {
        try {
            Connection connection = ConnectionUtil.getDataSource().getConnection();
            String query = "UPDATE user SET magnet = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, magnet);
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
    public int getMagnet(int id) {
        try{
            Connection connection = ConnectionUtil.getDataSource().getConnection();
            String query = "SELECT magnet FROM user WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int magnet = resultSet.getInt("magnet");
            connection.close();
            return magnet;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }



    @Override
    public int getSpeed(int id) {
        try{
            Connection connection = ConnectionUtil.getDataSource().getConnection();
            String query = "SELECT speed FROM user WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int speed = resultSet.getInt("speed");
            connection.close();
            return speed;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private ArrayList<LeaderboardScore> resultSetToLeaderboardScoreList(ResultSet resultSet, Connection connection) {
        try {
            ArrayList<LeaderboardScore> leaderboardScores = new ArrayList<>();
            while (resultSet.next()) {
                leaderboardScores.add(resultSetToLeaderboardScore(resultSet));
            }
            connection.close();
            return leaderboardScores;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private LeaderboardScore resultSetToLeaderboardScore(ResultSet resultSet) {
        try {
            return new LeaderboardScore(resultSet.getString("username"), resultSet.getInt("coin_count"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<User> resultSetToUserList(ResultSet resultSet, Connection connection) {
        try {
            ArrayList<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(resultSetToUser(resultSet));
            }
            connection.close();
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private User resultSetToUser(ResultSet resultSet, Connection connection) {
        try {
            User user= new User(resultSet.getString("username"), resultSet.getInt("id"), resultSet.getInt("high_score"), resultSet.getInt("coin_count"), resultSet.getInt("magnet"), resultSet.getInt("speed"));
            connection.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private User resultSetToUser(ResultSet resultSet) {
        try {
            User user= new User(resultSet.getString("username"), resultSet.getInt("id"), resultSet.getInt("high_score"), resultSet.getInt("coin_count"), resultSet.getInt("magnet"), resultSet.getInt("speed"));
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
