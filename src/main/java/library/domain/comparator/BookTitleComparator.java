package library.domain.comparator;

import library.Book;

import java.util.Comparator;

public  class BookTitleComparator implements Comparator<Book> {
    @Override
    public int compare(Book a, Book b) {
        return a.getTitle().compareToIgnoreCase(b.getTitle());
    }
}