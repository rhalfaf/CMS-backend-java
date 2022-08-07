package pl.softr.cms.models.post;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import pl.softr.cms.controllers.category.CategoryController;
import pl.softr.cms.controllers.post.PostController;

import java.util.function.Supplier;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PostRepresentationAssembler implements RepresentationModelAssembler<Post, PostDTO> {

    @Override
    public PostDTO toModel(Post entity) {
        return PostDTO.builder()
                .id(entity.getId())
                .createDate(entity.getCreateDate())
                .publishingDate(entity.getPublishingDate())
                .category(entity.getCategory())
                .title(entity.getTitle())
                .content(entity.getContent())
                .build()
                .add(linkTo(methodOn(PostController.class).getPost(entity.getId())).withSelfRel())
                .addIf(entity.getCategory() != null,
                        () -> linkTo(methodOn(CategoryController.class).getById(entity.getCategory().getId())).withRel("category"));
    }

    @Override
    public CollectionModel<PostDTO> toCollectionModel(Iterable<? extends Post> entities) {
        return RepresentationModelAssembler
                .super
                .toCollectionModel(entities)
                .add(linkTo(methodOn(PostController.class)).withSelfRel());
    }
}
