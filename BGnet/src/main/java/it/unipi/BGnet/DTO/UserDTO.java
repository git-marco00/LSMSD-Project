package it.unipi.BGnet.DTO;

import it.unipi.BGnet.model.Post;

import java.util.List;

public class UserDTO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private int yearRegistered;
    private String email;
    private String stateOfProvince;
    private String country;
    private String continent;
    private String img;
    private List<Post> mostRecentPosts;
    public UserDTO(String username, String password, String firstName, String lastName, int yearRegistered, String email, String stateOfProvince, String country, String continent, String img, List<Post> mostRecentPosts) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearRegistered = yearRegistered;
        this.email = email;
        this.stateOfProvince = stateOfProvince;
        this.country = country;
        this.continent = continent;
        this.img = img;
        this.mostRecentPosts = mostRecentPosts;
    }
    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getUsername() { return username; }
}
