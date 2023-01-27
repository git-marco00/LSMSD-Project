package it.unipi.BGnet.DTO;

import java.util.List;

public class PostDTO {
    private String id;
    private String author;
    private String game;
    private String authorImgUrl;
    private String text;
    private int likes;
    private int comments;
    private List<CommentDTO> listOfComments;
    private String date;
    private boolean hasLiked;
    public PostDTO() {}
    public PostDTO(String id, String game, String author, int likes, int comments, String date, String text, boolean hasLiked) {
        this.id = id;
        this.game = game;
        this.author = author;
        this.likes = likes;
        this.comments = comments;
        this.date = date;
        this.text = text;
        this.hasLiked = hasLiked;
    }
    public PostDTO(String id, String game, String author, int likes, List<CommentDTO> listOfComments, String date, String text, boolean hasLiked) {
        this.id = id;
        this.game = game;
        this.author = author;
        this.likes = likes;
        this.comments = listOfComments.size();
        this.listOfComments = listOfComments;
        this.date = date;
        this.text = text;
        this.hasLiked = hasLiked;
    }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getGame() { return game; }
    public void setGame(String game) { this.game = game; }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorImgUrl() {
        return authorImgUrl;
    }

    public void setAuthorImgUrl(String authorImgUrl) {
        this.authorImgUrl = authorImgUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public boolean getHasLiked() { return hasLiked; }
    public void setHasLiked(boolean hasLiked) { this.hasLiked = hasLiked; }
}
