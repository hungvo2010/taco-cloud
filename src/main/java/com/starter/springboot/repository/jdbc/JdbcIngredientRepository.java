package com.starter.springboot.repository.jdbc;

import com.starter.springboot.model.Ingredient;
import com.starter.springboot.repository.IngredientRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Ingredient> findAll() {
        return jdbcTemplate.query("select * from Ingredient", this::mappingRowToIngredient);
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        List<Ingredient> result = jdbcTemplate.query("select id, name, type from Ingredient where id = ?", new Object[]{id}, this::mappingRowToIngredient);
        return result.size() == 0 ? Optional.empty() : Optional.of(result.get(0));
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update("insert into Ingredient (id, name, type) values (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString()
        );
        return ingredient;
    }

    private Ingredient mappingRowToIngredient(ResultSet rs, int rowNum) {
        try {
            return new Ingredient(
                    rs.getString("id"),
                    rs.getString("name"),
                    Ingredient.Type.valueOf(rs.getString("type"))
            );
        } catch (Exception ex) {
            return Ingredient.createDefaultIngredient();
        }
    }
}
