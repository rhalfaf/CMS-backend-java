package pl.softr.cms.services.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.softr.cms.models.category.Category;

import java.util.List;

public interface CategoryService {
    Category save (Category category);
    Category findById(Long id);
    Category findByName(String name);
    List<Category> findAll();
    Page<Category> findAll(Pageable pageable);
    boolean categoryExist(Category category);
}
