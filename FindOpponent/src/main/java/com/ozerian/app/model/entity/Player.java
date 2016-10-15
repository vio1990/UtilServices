package com.ozerian.app.model.entity;

/**
 * This class is for representation of Player entity and
 * implements Comparable interface for using in TreeSet.
 */
public class Player implements Comparable<Player>{

    private String nickName;
    private double rating;
    private boolean isReady;

    /**
     * Constructor with fields values.
     *
     * @param nickName String player's nick name.
     * @param rating double player's rating.
     * @param isReady boolean is player ready for game.
     */
    public Player(String nickName, double rating, boolean isReady) {
        this.nickName = nickName;
        this.rating = rating;
        this.isReady = isReady;
    }

    /**
     * Get player's nick name.
     *
     * @return String player's nick name.
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Set player's nick name.
     *
     * @param nickName String player's nick name.
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * Get player's rating.
     *
     * @return double player's rating.
     */
    public double getRating() {
        return rating;
    }

    /**
     * Set player's rating.
     *
     * @param rating double player's rating.
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Get player's status.
     *
     * @return boolean player's status.
     */
    public boolean isReady() {
        return isReady;
    }

    /**
     * Set player's status.
     *
     * @param ready boolean player's status.
     */
    public void setReady(boolean ready) {
        isReady = ready;
    }

    /**
     * Override equals method.
     *
     * @param o Object for comparison.
     * @return boolean if objects are equals.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;

        if (Double.compare(player.rating, rating) != 0) return false;
        if (isReady != player.isReady) return false;
        return nickName != null ? nickName.equals(player.nickName) : player.nickName == null;

    }

    /**
     * Override hashcode method.
     *
     * @return int hashcode value for this player.
     */
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

    /**
     * Override toString method.
     *
     * @return String player's representation.
     */
    @Override
    public String toString() {
        return "Player{" +
                "nickName='" + nickName + '\'' +
                ", rating=" + rating +
                ", isReady=" + isReady +
                '}';
    }

    /**
     * Override compareTo method for using in NavigableSet.
     *
     * @param anotherPlayer Player for comparison.
     * @return int value after comparison.
     */
    @Override
    public int compareTo(Player anotherPlayer) {
        if (this.getRating() > anotherPlayer.getRating()) {
            return 1;
        } else if (this.getRating() < anotherPlayer.getRating()) {
            return -1;
        } else {
            return 0;
        }
    }
}
