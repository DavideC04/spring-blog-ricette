package org.exercise.java.springblogricette.controller;

import org.exercise.java.springblogricette.model.Recipe;
import org.exercise.java.springblogricette.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping
    private String index(Model model) {
        model.addAttribute("recipeAttribute", recipeRepository.findAll());
        return "recipes/list";

    }


    @GetMapping("/show/{recipeId}")
    private String show(@PathVariable("recipeId") Integer id, Model model) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if (recipeOptional.isPresent()) {
            Recipe recipeDb = recipeOptional.get();
            model.addAttribute("recipe", recipeDb);
            return "recipes/detail";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }


    @GetMapping("/create")
    private String create(Model model) {
        model.addAttribute("recipeObj", new Recipe());
        return "recipes/form";
    }
}
