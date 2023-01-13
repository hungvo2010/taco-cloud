package com.starter.springboot.model;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("Ingredient_Ref")
public class IngredientRef {
    private final String ingredient;
}
