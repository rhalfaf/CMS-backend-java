package pl.softr.cms.controllers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.softr.cms.models.Post;
import pl.softr.cms.models.PostModelAssembler;
import pl.softr.cms.services.PostService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final PostModelAssembler postModelAssembler;

    public PostController(PostService postService, PostModelAssembler postModelAssembler) {
        this.postService = postService;
        this.postModelAssembler = postModelAssembler;

    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<Post> savePost(@RequestBody Post post) {
        return postModelAssembler.toModel(this.postService.save(post));
    }

    @GetMapping("/all")
    public CollectionModel<EntityModel<Post>> getPosts() {
        List<EntityModel<Post>> posts = postService.findAll().stream().map(
                postModelAssembler::toModel
        ).collect(Collectors.toList());
        return CollectionModel.of(posts, linkTo(methodOn(PostController.class).getPosts()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Post> getPost(@PathVariable Long id) {
        Post post = this.postService.getPostById(id);
        return postModelAssembler.toModel(post);
    }
}
