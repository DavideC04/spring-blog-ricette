package org.exercise.java.springblogricette.controller;

import org.exercise.java.springblogricette.model.Category;
import org.exercise.java.springblogricette.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("categoryList", categoryRepository.findAll());
        model.addAttribute("categoryObj", new Category());
        return "category/index";

    }

    @PostMapping("/create")
    public String doCreate(@ModelAttribute("categoryObj") Category categoryForm) {
        // salvo su db
        categoryRepository.save(categoryForm);
        return "redirect:/categories";

    }


    @PostMapping("/delete/{categoryId}")
    public String delete(@PathVariable("categoryId") Integer id) {
        categoryRepository.deleteById(id);
        return "redirect:/categories";
    }
}
