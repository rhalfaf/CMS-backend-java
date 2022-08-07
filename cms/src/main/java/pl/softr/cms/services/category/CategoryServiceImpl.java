package pl.softr.cms.services.category;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.softr.cms.exceptions.category.CategoryAlreadyExistException;
import pl.softr.cms.exceptions.category.CategoryNotFoundException;
import pl.softr.cms.models.category.Category;
import pl.softr.cms.repositories.CategoryRepository;

import java.text.MessageFormat;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category) {
        if (categoryRepository.exists(Example.of(category))) {
            throw new CategoryAlreadyExistException("Category with name " + category.getName() + " already exist.");
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public boolean categoryExist(Category category) {
        return categoryRepository.exists(Example.of(category));
    }
}
