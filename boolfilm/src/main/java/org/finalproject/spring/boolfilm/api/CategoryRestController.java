package org.finalproject.spring.boolfilm.api;

import java.util.List;
import java.util.Optional;

import org.finalproject.spring.boolfilm.model.Category;
import org.finalproject.spring.boolfilm.service.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {

    // SERVICE
    @Autowired
    private CategoryService categoryService;

    // METHODS
    // INDEX
    @GetMapping
    public List<Category> index() {
        List<Category> category = categoryService.findAll();
        return category;
    }

    // SHOW
    @GetMapping("/{id}")
    public ResponseEntity<Category> show(@PathVariable("id") Integer id) {

        // find id
        Optional<Category> optionalCategory = categoryService.findById(id);

        // if film doesn't exist
        if (optionalCategory.isEmpty()) {
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }

        // else
        return new ResponseEntity<Category>(optionalCategory.get(), HttpStatus.OK);
    }

    // CREATE/STORE
    @PostMapping("/create")
    public ResponseEntity<Category> create(@Valid @RequestBody Category category) {
        return new ResponseEntity<Category>(categoryService.save(category), HttpStatus.OK);
    }

    // UPDATE
    @PutMapping("/edit/{id}")
    public ResponseEntity<Category> update(@Valid @RequestBody Category category, @PathVariable("id") Integer id) {

        // find id
        Optional<Category> optionalCategory = categoryService.findById(id);

        // if film doesn't exist
        if (optionalCategory.isEmpty()) {
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }

        // else
        return new ResponseEntity<Category>(categoryService.save(category), HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        // find id
        Optional<Category> optionalCategory = categoryService.findById(id);

        // if film doesn't exist
        if (optionalCategory.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // else
        categoryService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
