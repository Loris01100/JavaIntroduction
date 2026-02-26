package library.exception;
public class DuplicateBookException extends LibraryException {

    public DuplicateBookException(String ISBN) {
        super("Livre déjà présent : " + ISBN);
    }
    
}
