package it.unipi.BGnet.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;
import java.util.List;

@Document(collection = "post")
public class Post {

    @Id
    private String id;
    private String author;
    private String game;
    private String text;
    private OffsetDateTime dateTime;
    private List<Comment> commentList;

    public Post(String id, String author, String game, String text) {
        this.id = id;
        this.author = author;
        this.game = game;
        this.text = text;
    }

    public Post(String id, String author, String game, String text, OffsetDateTime dateTime, List<Comment> commentList) {
        this.id = id;
        this.author = author;
        this.game = game;
        this.text = text;
        this.dateTime = dateTime;
        this.commentList = commentList;
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

    public OffsetDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(OffsetDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
