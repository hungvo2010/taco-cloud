package com.starter.springboot.model;

import lombok.Data;

@Data
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;

    public static Ingredient createDefaultIngredient() {
        return new Ingredient("FLTO", "Flour Tortilla", Type.WRAP);
    }

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
