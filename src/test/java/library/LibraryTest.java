package library;

import library.exception.BookNotFoundException;
import library.exception.DuplicateBookException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Library library;

    @BeforeEach
    void setUp() {
        library = new Library();
        library.addBook(new Book("Clean Code", "Robert Martin", 2008, "9780132350884L"));
        library.addBook(new Book("Effective Java", "Joshua Bloch", 2018, "9780134686097L"));
    }

    // --- findBookByTitle ---

    @Test
    void shouldFindBookByTitle() {
        Book book = library.findBookByTitle("Clean Code");
        assertEquals("Clean Code", book.getTitle());
    }

    @ParameterizedTest
    @ValueSource(strings = {"clean code", "CLEAN CODE", "Clean Code"})
    void shouldFindBookByTitleCaseInsensitive(String title) {
        Book book = library.findBookByTitle(title);
        assertEquals("Clean Code", book.getTitle());
    }

    @Test
    void shouldThrowWhenBookNotFound() {
        assertThrows(BookNotFoundException.class,
            () -> library.findBookByTitle("Livre inexistant"));
    }

    // --- addBook ---

    @Test
    void shouldThrowOnDuplicateISBN() {
        assertThrows(DuplicateBookException.class,
            () -> library.addBook(new Book("Clean Code", "Robert Martin", 2008, "9780132350884L")));
    }

    // --- deleteBook ---

    @Test
    void shouldDeleteExistingBook() {
        Book book = library.findBookByTitle("Clean Code");
        library.deleteBook(book);
        assertThrows(BookNotFoundException.class,
            () -> library.findBookByTitle("Clean Code"));
    }

    @Test
    void shouldThrowWhenDeletingNonExistentBook() {
        Book ghost = new Book("Fantôme", "Inconnu", 2000, "0000000000000L");
        assertThrows(BookNotFoundException.class,
            () -> library.deleteBook(ghost));
    }

    // --- Cas limites ---

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowOnNullOrEmptyTitle(String title) {
        assertThrows(Exception.class,
            () -> library.findBookByTitle(title));
    }
}
