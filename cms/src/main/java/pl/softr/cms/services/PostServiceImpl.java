package pl.softr.cms.services;

import org.springframework.stereotype.Service;
import pl.softr.cms.exceptions.IncompletePostException;
import pl.softr.cms.exceptions.PostNotFoundException;
import pl.softr.cms.models.Post;
import pl.softr.cms.repositories.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository repository;

    public PostServiceImpl(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public Post getPostById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(PostNotFoundException::new);
    }

    @Override
    public Post save(Post post) {
        if (post.getContent().isEmpty() || post.getTitle().isEmpty()) {
            throw new IncompletePostException();
        }
        return repository.save(post);
    }

    @Override
    public List<Post> findAll() {
        return repository.findAll();
    }


}
