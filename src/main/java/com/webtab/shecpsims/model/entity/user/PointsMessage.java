package com.webtab.shecpsims.model.entity.user;

public class PointsMessage {
    private String username;
    private int points;
    private int inviteSum;

    @Override
    public String toString() {
        return "PointsMessage{" +
                "username='" + username + '\'' +
                ", points=" + points +
                ", inviteSum=" + inviteSum +
                '}';
    }

    public int getInviteSum() {
        return inviteSum;
    }

    public void setInviteSum(int inviteSum) {
        this.inviteSum = inviteSum;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
