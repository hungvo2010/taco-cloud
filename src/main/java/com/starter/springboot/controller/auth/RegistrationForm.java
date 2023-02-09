package com.starter.springboot.controller.auth;

import com.starter.springboot.model.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String confirm;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, passwordEncoder.encode(password), fullname, street, city, state, zip, phone);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[" + username + ", " + password + ", " + fullname + ", " + street + ", " + city + ", " + state + ", " + zip + "]";
    }
}
