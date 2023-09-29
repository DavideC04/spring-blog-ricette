package org.exercise.java.springblogricette.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe {
    // ATTRIBUTI
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Questa sezione non deve essere vuota")
    private String title;
    @NotBlank(message = "Questa sezione non deve essere vuota")
    private String photoURL;

    @NotNull(message = "Questa sezione non deve essere vuota")
    @Min(1)
    private Integer preparationTime;

    @NotBlank(message = "Questa sezione non deve essere vuota")
    private String ingredients;

    @NotNull(message = "Questa sezione non deve essere vuota")
    @Min(1)
    private Integer portions;

    @NotBlank(message = "Questa sezione non deve essere vuota")
    @Column(length = 500)
    private String recipeText;

    @OneToMany
    private List<Category> categories;

    // GETTER E SETTER


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public Integer getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Integer preparationTime) {
        this.preparationTime = preparationTime;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Integer getPortions() {
        return portions;
    }

    public void setPortions(Integer portions) {
        this.portions = portions;
    }

    public String getRecipeText() {
        return recipeText;
    }

    public void setRecipeText(String recipeText) {
        this.recipeText = recipeText;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    // metodo per la stringa limitata nella card
    public String getLimitedString() {
        if (recipeText.length() > 100) {
            return recipeText.substring(0, 100) + "...";
        } else {
            return recipeText;
        }
    }
}


