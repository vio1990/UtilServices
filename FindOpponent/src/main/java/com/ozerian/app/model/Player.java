package com.ozerian.app.model;

/**
 * Class for representation of Player entity.
 */
public class Player {

    private String nickName;
    private double rating;
    private boolean isReady;

    public Player() {
    }

    public Player(String nickName, double rating, boolean isReady) {
        this.nickName = nickName;
        this.rating = rating;
        this.isReady = isReady;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;

        Player player = (Player) o;

        if (Double.compare(player.rating, rating) != 0) return false;
        if (isReady != player.isReady) return false;
        return nickName != null ? nickName.equals(player.nickName) : player.nickName == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = nickName != null ? nickName.hashCode() : 0;
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (isReady ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Player{" +
                "nickName='" + nickName + '\'' +
                ", rating=" + rating +
                ", isReady=" + isReady +
                '}';
    }
}
