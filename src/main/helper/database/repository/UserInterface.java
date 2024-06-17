package main.helper.database.repository;

import main.entity.User;

import java.util.ArrayList;

public interface UserInterface {
    public void insertUser(String username, String password, int highScore, int coinCount);
    public boolean selectUser(String username, String password);
    public boolean updateCoinCount(int id, int coinCount);
    public boolean updateHighScore(int id, int highScore);
    public User selectUser(int id);
    public ArrayList<User> selectAllUsers();
}
