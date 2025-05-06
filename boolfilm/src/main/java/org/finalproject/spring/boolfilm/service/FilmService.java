package org.finalproject.spring.boolfilm.service;

import java.util.List;
import java.util.Optional;

import org.finalproject.spring.boolfilm.model.Category;
import org.finalproject.spring.boolfilm.model.Film;
import org.finalproject.spring.boolfilm.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FilmService {

    // REPOSITORY
    @Autowired
    private FilmRepository filmRepository;

    // METHODS
    // findAll
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    // findById
    public Optional<Film> findById(Integer id) {
        return filmRepository.findById(id);
    }

    // getById
    public Film getById(Integer id) {

        // find id
        Optional<Film> optionalFilm = filmRepository.findById(id);

        // if film doesn't exist, exeption 404
        if (optionalFilm.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Film not found");
        }

        // else
        return optionalFilm.get();
    }

    // save
    public Film save(Film film) {
        return filmRepository.save(film);
    }

    // deleteById
    public void deleteById(Integer id) {

        Film film = getById(id);

        // remove film for each categories
        for (Category category : film.getCategories()) {
            category.getFilm().remove(film);
        }

        // clear the movie category list (not obligatory, but safe)
        film.getCategories().clear();

        filmRepository.delete(film);
    }
}
