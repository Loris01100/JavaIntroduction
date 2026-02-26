import java.util.HashMap;
import java.util.Map;

public class Library {

    private Map<Long, Book> books = new HashMap<>();

    public boolean addBook(Book book) {
        if (books.containsKey(book.getISBN())) {
            System.out.println("Un livre avec cet ISBN existe déjà : " + book.getISBN());
            return false;
        }
        books.put(book.getISBN(), book);
        return true;
    }

    public void displayBooks() {
        for (Book book : books.values()) {
            System.out.println(book);
        }
    }

    public boolean deleteBook(Book book) {
        return books.remove(book.getISBN()) != null;
    }

    public Book searchBookByTitle(String title) {
        for (Book book : books.values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public Book searchBookByAuthor(String author) {
        for (Book book : books.values()) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                return book;
            }
        }
        return null;
    }

    public Book searchBookByISBN(long ISBN) {
        return books.get(ISBN);
    }
}