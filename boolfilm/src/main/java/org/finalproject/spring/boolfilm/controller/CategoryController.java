package org.finalproject.spring.boolfilm.controller;

import java.util.List;
import java.util.Optional;

import org.finalproject.spring.boolfilm.model.Category;
import org.finalproject.spring.boolfilm.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

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

        // if category doesn't exist, exeption 404
        if (categoryOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }

        // else
        model.addAttribute("category", categoryOptional.get());

        return "category/show";
    }

    // CREATE
    @GetMapping("/create")
    public String create(Model model) {

        // add a new empty object Category
        Category category = new Category();

        model.addAttribute("category", category);

        return "category/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute Category formCategory, BindingResult bindingResult) {

        // if validation errors are present
        if (bindingResult.hasErrors()) {
            return "category/create-or-edit";
        }

        // else, save
        categoryRepository.save(formCategory);

        return "redirect:/categories";
    }

    // UPDATE
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {

        // find id
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        // if category doesn't exist, exeption 404
        if (categoryOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }

        // else
        model.addAttribute("category", categoryOptional.get());

        // edit=true, for the form
        model.addAttribute("edit", true);

        return "category/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute Category formCategory, BindingResult bindingResult) {

        // if validation errors are present
        if (bindingResult.hasErrors()) {
            return "category/create-or-edit";
        }

        // else, save
        categoryRepository.save(formCategory);

        return "redirect:/categories";
    }

    // DELETE
}
