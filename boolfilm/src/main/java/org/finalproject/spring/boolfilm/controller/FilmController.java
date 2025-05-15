package org.finalproject.spring.boolfilm.controller;

import java.util.List;

import org.finalproject.spring.boolfilm.model.Film;
import org.finalproject.spring.boolfilm.service.CategoryService;
import org.finalproject.spring.boolfilm.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/film")
public class FilmController {

    // SERVICE
    @Autowired
    private FilmService filmService;

    @Autowired
    private CategoryService categoryService;

    // METHODS
    // INDEX
    @GetMapping
    public String index(Model model) {

        List<Film> film = filmService.findAll();

        model.addAttribute("film", film);

        return "film/index";
    }

    // SHOW
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {

        Film film = filmService.getById(id);

        model.addAttribute("film", film);

        return "film/show";
    }

    // CREATE
    @GetMapping("/create")
    public String create(Model model) {

        // add a new empty object film
        model.addAttribute("film", new Film());

        // categories
        model.addAttribute("categories", categoryService.findAll());

        return "film/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute Film formFilm, BindingResult bindingResult, Model model) {

        // if validation errors are present
        if (bindingResult.hasErrors()) {
            // categories
            model.addAttribute("categories", categoryService.findAll());
            return "film/create-or-edit";
        }

        // else, save
        filmService.save(formFilm);

        return "redirect:/film";
    }

    // UPDATE
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {

        Film film = filmService.getById(id);

        model.addAttribute("film", film);

        // categories
        model.addAttribute("categories", categoryService.findAll());

        // edit=true, for the form
        model.addAttribute("edit", true);

        return "film/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute Film formFilm, BindingResult bindingResult, Model model) {

        // if validation errors are present
        if (bindingResult.hasErrors()) {
            // categories
            model.addAttribute("categories", categoryService.findAll());
            return "film/create-or-edit";
        }

        // else, save
        filmService.save(formFilm);

        return "redirect:/film";
    }

    // DELETE
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {

        filmService.deleteById(id);

        return "redirect:/film";
    }
}
