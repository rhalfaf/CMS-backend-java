package pl.softr.cms.models.category;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import pl.softr.cms.models.post.Post;

import java.util.Set;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@Relation(itemRelation = "category", collectionRelation = "categories")
public class CategoryDTO extends RepresentationModel<CategoryDTO> {
    private final Long id;
    private final String name;
}
