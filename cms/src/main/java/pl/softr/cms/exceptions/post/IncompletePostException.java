package pl.softr.cms.exceptions.post;

public class IncompletePostException extends RuntimeException {
    public IncompletePostException(){
        super("Title and content can't be empty.");
    }
}
