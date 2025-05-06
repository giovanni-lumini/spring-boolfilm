package org.finalproject.spring.boolfilm.controller;

import java.util.List;

import org.finalproject.spring.boolfilm.model.Category;
import org.finalproject.spring.boolfilm.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    // CREATE

    // UPDATE

    // DELETE
}
