package com.starter.springboot.repository;

import com.starter.springboot.model.TacoOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    public List<TacoOrder> readOrdersByDeliveryCity(String city);
}
