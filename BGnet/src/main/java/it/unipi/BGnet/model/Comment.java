package it.unipi.BGnet.model;

import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@NoArgsConstructor
public class Comment {
    private String author;
    private String text;
    private String dateTime;
    public Comment(String author, String text) {
        this.author = author;
        this.text = text;
        this.dateTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
                .format(Calendar.getInstance().getTime());
    }
    public Comment(String author, String text, String dateTime) {
        this.author = author;
        this.text = text;
        this.dateTime = dateTime;
    }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public String getDateTime() { return dateTime; }
    public void setDateTime(String dateTime) { this.dateTime = dateTime; }
}
