package org.finalproject.spring.boolfilm.controller;

import java.util.List;

import org.finalproject.spring.boolfilm.model.Film;
import org.finalproject.spring.boolfilm.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/film")
public class FilmController {

    // REPOSITORY
    @Autowired
    private FilmRepository filmRepository;

    // METHODS
    @GetMapping
    public String index(Model model) {

        // find all film
        List<Film> film = filmRepository.findAll();

        model.addAttribute("film", film);

        return "film/index";
    }

}
