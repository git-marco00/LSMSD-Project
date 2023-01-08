package it.unipi.BGnet.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Game {
    private String id;
    private String name;
    private String designer;
    private int yearPublished;
    private int minPlayers;
    private int maxPlayers;
    private String playingTime;
    private String minPlayTime;
    private String maxPlayTime;
    private List<String> categories;
    private String description;
    private String imageUrl;
    private List<Rating> ratings;
    private List<Post> mostRecentPosts;
    private List<String> followers;


    public Game(String id, String name, String designer, int yearPublished, int minPlayers, int maxPlayers, String playingTime, String minPlayTime, String maxPlayTime, List<String> categories, String description, String imageUrl, List<Rating> ratings, List<Post> mostRecentPosts, List<String> followers) {
        this.id = id;
        this.name = name;
        this.designer = designer;
        this.yearPublished = yearPublished;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.playingTime = playingTime;
        this.minPlayTime = minPlayTime;
        this.maxPlayTime = maxPlayTime;
        this.categories = categories;
        this.description = description;
        this.imageUrl = imageUrl;
        this.ratings = ratings;
        this.mostRecentPosts = mostRecentPosts;
        this.followers = followers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPlayingTime() {
        return playingTime;
    }

    public void setPlayingTime(String playingTime) {
        this.playingTime = playingTime;
    }

    public String getMinPlayTime() {
        return minPlayTime;
    }

    public void setMinPlayTime(String minPlayTime) {
        this.minPlayTime = minPlayTime;
    }

    public String getMaxPlayTime() {
        return maxPlayTime;
    }

    public void setMaxPlayTime(String maxPlayTime) {
        this.maxPlayTime = maxPlayTime;
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

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Post> getMostRecentPosts() {
        return mostRecentPosts;
    }

    public void setMostRecentPosts(List<Post> mostRecentPosts) {
        this.mostRecentPosts = mostRecentPosts;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }
}
