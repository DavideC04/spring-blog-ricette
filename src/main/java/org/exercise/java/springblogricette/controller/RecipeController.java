package org.exercise.java.springblogricette.controller;

import jakarta.validation.Valid;
import org.exercise.java.springblogricette.model.Recipe;
import org.exercise.java.springblogricette.repository.CategoryRepository;
import org.exercise.java.springblogricette.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("recipeAttribute", recipeRepository.findAll());
        return "recipes/list";

    }


    @GetMapping("/show/{recipeId}")
    public String show(@PathVariable("recipeId") Integer id, Model model) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if (recipeOptional.isPresent()) {
            Recipe recipeDb = recipeOptional.get();
            model.addAttribute("recipe", recipeDb);
            model.addAttribute("categories", recipeDb.getCategories());
            return "recipes/detail";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }


    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("categoryList", categoryRepository.findAll());
        model.addAttribute("recipeObj", new Recipe());
        return "recipes/form";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("recipeObj") Recipe recipeForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "recipes/form";
        }
        recipeRepository.save(recipeForm);
        return "redirect:/recipes";

    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Optional<Recipe> result = recipeRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("recipe", result.get());
            model.addAttribute("categoryList", categoryRepository.findAll());
            return "recipes/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ricetta con id: " + id + " non trovato");
        }

    }


    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable("id") Integer id, @Valid @ModelAttribute("recipe") Recipe recipeForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categoryList", categoryRepository.findAll());
            return "recipes/edit";
        }
        recipeRepository.save(recipeForm);
        return "redirect:/recipes";
    }
}
