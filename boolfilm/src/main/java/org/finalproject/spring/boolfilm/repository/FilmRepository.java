package org.finalproject.spring.boolfilm.repository;

import org.finalproject.spring.boolfilm.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Integer> {

}
