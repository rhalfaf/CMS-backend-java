package pl.softr.cms.exceptions;


public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException() {
        super("Post not found.");
    }
}
