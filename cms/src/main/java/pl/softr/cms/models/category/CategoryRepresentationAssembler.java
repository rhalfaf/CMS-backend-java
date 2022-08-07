package pl.softr.cms.models.category;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import pl.softr.cms.controllers.category.CategoryController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CategoryRepresentationAssembler implements RepresentationModelAssembler<Category, CategoryDTO> {
    @Override
    public CategoryDTO toModel(Category entity) {
        return CategoryDTO
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .build()
                .add(linkTo(methodOn(CategoryController.class).getById(entity.getId())).withSelfRel());
    }

    @Override
    public CollectionModel<CategoryDTO> toCollectionModel(Iterable<? extends Category> entities) {
        return RepresentationModelAssembler
                .super
                .toCollectionModel(entities)
                .add(linkTo(methodOn(CategoryController.class)
                        .findAll())
                        .withSelfRel());
    }
}
