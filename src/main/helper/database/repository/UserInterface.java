package main.helper.database.repository;

import main.entity.LeaderboardScore;
import main.entity.User;

import java.util.ArrayList;

public interface UserInterface {
    public void insertUser(String username, int highScore, int coinCount, int speed, int magnet);
//    public boolean selectUser(String username);
    public boolean updateCoinCount(int id, int coinCount);
    public boolean updateHighScore(int id, int highScore);
    public User selectUser(String username);
    public ArrayList<User> selectAllUsers();
    public boolean updateSpeed(int id, int speed);
    public ArrayList<LeaderboardScore> getLeaderboard();
    public boolean updateMagnet(int id, int magnet);
    public int getMagnet(int id);
    public int getSpeed(int id);
}
