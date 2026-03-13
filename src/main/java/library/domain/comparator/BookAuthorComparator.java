package library.domain.comparator;

import library.Book;

import java.util.Comparator;

public  class BookAuthorComparator implements Comparator<Book> {
    @Override
    public int compare(Book a, Book b) {
        return a.getAuthor().compareToIgnoreCase(b.getAuthor());
    }
}