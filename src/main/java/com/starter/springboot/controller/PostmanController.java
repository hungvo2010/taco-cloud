package com.starter.springboot.controller;

import com.starter.springboot.model.TacoOrder;
import com.starter.springboot.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class PostmanController {
    private OrderRepository repository;
    @Autowired
    public PostmanController(OrderRepository repo, String name){
        this.repository = repo;
    }
    @GetMapping("/postman")
    public List<TacoOrder> test() {
//        log.info(hello.toString());
//        return hello;
//        if (hello != null || !hello.isEmpty()) {
//            log.info(hello);
//        }
        List<TacoOrder> result = new ArrayList<>();
        repository.findAll().forEach(element -> {
            result.add(element);
        });
        return result;
    }

    @GetMapping("/test")
    public List<TacoOrder> guess(){
        return repository.readOrdersByDeliveryCity("Quang Nam");
    }
}
