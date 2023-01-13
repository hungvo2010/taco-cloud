package com.starter.springboot.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("Ingredient")
public class Ingredient {
    @Id
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
