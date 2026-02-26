package library.exception;
public class DuplicateBookException extends LibraryException {

    public DuplicateBookException(long ISBN) {
        super("Livre déjà présent : " + ISBN);
    }
    
}
