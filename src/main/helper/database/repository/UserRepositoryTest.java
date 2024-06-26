package main.helper.database.repository;

import main.entity.LeaderboardScore;
import main.entity.User;
import main.helper.database.ConnectionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    void setUp() throws SQLException {
        userRepository = new UserRepository();
//        createTable();
        truncateTable();
    }

    private void truncateTable() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.createStatement().execute("TRUNCATE TABLE user");
        connection.close();
    }

    private void createTable() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String createTableSQL = "CREATE TABLE user (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "username VARCHAR(255) NOT NULL," +
                "high_score INT," +
                "coin_count INT)";
        connection.createStatement().execute(createTableSQL);
        connection.close();
    }

    @Test
    void insertUser() throws SQLException {
        userRepository.insertUser("testUser", 100, 50, 0, 0);

        Connection connection = ConnectionUtil.getDataSource().getConnection();
        var resultSet = connection.createStatement().executeQuery("SELECT * FROM user WHERE username = 'testUser'");
        assertTrue(resultSet.next());
        assertEquals("testUser", resultSet.getString("username"));
        assertEquals(100, resultSet.getInt("high_score"));
        assertEquals(50, resultSet.getInt("coin_count"));
        assertEquals(0, resultSet.getInt("magnet"));
        assertEquals(0, resultSet.getInt("speed"));
//        connection.close();
    }

    @Test
    void selectUser() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.createStatement().execute("INSERT INTO user (username, high_score, coin_count) VALUES ('testUser', 100, 50)");
        connection.close();

        User user = userRepository.selectUser("testUser");
        System.out.println(user);
        assertNotNull(user);
        assertEquals("testUser", user.getUsername());
        assertEquals(1, user.getId());
        assertEquals(100, user.getHighScore());
        assertEquals(50, user.getCoinCount());
    }

    @Test
    void updateCoinCount() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.createStatement().execute("INSERT INTO user (username, high_score, coin_count) VALUES ('testUser', 100, 50)");
        connection.close();

        boolean result = userRepository.updateCoinCount(1, 200);
        assertTrue(result);

        connection = ConnectionUtil.getDataSource().getConnection();
        var resultSet = connection.createStatement().executeQuery("SELECT * FROM user WHERE id = 1");
        assertTrue(resultSet.next());
        assertEquals(200, resultSet.getInt("coin_count"));
        connection.close();
    }

    @Test
    void updateHighScore() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.createStatement().execute("INSERT INTO user (username, high_score, coin_count) VALUES ('testUser', 100, 50)");
        connection.close();

        boolean result = userRepository.updateHighScore(1, 200);
        assertTrue(result);

        connection = ConnectionUtil.getDataSource().getConnection();
        var resultSet = connection.createStatement().executeQuery("SELECT * FROM user WHERE id = 1");
        assertTrue(resultSet.next());
        assertEquals(200, resultSet.getInt("high_score"));
        connection.close();
    }

    @Test
    void selectAllUsers() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.createStatement().execute("INSERT INTO user (username, high_score, coin_count) VALUES ('testUser', 100, 50)");
        connection.close();

        ArrayList<User> users = userRepository.selectAllUsers();
        assertNotNull(users);
        assertEquals(1, users.size());
        User user = users.get(0);
        assertEquals("testUser", user.getUsername());
        assertEquals(100, user.getHighScore());
        assertEquals(50, user.getCoinCount());
    }

    @Test
    void updateSpeed() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.createStatement().execute("INSERT INTO user (username, high_score, coin_count) VALUES ('testUser', 100, 50)");
        connection.close();

        boolean result = userRepository.updateSpeed(1, 200);
        assertTrue(result);
    }

    @Test
    void getLeaderboard() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.createStatement().execute("INSERT INTO user (username, coin_count) VALUES ('testUser1', 50)");
        connection.createStatement().execute("INSERT INTO user (username, coin_count) VALUES ('testUser2', 100)");
        connection.createStatement().execute("INSERT INTO user (username, coin_count) VALUES ('testUser3', 150)");
        connection.close();

        ArrayList<LeaderboardScore> leaderboard = userRepository.getLeaderboard();
        assertNotNull(leaderboard);
        assertEquals(3, leaderboard.size());
        assertEquals("testUser3", leaderboard.get(0).getUsername());
        assertEquals(150, leaderboard.get(0).getCoinCount());
        assertEquals("testUser2", leaderboard.get(1).getUsername());
        assertEquals(100, leaderboard.get(1).getCoinCount());
        assertEquals("testUser1", leaderboard.get(2).getUsername());
        assertEquals(50, leaderboard.get(2).getCoinCount());
    }

    @Test
    void updateMagnet() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.createStatement().execute("INSERT INTO user (username, high_score, coin_count) VALUES ('testUser', 100, 50)");
        connection.close();

        boolean result = userRepository.updateMagnet(1, 200);
        assertTrue(result);
    }

    @Test
    void getMagnet() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.createStatement().execute("INSERT INTO user (username, high_score, coin_count, magnet) VALUES ('testUser', 100, 50, 200)");
        connection.close();

        int magnet = userRepository.getMagnet(1);
        assertEquals(200, magnet);
    }

    @Test
    void getSpeed() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.createStatement().execute("INSERT INTO user (username, high_score, coin_count, speed) VALUES ('testUser', 100, 50, 200)");
        connection.close();

        int speed = userRepository.getSpeed(1);
        assertEquals(200, speed);
    }
}
