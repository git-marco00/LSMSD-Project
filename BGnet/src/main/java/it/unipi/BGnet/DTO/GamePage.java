package it.unipi.BGnet.DTO;

import it.unipi.BGnet.model.Post;
import it.unipi.BGnet.model.Rating;

import java.util.List;

public class GamePage {
    private String gameName;
    private String designer;
    private int yearPublished;
    private int minPlayers;
    private int maxPlayers;
    private List<String> categories;
    private String description;
    private String imageUrl;
    private float ratings;
    private List<Post> mostRecentPosts;
    private int followers;
    private boolean followed;
    private int rated;  // -1 if not rated
    private List<String> inCommonFollowers;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public float getRatings() {
        return ratings;
    }

    public void setRatings(float ratings) {
        this.ratings = ratings;
    }

    public List<Post> getMostRecentPosts() {
        return mostRecentPosts;
    }

    public void setMostRecentPosts(List<Post> mostRecentPosts) {
        this.mostRecentPosts = mostRecentPosts;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }

    public int getRated() {
        return rated;
    }

    public void setRated(int rated) {
        this.rated = rated;
    }

    public List<String> getInCommonFollowers() {
        return inCommonFollowers;
    }

    public void setInCommonFollowers(List<String> inCommonFollowers) {
        this.inCommonFollowers = inCommonFollowers;
    }
}
