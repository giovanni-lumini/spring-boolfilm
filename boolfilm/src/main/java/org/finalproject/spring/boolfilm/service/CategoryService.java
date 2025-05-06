package org.finalproject.spring.boolfilm.service;

import java.util.List;
import java.util.Optional;

import org.finalproject.spring.boolfilm.model.Category;
import org.finalproject.spring.boolfilm.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CategoryService {

    // REPOSITORY
    @Autowired
    private CategoryRepository categoryRepository;

    // METHODS
    // findAll
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    // getById
    public Category getById(Integer id) {

        // find id
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        // if category doesn't exist, exeption 404
        if (optionalCategory.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }

        // else
        return optionalCategory.get();
    }

    // save
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    // deleteById
    public void deleteById(Integer id) {

        // find id
        Optional<Category> optionalCategoryToDelete = categoryRepository.findById(id);

        // if category doesn't exist, exeption 404
        if (optionalCategoryToDelete.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }

        categoryRepository.delete(optionalCategoryToDelete.get());
    }
}
