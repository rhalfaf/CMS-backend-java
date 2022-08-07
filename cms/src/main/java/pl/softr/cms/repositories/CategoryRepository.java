package pl.softr.cms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.softr.cms.models.category.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByName(String name);
}
