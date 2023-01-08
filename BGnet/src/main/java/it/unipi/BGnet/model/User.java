package it.unipi.BGnet.model;

import java.util.List;

public class User {
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private int yearRegistered;
    private String email;
    private String stateOfProvince;
    private String country;
    private String continent;
    private String imgUrl;
    private List<Post> mostRecentPosts;

    public User(int id, String username, String password, String firstName, String lastName, int yearRegistered, String email, String stateOfProvince, String country, String continent, String imgUrl, List<Post> mostRecentPosts) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearRegistered = yearRegistered;
        this.email = email;
        this.stateOfProvince = stateOfProvince;
        this.country = country;
        this.continent = continent;
        this.imgUrl = imgUrl;
        this.mostRecentPosts = mostRecentPosts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getYearRegistered() {
        return yearRegistered;
    }

    public void setYearRegistered(int yearRegistered) {
        this.yearRegistered = yearRegistered;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStateOfProvince() {
        return stateOfProvince;
    }

    public void setStateOfProvince(String stateOfProvince) {
        this.stateOfProvince = stateOfProvince;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<Post> getMostRecentPosts() {
        return mostRecentPosts;
    }

    public void setMostRecentPosts(List<Post> mostRecentPosts) {
        this.mostRecentPosts = mostRecentPosts;
    }
}
