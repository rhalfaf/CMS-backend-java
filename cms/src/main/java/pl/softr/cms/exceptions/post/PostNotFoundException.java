package pl.softr.cms.exceptions.post;


public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException() {
        super("Post not found.");
    }
}
