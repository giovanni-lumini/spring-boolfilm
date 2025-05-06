package org.finalproject.spring.boolfilm.api;

import java.util.List;
import java.util.Optional;

import org.finalproject.spring.boolfilm.model.Film;
import org.finalproject.spring.boolfilm.service.FilmService;
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
@RequestMapping("/api/film")
public class FilmRestController {

    // SERVICE
    @Autowired
    private FilmService filmService;

    // METHODS
    // INDEX
    @GetMapping
    public List<Film> index() {
        List<Film> film = filmService.findAll();
        return film;
    }

    // SHOW
    @GetMapping("/{id}")
    public ResponseEntity<Film> show(@PathVariable("id") Integer id) {

        // find id
        Optional<Film> optionalFilm = filmService.findById(id);

        // if film doesn't exist
        if (optionalFilm.isEmpty()) {
            return new ResponseEntity<Film>(HttpStatus.NOT_FOUND);
        }

        // else
        return new ResponseEntity<Film>(optionalFilm.get(), HttpStatus.OK);
    }

    // CREATE/STORE
    @PostMapping("/create")
    public ResponseEntity<Film> create(@Valid @RequestBody Film film) {
        return new ResponseEntity<Film>(filmService.save(film), HttpStatus.OK);
    }

    // UPDATE
    @PutMapping("/edit/{id}")
    public ResponseEntity<Film> update(@Valid @RequestBody Film film, @PathVariable("id") Integer id) {

        // find id
        Optional<Film> optionalFilm = filmService.findById(id);

        // if film doesn't exist
        if (optionalFilm.isEmpty()) {
            return new ResponseEntity<Film>(HttpStatus.NOT_FOUND);
        }

        // else
        return new ResponseEntity<Film>(filmService.save(film), HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        // find id
        Optional<Film> optionalFilm = filmService.findById(id);

        // if film doesn't exist
        if (optionalFilm.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // else
        filmService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
