package main.entity;

public class LeaderboardScore {
    private String username;
    private int coinCount;

    public LeaderboardScore(String username, int coinCount) {
        this.username = username;
        this.coinCount = coinCount;
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
}
