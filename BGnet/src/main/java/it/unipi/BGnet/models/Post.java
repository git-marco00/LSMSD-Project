package it.unipi.BGnet.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "post")
public class Post {
    @Id
    private String id;
    private String author;
    private String game;
    private String text;
    private List<String> likes;
    private String timestamp;
    private List<Comment> comments;

    public Post(String id, String author, String game, String text) {
        this.id = id;
        this.author = author;
        this.game = game;
        this.text = text;
    }
    public Post(String id, String author, String game, String text, String dateTime, List<Comment> commentList) {
        this.id = id;
        this.author = author;
        this.game = game;
        this.text = text;
        this.timestamp = dateTime;
        this.comments = commentList;
    }
    public void addLike(String name){
        likes.add(name);
    }
    public void removeLike(String name){
        likes.remove(name);
    }
    public void addComment(Comment comment){
        comments.add(0, comment);
    }
    public void removeComment(Comment comment){
        comments.remove(comment);
    }
    public List<String> getLikes() {
        return likes;
    }
    public void setLikes(List<String> likes) {
        this.likes = likes;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getGame() {
        return game;
    }
    public void setGame(String game) {
        this.game = game;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public List<Comment> getComments() {
        return comments;
    }
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
