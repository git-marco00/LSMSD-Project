package it.unipi.BGnet.model;

public class Rating {
    private String user;
    private int rate;

    public Rating(String user, int rate) {
        this.user = user;
        this.rate = rate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
