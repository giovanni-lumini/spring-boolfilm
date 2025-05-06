package org.finalproject.spring.boolfilm.repository;

import org.finalproject.spring.boolfilm.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
