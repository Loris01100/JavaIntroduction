package library.exception;

public class BookNotFoundException extends LibraryException {
    public BookNotFoundException(String identifier) {
        super("Livre introuvable : " + identifier);
    }
    
}
