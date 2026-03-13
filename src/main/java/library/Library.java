package library;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import library.exception.BookNotFoundException;
import library.exception.DuplicateBookException;
import java.util.function.Predicate;
import static library.utils.tools.toSortedList;


import library.domain.comparator.BookTitleComparator;
import library.domain.comparator.BookAuthorComparator;
public class Library {

    private Map<String, Book> books = new HashMap<>();

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
        return books.get(String.valueOf(ISBN));
    }

    public List<Book> getBooksFromAuthor(String author) {
        List<Book> result = books.values().stream()
                .filter(book -> book.getAuthor().equals(author))
                .toList();

        // ou
        // List<Book> result = filterBooks(book -> book.getAuthor().equals(author));

        if (result.isEmpty()) {
            throw new BookNotFoundException(author);
        }

        return result;
    }

    public List<Book> getBooksByTitle(String title) {
        String needle = title.toLowerCase();

        List<Book> result = books.values().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(needle))
                .toList();

        // ou
        // List<Book> result = filterBooks(book -> book.getTitle().toLowerCase().contains(needle));

        if (result.isEmpty()) {
            throw new BookNotFoundException(title);
        }

        return result;
    }

    public Book findBookByTitle(String title) {
        return books.values().stream()
                .filter(book -> book.getTitle().equals(title))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(title));
    }

    public Book getBookByIsbn(String isbn) {
        return books.values().stream()
                .filter(book -> book.getISBN().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public List<Book> filterBooks(Predicate<Book> predicate) {
        return books.values().stream()
                .filter(predicate)
                .toList();
    }

    public List<Book> getBooksSortedByTitle() {
        return books.values().stream()
                .collect(toSortedList(new BookTitleComparator()));
    }

    public List<Book> getBooksSortedByAuthor() {
        return books.values().stream()
                .collect(toSortedList(new BookAuthorComparator()));
    }
}