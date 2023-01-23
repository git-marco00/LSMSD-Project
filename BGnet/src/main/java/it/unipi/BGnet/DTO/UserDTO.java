package it.unipi.BGnet.DTO;

import it.unipi.BGnet.model.Post;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
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
    private List<PostDTO> mostRecentPosts;
    private List<InCommonGenericDTO> inCommonFollowers;
    private List<TournamentDTO> inCommonTournaments;
    private int followers;
    private boolean isFollowed;

    private boolean isMyself;

    public boolean isMyself() {
        return isMyself;
    }

    public void setMyself(boolean myself) {
        isMyself = myself;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public boolean isFollowed() {
        return isFollowed;
    }

    public void setFollowed(boolean followed) {
        isFollowed = followed;
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

    public void setMostRecentPosts(List<PostDTO> mostRecentPosts) {
        this.mostRecentPosts = mostRecentPosts;
    }
    public UserDTO(String username, String firstName, String lastName, String img) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.img = img;
    }

    public List<InCommonGenericDTO> getInCommonFollowers() {
        return inCommonFollowers;
    }
    public void setInCommonFollowers(List<InCommonGenericDTO> inCommonFollowers) {
        this.inCommonFollowers = inCommonFollowers;
    }

    public List<TournamentDTO> getInCommonTournaments() {
        return inCommonTournaments;
    }

    public void setInCommonTournaments(List<TournamentDTO> inCommonTournaments) {
        this.inCommonTournaments = inCommonTournaments;
    }
    public void setUsername(String username) {
        this.username = username;
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
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public List<PostDTO> getMostRecentPosts() {
        return mostRecentPosts;
    }
    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getUsername() { return username; }
    public void setMostRecentPosts(List<Post> postList, String username) {
        List<PostDTO> postDTOList = new ArrayList<>();
        for (Post post : postList) {
            PostDTO postDTO = new PostDTO();
            postDTO.setId(post.getId());
            postDTO.setGame(post.getGame());
            postDTO.setAuthor(post.getAuthor());
            postDTO.setLikes(post.getLikes().size());
            postDTO.setComments(post.getComments().size());
            postDTO.setDate(post.getTimestamp());
            postDTO.setText(post.getText());
            postDTO.setHasLiked(post.getLikes().contains(username));
            System.out.println("HAS_LIKED >> " + post.getLikes().contains(username));
            postDTOList.add(postDTO);
        }
        this.mostRecentPosts =  postDTOList;
    }
}
