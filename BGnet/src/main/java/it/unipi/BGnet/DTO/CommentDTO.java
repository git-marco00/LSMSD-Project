package it.unipi.BGnet.DTO;

public class CommentDTO {
    private String author;
    private String text;
    private String timestamp;
    public CommentDTO(String author, String text, String timestamp) {
        this.author = author;
        this.text = text;
        this.timestamp = timestamp;
    }
    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }

    public String getTimestamp() { return timestamp; }

    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}
