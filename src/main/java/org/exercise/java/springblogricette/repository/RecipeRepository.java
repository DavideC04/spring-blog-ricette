package org.exercise.java.springblogricette.repository;

import org.exercise.java.springblogricette.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
}
