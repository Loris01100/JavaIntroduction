package library;

import java.util.HashMap;
import java.util.Map;

import library.exception.BookNotFoundException;
import library.exception.DuplicateBookException;

public class Library {

    private Map<Long, Book> books = new HashMap<>();

    public Book findBookByTitle(String title) {
        return books.values().stream()
            .filter(book -> book.getTitle().equalsIgnoreCase(title))
            .findFirst()
            .orElseThrow(() -> new BookNotFoundException(title));
    }

    public void addBook(Book book) {
        if (books.containsKey(book.getISBN())) {
            throw new DuplicateBookException(book.getISBN());
        }
        books.put(book.getISBN(), book);
    }

    public void displayBooks() {
        for (Book book : books.values()) {
            System.out.println(book);
        }
    }

    public void deleteBook(Book book) {
        if (books.remove(book.getISBN()) == null) {
            throw new BookNotFoundException(String.valueOf(book.getISBN()));
        }
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