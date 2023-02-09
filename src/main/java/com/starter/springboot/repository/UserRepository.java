package com.starter.springboot.repository;

import com.starter.springboot.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
//    @Query("SELECT id, username, password, fullname, street, city, state, zip, phone_number from User where username = :name limit 1")
    User findByUsername(String username);
}
