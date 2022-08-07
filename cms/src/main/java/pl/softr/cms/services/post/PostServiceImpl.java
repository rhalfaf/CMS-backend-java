package pl.softr.cms.services.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.softr.cms.exceptions.category.CategoryNotFoundException;
import pl.softr.cms.exceptions.post.IncompletePostException;
import pl.softr.cms.exceptions.post.PostNotFoundException;
import pl.softr.cms.models.category.Category;
import pl.softr.cms.models.post.Post;
import pl.softr.cms.repositories.CategoryRepository;
import pl.softr.cms.repositories.PostRepository;
import pl.softr.cms.services.category.CategoryService;

import java.time.Instant;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository repository;
    private final CategoryService categoryService;

    public PostServiceImpl(PostRepository repository, CategoryService categoryService) {
        this.repository = repository;
        this.categoryService = categoryService;
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
        if (post.getCreateDate() == null) {
            post.setCreateDate(Instant.now());
        }
        if (post.getPublishingDate() == null) {
            post.setPublishingDate(post.getCreateDate());
        }
        if (post.getCategory() == null || !categoryService.categoryExist(post.getCategory())) {
            throw new CategoryNotFoundException();
        }
        return repository.save(post);
    }

    @Override
    public List<Post> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


}
