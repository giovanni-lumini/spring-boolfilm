package org.finalproject.spring.boolfilm.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "film")
public class Film {

    // VARIABLES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "title mustn't be null, empty or blank")
    private String title;

    @NotBlank(message = "author mustn't be null, empty or blank")
    private String author;

    @NotNull(message = "publication date mustn't be null, empty or blank")
    private LocalDate publicationDate;

    @Lob
    private String description;

    @Lob
    private String image;

    // MANY TO MANY
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "category_film", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    @JsonManagedReference
    private List<Category> categories;

    // GETTERS AND SETTERS
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getPublicationDate() {
        return this.publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    // TO STRING
    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", title='" + getTitle() + "'" +
                ", author='" + getAuthor() + "'" +
                ", publicationDate='" + getPublicationDate() + "'" +
                ", description='" + getDescription() + "'" +
                ", image='" + getImage() + "'" +
                "}";
    }
}
