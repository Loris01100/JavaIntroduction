import java.util.Objects;

public class Book {

    private String title;
    private String author;
    private int year;
    private long ISBN;

    public Book(String title, String author, int year, long ISBN) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.ISBN = ISBN;
    }

    public long getISBN() { return ISBN; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }

    public void setISBN(long ISBN) { this.ISBN = ISBN; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return ISBN == book.ISBN;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ISBN);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", ISBN=" + ISBN +
                '}';
    }
}