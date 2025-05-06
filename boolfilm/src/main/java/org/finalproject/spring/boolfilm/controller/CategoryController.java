package org.finalproject.spring.boolfilm.controller;

import java.util.List;
import java.util.Optional;

import org.finalproject.spring.boolfilm.model.Category;
import org.finalproject.spring.boolfilm.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    // REPOSITORY
    @Autowired
    private CategoryRepository categoryRepository;

    // METHODS
    // INDEX
    @GetMapping
    public String index(Model model) {

        // find all film
        List<Category> category = categoryRepository.findAll();

        model.addAttribute("categories", category);

        return "category/index";
    }

    // SHOW
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {

        // find id
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        // if film doesn't exist, exeption 404
        if (categoryOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Film not found");
        }

        // else
        model.addAttribute("category", categoryOptional.get());

        return "category/show";
    }

    // CREATE

    // UPDATE

    // DELETE
}
