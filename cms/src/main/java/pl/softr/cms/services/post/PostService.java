package pl.softr.cms.services.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.softr.cms.models.post.Post;

import java.util.List;

public interface PostService {
    Post getPostById(Long id);
    Post save(Post post);
    List<Post> findAll();
    Page<Post> findAll(Pageable pageable);
}
