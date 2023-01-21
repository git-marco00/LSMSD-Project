package it.unipi.BGnet.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Document(collection = "game")
public class Game {
    @Id
    private String id;
    private String name;
    private String designer;
    @Field("yearpublished")
    private int yearPublished;
    @Field("minplayers")
    private int minPlayers;
    @Field("maxplayers")
    private int maxPlayers;
    private String playingTime;
    private String minPlayTime;
    private String maxPlayTime;
    private List<String> categories;
    private String description;
    private String img;
    private List<String> ratings;
    @Field("most_recent_post")
    private List<Post> mostRecentPosts;
    private List<String> followers;
    @Field("avg_rate")
    private float avgRate;

    public Game(String name, String designer, int yearPublished, int minPlayers, int maxPlayers, String playingTime, String minPlayTime, String maxPlayTime, List<String> categories, String description, String img) {
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
        this.img = img;
        this.ratings = new ArrayList<>();
        this.mostRecentPosts = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.avgRate = 0;
    }

    public Game(String id, String name, String designer, int yearPublished, int minPlayers, int maxPlayers, String playingTime, String minPlayTime, String maxPlayTime, List<String> categories, String description, String imageUrl, List<String> ratings, List<Post> mostRecentPosts, List<String> followers) {
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
        this.img = imageUrl;
        this.ratings = ratings;
        this.mostRecentPosts = mostRecentPosts;
        this.followers = followers;
    }
    public Game(String id, String name, String designer, int yearPublished, int minPlayers, int maxPlayers, String playingTime, String minPlayTime, String maxPlayTime, List<String> categories, String description, String imageUrl, List<String> ratings, List<Post> mostRecentPosts, List<String> followers, float avgRate) {
        this(id, name, designer, yearPublished, minPlayers, maxPlayers, playingTime, minPlayTime, maxPlayTime, categories, description, imageUrl,ratings, mostRecentPosts, followers);
        this.avgRate = avgRate;
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
        return img;
    }
    public void setImageUrl(String imageUrl) {
        this.img = imageUrl;
    }
    public void setRatings(List<String> ratings) {
        this.ratings = ratings;
    }
    public List<Post> getMostRecentPosts() { return mostRecentPosts; }
    public void setMostRecentPosts(List<Post> mostRecentPosts) {
        this.mostRecentPosts = mostRecentPosts;
    }
    public List<String> getFollowers() {
        return followers;
    }
    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }
    public boolean haveIVoted(String username){
        for(String user : ratings){
            if (user.equals(username)){
                return true;
            }
        }
        return false;
    }

    public void addRate(String username, int rate){
        // Avg = SommaVoti / NumeroVoti
        // Somma = Avg * numeroVoti
        // NuovaAvg = (SommaVoti + rate) / (NumeroVoti + 1)
        float somma = avgRate* ratings.size();
        avgRate = (somma + rate) / (ratings.size() + 1);
        ratings.add(username);
    }
    public float getAvgRate() { return avgRate; }

    public void removeRate(String username) {
        ratings.remove(username);
    }
}
