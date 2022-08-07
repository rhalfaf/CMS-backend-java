package pl.softr.cms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.softr.cms.models.post.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
