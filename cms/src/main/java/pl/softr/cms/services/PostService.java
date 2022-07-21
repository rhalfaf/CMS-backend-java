package pl.softr.cms.services;

import pl.softr.cms.models.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Post getPostById(Long id);
    Post save(Post post);
    List<Post> findAll();
}
