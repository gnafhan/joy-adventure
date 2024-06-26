package main.entity;

import main.helper.Session;

public class User {
    private String username;
    private int id, highScore, coinCount, magnet, speed;

    public User(String username, int id, int highScore, int coinCount, int magnet, int speed) {
        this.username = username;
        this.id = id;
        this.highScore = highScore;
        this.coinCount = coinCount;
        this.magnet = magnet;
        this.speed = speed;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void updateDBCoinCount(int coinCount) {
        Session.getUserRepository().updateCoinCount(this.id, coinCount);
    }

    public void updateDBHighScore(int highScore) {
        Session.getUserRepository().updateHighScore(this.id, highScore);
    }

    public void updateDBSpeed(int speed) {
        Session.getUserRepository().updateSpeed(this.id, speed);
    }

    public void updateDBMagnet(int magnet) {
        Session.getUserRepository().updateMagnet(this.id, magnet);
    }

    public int getMagnet() {
        return magnet;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDBSpeed() {
        return Session.getUserRepository().selectUser(this.username).getSpeed();
    }

    public int getDBMagnet() {
        return Session.getUserRepository().selectUser(this.username).getMagnet();
    }



    public int getDBCoinCount() {
        return Session.getUserRepository().selectUser(this.username).getCoinCount();
    }
}
