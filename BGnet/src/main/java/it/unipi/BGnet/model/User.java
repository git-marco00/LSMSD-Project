package it.unipi.BGnet.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.List;

@Document(collection = "users")
public class User {
    @Id
    private BigInteger id;
    @NotBlank
    @Size(max = 20)
    private String username;
    @NotBlank
    @Size(max = 100)
    private String password;
    @NotBlank
    @Size(max = 20)
    private String firstName;
    @NotBlank
    @Size(max = 20)
    private String lastName;
    private int yearRegistered;
    @NotBlank
    @Size(max = 100)
    @Email
    private String email;
    private String stateOfProvince;
    private String country;
    private String continent;
    private String img;
    private List<Post> mostRecentPosts;

    public User(String username, String password, String firstName, String lastName, int yearRegistered, String email, String stateOfProvince, String country, String continent, String img, List<Post> mostRecentPosts) {
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
    public User(String username, String password, String firstName, String lastName, int yearRegistered, String email, String stateOfProvince, String country, String continent) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearRegistered = yearRegistered;
        this.email = email;
        this.stateOfProvince = stateOfProvince;
        this.country = country;
        this.continent = continent;
    }

    public User() {}
    public BigInteger getId() { return id; }
    public void setId(BigInteger id) {
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
    public void setContinent(String continent) { this.continent = continent; }
    public String getImg() { return img; }
    public void setImg(String img) { this.img = img; }
    public List<Post> getMostRecentPosts() { return mostRecentPosts; }
    public void setMostRecentPosts(List<Post> mostRecentPosts) { this.mostRecentPosts = mostRecentPosts; }
}
