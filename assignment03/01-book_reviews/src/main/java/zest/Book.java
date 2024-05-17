package zest;

public class Book {
    private String title;
    private String author;
    private int rating; // Rating on a scale from 1 to 5

    public Book(String title, String author, int rating) {
        this.title = title;
        this.author = author;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }
    public String getAuthor() { return author; }
    public int getRating() {
        return rating;
    }
}