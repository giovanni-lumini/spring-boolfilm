package org.finalproject.spring.boolfilm.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "categories")
public class Category {

    // VARIABLES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "title mustn't be null, empty or blank")
    private String name;

    @Lob
    private String description;

    // MANY TO MANY
    @ManyToMany(mappedBy = "categories", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Film> film;

    // GETTERS E SETTERS
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Film> getFilm() {
        return this.film;
    }

    public void setFilm(List<Film> film) {
        this.film = film;
    }

    // TO STRING
    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", description='" + getDescription() + "'" +
                "}";
    }

}
