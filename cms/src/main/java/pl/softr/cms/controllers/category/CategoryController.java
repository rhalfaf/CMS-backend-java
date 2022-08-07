package pl.softr.cms.controllers.category;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.softr.cms.models.category.Category;
import pl.softr.cms.models.category.CategoryDTO;
import pl.softr.cms.models.category.CategoryRepresentationAssembler;
import pl.softr.cms.services.category.CategoryService;

import static org.springframework.hateoas.MediaTypes.HAL_JSON;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController()
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;
    private final CategoryRepresentationAssembler categoryRepresentationAssembler;

    public CategoryController(CategoryService categoryService, CategoryRepresentationAssembler categoryRepresentationAssembler) {
        this.categoryService = categoryService;
        this.categoryRepresentationAssembler = categoryRepresentationAssembler;

    }

    @PostMapping("/add")
    public ResponseEntity<CategoryDTO> add(@RequestBody Category category) {
        return ResponseEntity
                .ok()
                .contentType(HAL_JSON)
                .body(categoryRepresentationAssembler.toModel(categoryService.save(category)));
    }

    @GetMapping(params = "id")
    public ResponseEntity<CategoryDTO> getById(@RequestParam Long id) {
        return ResponseEntity
                .ok()
                .contentType(HAL_JSON)
                .body(categoryRepresentationAssembler.toModel(categoryService.findById(id))
                        .add(linkTo(methodOn(CategoryController.class).findAll()).withRel("all categories")));
    }

    @GetMapping(params = "name")
    public ResponseEntity<CategoryDTO> getByName(@RequestParam String name) {
        return ResponseEntity
                .ok()
                .contentType(HAL_JSON)
                .body(categoryRepresentationAssembler.toModel(categoryService.findByName(name))
                        .add(linkTo(methodOn(CategoryController.class).findAll()).withRel("all categories")));

    }

    @GetMapping("/all")
    public ResponseEntity<CollectionModel<CategoryDTO>> findAll() {
        return ResponseEntity
                .ok()
                .contentType(HAL_JSON)
                .body(categoryRepresentationAssembler.toCollectionModel(categoryService.findAll()));
    }

}
