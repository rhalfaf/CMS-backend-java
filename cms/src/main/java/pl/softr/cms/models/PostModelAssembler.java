package pl.softr.cms.models;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import pl.softr.cms.controllers.PostController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PostModelAssembler implements RepresentationModelAssembler<Post, EntityModel<Post>> {
    @Override
    public EntityModel<Post> toModel(Post post) {
        return EntityModel.of(post,
                linkTo(methodOn(PostController.class).getPost(post.getId())).withSelfRel(),
                linkTo(methodOn(PostController.class).getPosts()).withRel("all")
        );
    }
}
