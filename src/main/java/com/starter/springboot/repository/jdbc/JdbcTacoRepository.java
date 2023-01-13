package com.starter.springboot.repository.jdbc;

import com.starter.springboot.model.Taco;
import com.starter.springboot.repository.TacoRepository;

public class JdbcTacoRepository implements TacoRepository {
    @Override
    public Taco save(Taco taco) {
        return taco;
    }
}
