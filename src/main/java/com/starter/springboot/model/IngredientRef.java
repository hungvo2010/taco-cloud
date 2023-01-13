package com.starter.springboot.model;

import lombok.Data;

@Data
public class IngredientRef {
    private final String ingredient;
    private final long taco;
    private final long taco_key;
}
