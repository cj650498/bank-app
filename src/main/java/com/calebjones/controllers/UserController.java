package com.calebjones.controllers;

import com.calebjones.dao.UserDao;
import com.calebjones.models.User;
import com.calebjones.security.PasswordUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody User user) {

        // Hash the user's password before storing it in the database
        String hashedPassword = PasswordUtils.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        UserDao.createUser(user);

        // Return a ResponseEntity with a success message and HTTP status code 201
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }
}
