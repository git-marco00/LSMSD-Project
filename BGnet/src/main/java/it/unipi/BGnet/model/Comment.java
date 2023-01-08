package it.unipi.BGnet.model;

import java.time.OffsetDateTime;


public class Comment {
    private String id;
    private String author;
    private String text;
    private OffsetDateTime dateTime;

    public Comment(String id, String author, String text) {
        this.id = id;
        this.author = author;
        this.text = text;
    }

    public Comment(String id, String author, String text, OffsetDateTime dateTime) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.dateTime = dateTime;
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
}
