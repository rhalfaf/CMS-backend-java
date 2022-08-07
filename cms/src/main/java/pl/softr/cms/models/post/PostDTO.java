package pl.softr.cms.models.post;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import pl.softr.cms.models.category.Category;

import java.time.Instant;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@Relation(itemRelation = "post", collectionRelation = "posts")
public class PostDTO extends RepresentationModel<PostDTO> {
    private final Long id;
    private final Instant createDate;
    private final Instant publishingDate;
    private final Category category;
    private final String title;
    private final String content;
}
