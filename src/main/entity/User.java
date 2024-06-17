package main.entity;

public class User {
    private String username, password;
    private int id, highScore, coinCount;

    public User(String username, String password, int id, int highScore, int coinCount) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.highScore = highScore;
        this.coinCount = coinCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public int getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
