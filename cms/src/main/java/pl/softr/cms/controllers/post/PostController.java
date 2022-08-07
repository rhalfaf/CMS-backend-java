package pl.softr.cms.controllers.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.softr.cms.models.post.Post;
import pl.softr.cms.models.post.PostDTO;
import pl.softr.cms.models.post.PostRepresentationAssembler;
import pl.softr.cms.services.post.PostService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final PostRepresentationAssembler postRepresentationAssembler;
    private final PagedResourcesAssembler<Post> pagedResourcesAssembler;

    public PostController(PostService postService, PostRepresentationAssembler postRepresentationAssembler,
                          PagedResourcesAssembler<Post> pagedResourcesAssembler) {
        this.postService = postService;
        this.postRepresentationAssembler = postRepresentationAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @PostMapping(value = "/new",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PostDTO> savePost(@RequestBody Post post) {
        return ResponseEntity
                .ok()
                .contentType(MediaTypes.HAL_JSON)
                .body(postRepresentationAssembler.toModel(postService.save(post)));
    }

    @GetMapping("/all")
    public ResponseEntity<PagedModel<PostDTO>> getPostsPageable(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        Page<Post> postsPage = postService.findAll(PageRequest.of(page, size));
        return ResponseEntity
                .ok()
                .contentType(MediaTypes.HAL_JSON)
                .body(pagedResourcesAssembler.toModel(postsPage, postRepresentationAssembler));
    }

    @GetMapping()
    public ResponseEntity<PostDTO> getPost(@RequestParam Long id) {
        Post post = this.postService.getPostById(id);
        return ResponseEntity
                .ok()
                .contentType(MediaTypes.HAL_JSON)
                .body(postRepresentationAssembler.toModel(post));
    }
}
