package it.unipi.BGnet.DTO;

import it.unipi.BGnet.model.Post;

import java.util.ArrayList;
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
    private float avgRate;
    private List<PostDTO> mostRecentPosts;
    private int followers;
    private boolean followed;
    private boolean rated;  // -1 if not rated
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
    public float getAvgRate() {
        return avgRate;
    }
    public void setAvgRate(float avgRate) {
        this.avgRate = avgRate;
    }
    public List<PostDTO> getMostRecentPosts() {
        return mostRecentPosts;
    }
    public void setMostRecentPosts(List<Post> mostRecentPosts) {
        List<PostDTO> mostRecentPostsDTO = new ArrayList<>();
        mostRecentPosts.forEach((post) -> {
            PostDTO postDTO = new PostDTO();
            postDTO.setId(post.getId());
            postDTO.setAuthor(post.getAuthor());
            postDTO.setDate(post.getTimestamp());
            postDTO.setText(post.getText());
            postDTO.setLikes(post.getLikes().size());
            postDTO.setComments(post.getComments().size());
            mostRecentPostsDTO.add(postDTO);
        });
        this.mostRecentPosts = mostRecentPostsDTO;
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
    public boolean getRated() {
        return rated;
    }
    public void setRated(boolean rated) {
        this.rated = rated;
    }
    public List<String> getInCommonFollowers() {
        return inCommonFollowers;
    }
    public void setInCommonFollowers(List<String> inCommonFollowers) {
        this.inCommonFollowers = inCommonFollowers;
    }
    @Override
    public String toString() {
        return "GamePage{" +
                "gameName='" + gameName + '\'' +
                ", designer='" + designer + '\'' +
                ", yearPublished=" + yearPublished +
                ", minPlayers=" + minPlayers +
                ", maxPlayers=" + maxPlayers +
                ", categories=" + categories +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", avgRate=" + avgRate +
                ", mostRecentPosts=" + mostRecentPosts +
                ", followers=" + followers +
                ", followed=" + followed +
                ", rated=" + rated +
                ", inCommonFollowers=" + inCommonFollowers +
                '}';
    }
}
