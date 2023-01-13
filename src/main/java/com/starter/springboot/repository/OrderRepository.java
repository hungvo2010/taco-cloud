package com.starter.springboot.repository;

import com.starter.springboot.model.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder tacoOrder);
}
